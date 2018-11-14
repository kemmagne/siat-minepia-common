package org.guce.epayment.rest.controllers.jwt;

import java.util.List;
import java.util.stream.Collectors;
import org.guce.epayment.core.entities.PaymentMode;
import org.guce.epayment.core.services.CoreService;
import org.guce.epayment.core.services.PaymentService;
import org.guce.epayment.rest.controllers.utils.RestUtils;
import org.guce.epayment.rest.dto.PaymentModeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tadzotsa
 */
@RestController
@RequestMapping("jwt")
public class PaymentController {

    @Autowired
    private CoreService coreService;
    @Autowired
    private PaymentService paymentService;

    @ResponseBody
    @RequestMapping(path = "admin/payment/modes/all", method = RequestMethod.GET)
    public ResponseEntity<List<PaymentModeDto>> findAllPaymentModes() {

        final List<PaymentMode> allPaymentModes = coreService.findAll(PaymentMode.class);

        return ResponseEntity.ok(allPaymentModes.stream().map(pm -> {
            PaymentModeDto pmDto = RestUtils.downCast(PaymentMode.class, PaymentModeDto.class, pm);
            return pmDto;
        }).collect(Collectors.toList()));
    }

    @ResponseBody
    @RequestMapping(path = "public/payment/modes/by-direct/{direct}", method = RequestMethod.GET)
    public ResponseEntity<List<PaymentModeDto>> findPaymentModesByDirect(@PathVariable boolean direct) {

        final List<PaymentMode> paymentModesByDirect = paymentService.findPaymentModesByDirect(direct);

        return ResponseEntity.ok(paymentModesByDirect.stream().map(pm -> {
            PaymentModeDto pmDto = RestUtils.downCast(PaymentMode.class, PaymentModeDto.class, pm);
            return pmDto;
        }).collect(Collectors.toList()));
    }

}

