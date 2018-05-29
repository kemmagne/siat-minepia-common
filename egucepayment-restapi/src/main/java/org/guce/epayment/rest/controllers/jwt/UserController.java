package org.guce.epayment.rest.controllers.jwt;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.mail.MessagingException;
import org.guce.epayment.core.entities.Partner;
import org.guce.epayment.core.entities.Role;
import org.guce.epayment.core.entities.User;
import org.guce.epayment.core.services.CoreService;
import org.guce.epayment.core.services.EmailService;
import org.guce.epayment.core.services.UserService;
import org.guce.epayment.core.utils.Constants;
import org.guce.epayment.core.utils.MailConstants;
import org.guce.epayment.rest.controllers.utils.RestConstants;
import org.guce.epayment.rest.dto.DefaultDto;
import org.guce.epayment.rest.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.guce.epayment.rest.controllers.utils.RestUtils;
import org.guce.epayment.rest.controllers.utils.TemplateConstants;
import org.guce.epayment.rest.dto.RoleDto;
import org.guce.epayment.rest.dto.SetPasswordDto;
import org.guce.epayment.security.core.util.SecurityUtils;
import org.guce.epayment.security.services.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author tadzotsa
 */
@Transactional
@RestController
@RequestMapping("jwt")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private CoreService coreService;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private EmailService emailService;

    @ResponseBody
    @RequestMapping(path = "admin/users/all", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> findAllUsers() throws Exception {

        final List<User> allUsers = coreService.findAll(User.class);

        return ResponseEntity.ok(allUsers.stream().map(
                user -> RestUtils.getUserDto(user, Optional.empty())
        ).collect(Collectors.toList()));
    }

    @ResponseBody
    @RequestMapping(path = "public/users", method = RequestMethod.POST)
    public ResponseEntity saveUserFromRegistrationRequest(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(saveUser(userDto, false));
    }

    @ResponseBody
    @RequestMapping(path = "admin/users", method = RequestMethod.POST)
    public ResponseEntity saveUserFromAdminInterface(@RequestBody UserDto userDto) throws IOException {
        return ResponseEntity.ok(saveUser(userDto, true));
    }

    private Object saveUser(final UserDto userDto, final boolean admin) {

        final String userLogin = userDto.getLogin();
        BigDecimal userId = userDto.getId();

        userDto.setActive(admin);

        if (userId == null) {
            userId = Constants.BIG_DECIMAL_MINUS_ONE;
        }

        if (Constants.BIG_DECIMAL_MINUS_ONE.equals(userId)
                && coreService.findByUniqueKey(Constants.UK_USER_LOGIN, userLogin, User.class).isPresent()) {
            // tentative de créer un utilisateur déjà existant
            return DefaultDto.of("0");
        }

        if (!userDto.getEmail().matches(RestConstants.EMAIL_PATTERN)) {
            // email non conforme à la regex
            return DefaultDto.of("1");
        }

        final User user = coreService.findById(userId, User.class).orElse(new User());

        user.setLogin(userLogin);
        user.setTitle(userDto.getTitle());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setActive(userDto.isActive());
        user.setEmail(userDto.getEmail());
        user.setResetPassword(admin);
        user.setPartner(coreService.findById(userDto.getPartner().getId(), Partner.class).get());
        user.setRoles(userDto.getRoles().stream().map(
                roleName -> coreService.findByUniqueKey(Constants.UK_ROLE_NAME, roleName, Role.class).get()
        ).collect(Collectors.toList()));

        String password = null;

        if (Constants.BIG_DECIMAL_MINUS_ONE.equals(userId) && admin) { // créé par l'admin
            password = RestConstants.DEFAULT_PASSWORD;
        } else if (Constants.BIG_DECIMAL_MINUS_ONE.equals(userId) && !admin) { // demande de création de compte
            password = userDto.getPassword();
        }

        authenticationService.saveUser(user, password);

        userDto.setId(user.getId());
        if (admin) {
            userDto.setPassword(password);
        }

        return userDto;
    }

    @ResponseBody
    @RequestMapping(path = "admin/users/locked", method = RequestMethod.POST)
    public ResponseEntity<DefaultDto> updateLockedState(@RequestBody UserDto userDto) {

        final User user = coreService.findById(userDto.getId(), User.class).get();

        user.setLocked(userDto.isLocked());

        authenticationService.saveUser(user, null);

        return ResponseEntity.ok(RestConstants.DEFAULT_RESPONSE_BODY);
    }

    @ResponseBody
    @RequestMapping(path = "admin/users/active", method = RequestMethod.POST)
    public ResponseEntity<DefaultDto> updateActiveState(@RequestBody BigDecimal userId) {

        final User user = coreService.findById(userId, User.class).get();

        user.setActive(true);

        authenticationService.saveUser(user, null);

        return ResponseEntity.ok(RestConstants.DEFAULT_RESPONSE_BODY);
    }

    @ResponseBody
    @RequestMapping(path = "admin/users/password/reset", method = RequestMethod.POST)
    public ResponseEntity<DefaultDto> resetPassword(@RequestBody BigDecimal userId) {

        final User user = coreService.findById(userId, User.class).get();

        user.setResetPassword(true);

        final String password = SecurityUtils.generatePassword();

        authenticationService.saveUser(user, password);

        try {
            final Map<String, Object> props = new HashMap<>();

            final String userLocale = user.getLogin();

            props.put(MailConstants.FTL, TemplateConstants.FTL_RESET_PASSWORD + userLocale + TemplateConstants.FTL_EXTENSION);
            props.put(MailConstants.SUBJECT, RestUtils.getResourceBundle(userLocale).getString(TemplateConstants.LOCALE_RESET_PASSWORD));
            props.put(MailConstants.TO, new String[]{user.getEmail()});

            // template elements
            props.put(TemplateConstants.USER_TITLE, user.getTitle());
            props.put(TemplateConstants.USER_LAST_NAME, user.getLastName());
            props.put(TemplateConstants.USER_FIRST_NAME, user.getFirstName());
            props.put(TemplateConstants.PASSWORD, password);

            emailService.send(props);

            return ResponseEntity.ok(RestConstants.DEFAULT_RESPONSE_BODY);
        } catch (final MessagingException ex) {
            LOGGER.error("Problem occured when trying to send password by mail", ex);
            return ResponseEntity.ok(DefaultDto.of(password));
        }
    }

    @ResponseBody
    @RequestMapping(path = "public/roles/all", method = RequestMethod.GET)
    public ResponseEntity<List<RoleDto>> findAllRoles() throws Exception {

        final List<Role> allRoles = coreService.findAll(Role.class);

        return ResponseEntity.ok(allRoles.stream().map(
                role -> RoleDto.of(role.getName(), role.getDescription())
        ).collect(Collectors.toList()));
    }

    @ResponseBody
    @RequestMapping(path = "users/by-roles/{roles}", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> findUsersByRoles(@PathVariable String roles) {

        final List<User> usersByRoles = userService.findByRoles(roles);

        return ResponseEntity.ok(usersByRoles.stream().map(
                user -> RestUtils.getUserDto(user, Optional.empty())
        ).collect(Collectors.toList()));
    }

    @ResponseBody
    @RequestMapping(path = "admin/users/desactivated", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> findDesactivedUsers() {

        final List<User> desactivatedUsers = userService.findDesactivedUsers();

        return ResponseEntity.ok(desactivatedUsers.stream().map(
                user -> RestUtils.getUserDto(user, Optional.empty())
        ).collect(Collectors.toList()));
    }

    @ResponseBody
    @RequestMapping(path = "admin/users/locked", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> findLockedUsers() {

        final List<User> lockedUsers = userService.findLockedUsers();

        return ResponseEntity.ok(lockedUsers.stream().map(
                user -> RestUtils.getUserDto(user, Optional.empty())
        ).collect(Collectors.toList()));
    }

    @ResponseBody
    @RequestMapping(path = "users/password", method = RequestMethod.POST)
    public ResponseEntity<DefaultDto> setPassword(@RequestHeader(RestConstants.LOGIN) String userLogin,
            @RequestBody SetPasswordDto passwordDto) {

        final String actual = passwordDto.getActual();
        final Optional<User> userOp = authenticationService.authenticate(userLogin, actual);

        if (!userOp.isPresent()) {
            return ResponseEntity.ok(DefaultDto.of("0"));
        }

        authenticationService.saveUser(userOp.get(), passwordDto.getUpdate());
        return ResponseEntity.ok(RestConstants.DEFAULT_RESPONSE_BODY);
    }

    @ResponseBody
    @RequestMapping(path = "users/by-login", method = RequestMethod.GET)
    public ResponseEntity findUserByLogin(@RequestHeader(RestConstants.LOGIN) String userLogin) {

        final Optional<User> userOp = coreService.findByUniqueKey(Constants.UK_USER_LOGIN, userLogin, User.class);

        if (!userOp.isPresent()) {
            return ResponseEntity.ok(DefaultDto.of("NULL"));
        }

        return ResponseEntity.ok(RestUtils.getUserDto(userOp.get(), Optional.empty()));
    }

    @ResponseBody
    @RequestMapping(path = "users/by-partner/{partnerId}/{roles}", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> findByPartnerAndRoles(@PathVariable("partnerId") BigDecimal partnerId,
            @PathVariable("roles") String roles) {

        final List<User> usersByPartnerAndRoles = userService.findByPartnerAndRoles(partnerId, roles);

        return ResponseEntity.ok(usersByPartnerAndRoles.stream().map(
                user -> RestUtils.getUserDto(user, Optional.empty())
        ).collect(Collectors.toList()));
    }

}
