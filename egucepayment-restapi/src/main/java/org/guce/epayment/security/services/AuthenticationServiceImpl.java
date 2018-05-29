package org.guce.epayment.security.services;

import java.util.ArrayList;
import java.util.Optional;
import org.guce.epayment.core.entities.Credentials;
import org.guce.epayment.core.entities.User;
import org.guce.epayment.core.repositories.UserRepository;
import org.guce.epayment.core.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Transactional
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    private static final int SALT_LENGTH = 64;
    private static final int ITERATIONS_NUMEBER = 10000;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveUser(final User user, final String password) {

        if (password != null) {

            final Credentials credentials = new Credentials();

            credentials.setOwner(user);
            credentials.setPassword(passwordEncoder.encode(password));

            if (CollectionUtils.isEmpty(user.getCredentialses())) {
                user.setCredentialses(new ArrayList<>(1));
            }
            user.getCredentialses().add(credentials);
        }

        if (Constants.BIG_DECIMAL_MINUS_ONE.equals(user.getId())) { // a new user
            user.setId(null);
        }

        userRepository.save(user);
    }

    @Override
    public Optional<User> authenticate(final String userLogin, final String password) {

        final User user = userRepository.findByLogin(userLogin).orElseThrow(() -> new UsernameNotFoundException("User not found : " + userLogin));
        final Credentials lastCredentials = user.getCredentialses().get(0);

        return Optional.ofNullable(passwordEncoder.matches(password, lastCredentials.getPassword()) ? user : null);
    }

}
