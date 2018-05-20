package org.guce.epayment.campost.services;

import java.util.Map;
import org.guce.epayment.campost.documents.TRANSNET;
import org.springframework.stereotype.Service;

@Service("campostWalletService")
public class CampostWalletService extends AbstractCampostService {

    @Override
    protected void fillMapResForFirstReq(Map<String, String> result, TRANSNET transnet) {

        result.put("p_pac_code_one_label", transnet.getCATALOG().getCARDDATA().getPPACCODEONE());
        result.put("p_pac_code_two_label", transnet.getCATALOG().getCARDDATA().getPPACCODETWO());
        result.put("p_amount", transnet.getCATALOG().getCARDDATA().getTRANSAMOUNT().toString());
        String cardNumber = transnet.getCATALOG().getCARDDATA().getCARDNO();
        String[] cardNumberParts = cardNumber.split("-");
        result.put("p_card_number_part", cardNumberParts[(cardNumberParts.length - 1)]);
        result.put("code", "1");
    }

}
