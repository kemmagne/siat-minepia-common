package org.guce.epayment.rest.controllers.jwt;

import java.util.List;
import java.util.stream.Collectors;
import org.guce.epayment.core.entities.Partner;
import org.guce.epayment.core.entities.User;
import org.guce.epayment.core.services.CoreService;
import org.guce.epayment.core.utils.Constants;
import org.guce.epayment.rest.controllers.utils.RestUtils;
import org.guce.epayment.rest.dto.TransferOrderDto;
import org.guce.epayment.transfer.entities.TransferOrder;
import org.guce.epayment.transfer.services.TransferOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tadzotsa
 */
@RestController
@RequestMapping("jwt/statistics")
public class StatisticsController {

    @Autowired
    private CoreService coreService;
    @Autowired
    private TransferOrderService transferOrderService;

    @ResponseBody
    @RequestMapping(path = "transfer/orders/by-bank/last/{number}", method = RequestMethod.GET)
    public ResponseEntity<List<TransferOrderDto>> findLastTransferOrders(@RequestHeader("login") String userLogin,
            @RequestHeader("locale") String locale, @PathVariable int number) {

        final User connectedUser = coreService.findByUniqueKey(Constants.UK_USER_LOGIN, userLogin, User.class).get();
        final Partner partner = connectedUser.getPartner();
        final Partner partnerParent = partner.getParent();
        final String bankCode;

        if (null != partnerParent) {
            bankCode = partnerParent.getCode();
        } else {
            bankCode = partner.getCode();
        }

        final List<TransferOrder> lastTransferOrders = transferOrderService
                .findLastTransferOrders(Constants.ONE, bankCode, number);

        return ResponseEntity.ok(lastTransferOrders.stream().map(
                to -> RestUtils.getTransferOrderDto(to, locale)
        ).collect(Collectors.toList()));
    }

    @ResponseBody
    @RequestMapping(path = "transfer/orders/by-beneficiary/last/{number}", method = RequestMethod.GET)
    public ResponseEntity<List<TransferOrderDto>> findLastPaymentByBenef(@RequestHeader("login") String userLogin,
            @RequestHeader("locale") String locale, @PathVariable int number) {

        final User connectedUser = coreService.findByUniqueKey(Constants.UK_USER_LOGIN, userLogin, User.class).get();

        final List<TransferOrder> lastTransferOrders = transferOrderService
                .findLastTransferOrders(Constants.TWO, connectedUser.getPartner().getCode(), number);

        return ResponseEntity.ok(lastTransferOrders.stream().map(
                to -> RestUtils.getTransferOrderDto(to, locale)
        ).collect(Collectors.toList()));
    }

    @ResponseBody
    @RequestMapping(path = "transfer/orders/by-decision-maker/last/{number}", method = RequestMethod.GET)
    public ResponseEntity<List<TransferOrderDto>> findLastPaymentByDecisionMaker(@RequestHeader("login") String decisionMakerLogin,
            @RequestHeader("locale") String locale, @PathVariable int number) {

        final List<TransferOrder> lastTransferOrders = transferOrderService
                .findLastTransferOrders(Constants.THREE, decisionMakerLogin, number);

        return ResponseEntity.ok(lastTransferOrders.stream().map(
                to -> RestUtils.getTransferOrderDto(to, locale)
        ).collect(Collectors.toList()));
    }

}
