package org.guce.epayment.rest.dto;

import lombok.Data;

/**
 *
 * @author hyacinthe
 */
@Data
public class CampostMobileFirstRes {

    private int responseCode;
    private String orderNumber;
    private String pacCodeOneLabel;
    private String pacCodeTwoLabel;
    private String amount;
    private String cardNumberPart;

    public CampostMobileFirstRes() {
    }

    private CampostMobileFirstRes(int responseCode, String orderNumber, String pacCodeOneLabel, String pacCodeTwoLabel, String amount, String cardNumberPart) {
        this.responseCode = responseCode;
        this.orderNumber = orderNumber;
        this.pacCodeOneLabel = pacCodeOneLabel;
        this.pacCodeTwoLabel = pacCodeTwoLabel;
        this.amount = amount;
        this.cardNumberPart = cardNumberPart;
    }

    public static CampostMobileFirstRes of(int responseCode, String orderNumber, String pacCodeOneLabel, String pacCodeTwoLabel, String amount, String cardNumberPart) {

        return new CampostMobileFirstRes(responseCode, orderNumber, pacCodeOneLabel, pacCodeTwoLabel, amount, cardNumberPart);
    }

}
