package org.guce.epayment.rest.controllers.jwt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.guce.epayment.core.entities.PaymentMode;
import org.guce.epayment.core.entities.Step;
import org.guce.epayment.core.entities.User;
import org.guce.epayment.core.entities.UserStep;
import org.guce.epayment.core.entities.enums.PartnerTypeCode;
import org.guce.epayment.core.entities.enums.StepCode;
import org.guce.epayment.core.services.CoreService;
import org.guce.epayment.core.services.UserStepService;
import org.guce.epayment.core.utils.Constants;
import org.guce.epayment.rest.controllers.utils.RestConstants;
import org.guce.epayment.rest.controllers.utils.RestUtils;
import org.guce.epayment.rest.dto.DefaultDto;
import org.guce.epayment.rest.dto.PaymentModeDto;
import org.guce.epayment.rest.dto.StepDto;
import org.guce.epayment.rest.dto.UserDto;
import org.guce.epayment.rest.dto.UserStepDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author tadzotsa
 */
@Transactional
@RestController
@RequestMapping("jwt/admin/multiple/validation")
public class MultipleValidationController {

    @Autowired
    private CoreService coreService;
    @Autowired
    private UserStepService userStepService;

    @ResponseBody
    @RequestMapping(path = "user/step/by-partner/{partnerId}", method = RequestMethod.GET)
    public ResponseEntity<List<UserStepDto>> findUserStepsByPartner(@PathVariable BigDecimal partnerId) {

        final List<UserStep> userSteps = userStepService.findByPartner(partnerId);

        return ResponseEntity.ok(userSteps.stream().map(userStep -> {

            final UserStepDto userStepDto = new UserStepDto();

            userStepDto.setId(userStep.getId());
            userStepDto.setLevel(userStep.getLevel());
            userStepDto.setUser(RestUtils.downCast(User.class, UserDto.class, userStep.getUser()));
            userStepDto.setStep(RestUtils.downCast(Step.class, StepDto.class, userStep.getStep()));
            userStepDto.setPaymentMode(RestUtils.downCast(PaymentMode.class, PaymentModeDto.class,
                    userStep.getPaymentMode()));

            return userStepDto;
        }).collect(Collectors.toList()));
    }

    @ResponseBody
    @RequestMapping(path = "user/step/by-partner/{partnerId}/{partnerType}/{add}", method = RequestMethod.POST)
    public ResponseEntity<DefaultDto> setValidationLevels(@PathVariable("partnerId") BigDecimal partnerId,
            @PathVariable("partnerType") String partnerType, @PathVariable("add") boolean add,
            @RequestBody UserStepDto userStepDto) {

        final List<UserStep> userSteps = getUserSteps(partnerType, userStepDto);

        if (add) {
            userStepService.addValidationLevels(partnerId, userSteps);
        } else {
            userStepService.removeValidationLevels(partnerId, userSteps);
        }

        return ResponseEntity.ok(RestConstants.DEFAULT_RESPONSE_BODY);
    }

    private List<UserStep> getUserSteps(String partnerType, UserStepDto userStepDto) {

        final List<UserStep> userSteps = new ArrayList<>();
        PaymentMode paymentMode = null;

        if (userStepDto.getPaymentMode() != null) {
            paymentMode = coreService.findByUniqueKey(Constants.UK_CODE, userStepDto.getPaymentMode().getCode().name(), PaymentMode.class).get();
        }

        final User user = coreService.findByUniqueKey(Constants.UK_USER_LOGIN, userStepDto.getUser().getLogin(), User.class).get();
        final int level = userStepDto.getLevel();

        final PartnerTypeCode partnerTypeCode = PartnerTypeCode.valueOf(partnerType);
        switch (partnerTypeCode) {

            case PRINCIPAL: {
                userSteps.add(getUserStep(user,
                        coreService.findByUniqueKey(Constants.UK_CODE, StepCode.T11.name(), Step.class).get(),
                        paymentMode, level));
            }
            break;

            case BANK:
            case BANK_AGENCY: {

                userSteps.add(getUserStep(user,
                        coreService.findByUniqueKey(Constants.UK_CODE, StepCode.T21.name(), Step.class).get(),
                        paymentMode, level));
                userSteps.add(getUserStep(user,
                        coreService.findByUniqueKey(Constants.UK_CODE, StepCode.T31.name(), Step.class).get(),
                        paymentMode, level));
            }
            break;

            case BENEFICIARY: {
                userSteps.add(getUserStep(user,
                        coreService.findByUniqueKey(Constants.UK_CODE, StepCode.T41.name(), Step.class).get(),
                        paymentMode, level));
            }
        }

        return userSteps;
    }

    private UserStep getUserStep(User user, Step step, PaymentMode paymentMode, int level) {

        final UserStep userStep = new UserStep();

        userStep.setLevel(level);
        userStep.setPaymentMode(paymentMode);
        userStep.setStep(step);
        userStep.setUser(user);

        return userStep;
    }

}

