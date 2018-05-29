package org.guce.epayment.rest.controllers.jwt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.guce.epayment.core.entities.PartnerType;
import org.guce.epayment.core.entities.PaymentMode;
import org.guce.epayment.core.entities.Step;
import org.guce.epayment.core.entities.User;
import org.guce.epayment.core.entities.UserStep;
import org.guce.epayment.core.services.CoreService;
import org.guce.epayment.core.services.UserStepService;
import org.guce.epayment.core.utils.Constants;
import org.guce.epayment.rest.controllers.utils.RestConstants;
import org.guce.epayment.rest.controllers.utils.RestUtils;
import org.guce.epayment.rest.dto.DefaultDto;
import org.guce.epayment.rest.dto.PaymentModeDto;
import org.guce.epayment.rest.dto.StepDto;
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
            userStepDto.setUser(RestUtils.getUserDto(userStep.getUser(), Optional.empty()));
            userStepDto.setStep(StepDto.of(userStep.getStep().getCode(), userStep.getStep().getLabel()));
            userStepDto.setPaymentMode(userStep.getPaymentMode() == null ? null
                    : PaymentModeDto.of(userStep.getPaymentMode().getCode(), userStep.getPaymentMode().getLabel()));

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
            paymentMode = coreService.findByUniqueKey(Constants.UK_CODE, userStepDto.getPaymentMode().getCode(), PaymentMode.class).get();
        }

        final User user = coreService.findByUniqueKey(Constants.UK_USER_LOGIN, userStepDto.getUser().getLogin(), User.class).get();
        final int level = userStepDto.getLevel();

        switch (partnerType) {

            case PartnerType.PARTNER_TYPE_PRINCIPAL: {
                userSteps.add(getUserStep(user,
                        coreService.findByUniqueKey(Constants.UK_CODE, Step.STEP_TO_PRINCIPAL, Step.class).get(),
                        paymentMode, level));
            }
            break;

            case PartnerType.PARTNER_TYPE_BANK:
            case PartnerType.PARTNER_TYPE_BANK_AGENCY: {

                userSteps.add(getUserStep(user,
                        coreService.findByUniqueKey(Constants.UK_CODE, Step.STEP_TO_PRINCIPAL_BANK, Step.class).get(),
                        paymentMode, level));
                userSteps.add(getUserStep(user,
                        coreService.findByUniqueKey(Constants.UK_CODE, Step.STEP_TO_BENEFICIARY_BANK, Step.class).get(),
                        paymentMode, level));
            }
            break;

            case PartnerType.PARTNER_TYPE_BENEFICIARY: {
                userSteps.add(getUserStep(user,
                        coreService.findByUniqueKey(Constants.UK_CODE, Step.STEP_TO_BENEFICIARY, Step.class).get(),
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
