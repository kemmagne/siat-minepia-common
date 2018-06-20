package org.guce.epayment.data;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import static org.guce.epayment.Application.DB_TARGET_USER;
import static org.guce.epayment.Application.POOL;
import static org.guce.epayment.Application.targetCon;
import org.guce.epayment.data.util.Utils;

/**
 *
 * @author tadzotsa
 */
public interface ToMigrator {

    static void migrate() throws SQLException {

        String oldToNumber = null;
        try (final Statement oldToStatement = targetCon.createStatement();
                final ResultSet oldToResultSet = oldToStatement
                        .executeQuery("SELECT OLD_TO_NUMBER FROM TRANSFER_ORDER ORDER BY OLD_TO_NUMBER DESC")) {
            if (oldToResultSet.next()) {
                oldToNumber = oldToResultSet.getString(1);
            }
        }

        String selectQuery = "SELECT NUMEROVIREMENT, CODEMODEPAIEMENT, IDDONNEURORDRE, IDBENIFICIAIRE, "
                + "MONTANTAPAYERCHIFFRE, NUMCOMPTEBENIF, CLERIBBENIF, CODEBANQUEDO, CODEAGENCEDO, CODEBANQUEBENIF, "
                + "CODEAGENCEBENIF, LOGINAGENT, DATEACCUSE, NCONTRIBUABLE, DATECREATIONORDRE, DATECLOTUREORDRE, "
                + "DATESIGNATURE, DATEORDRESEANCE FROM ORDREVIREMENTDO WHERE NUMEROVIREMENT IN "
                + "(SELECT IV.OLD_TO_NUMBER OLD_TO_NUMBER FROM " + DB_TARGET_USER
                + ".INVOICE_VERSION IV WHERE IV.E_GUCE_REFERENCE IS NOT NULL) ORDER BY NUMEROVIREMENT";
        if (null != oldToNumber) {
            selectQuery += " WHERE NUMEROVIREMENT > " + oldToNumber;
        }

        final String insertPayQuery = "INSERT INTO CORE_PAYMENT "
                + "(ID, P_TYPE, AMOUNT, PARTNER_REFERENCE, P_REFERENCE, STATUS, COMMITER_ID, MODE_ID, BANK_GATEWAY_ID, STARTED_DATE, VALIDATION_DATE)"
                + " VALUES (?, 'TRANSFER_ORDER', ?, NULL, ?, ?, (SELECT ID FROM PARTNER WHERE CODE = ?), "
                + "(SELECT ID FROM PAYMENT_MODE WHERE CODE = ?), (SELECT ID FROM PARTNER WHERE CODE = ?), ?, ?)";
        final String insertSignatureQuery = "INSERT INTO SIGNATURE (ID, ORIGIN_MESSAGE, SIGNATURE, "
                + "SIGNATURE_DATE, LEVEL_VALUE, USER_ID, STEP_ID, PAYMENT_ID)"
                + " VALUES (SIGNATURE_SEQ.NEXTVAL, 'ORIGIN_MESSAGE', 'SIGNATURE', ?, ?, "
                + "(SELECT ID FROM USER WHERE LOGIN = ?), (SELECT ID FROM STEP WHERE CODE = 'T21'), ?)";
        final String insertTOQuery = "INSERT INTO TRANSFER_ORDER "
                + "(ID, DEBIT_ACCOUNT_ID, RECEPTIVE_AGENCY_ID, INVOICE_TYPE_ID, CREDIT_ACCOUNT_ID, BENEFICIARY_AGENCY_ID, BENEFICIARY_BANK_ID, TAX_PAYER_ID, FLOW_IN_USE, SITTING_DATE, OLD_TO_NUMBER)"
                + " VALUES (?, ?, (SELECT ID FROM PARTNER WHERE CODE = ?), "
                + "(SELECT TYPE_ID FROM INVOICE WHERE ID = (SELECT INVOICE_ID FROM INVOICE_VERSION WHERE CODEDAP = "
                + "(SELECT CODEDAP FROM INVOICE_VERSION WHERE OLD_TO_NUMBER = ?))), "
                + "(SELECT ID FROM BANK_ACCOUNT WHERE AND ACCOUNT_KEY = ? AND ACCOUNT_NUMBER = ? "
                + "AND AGENCY_ID = (SELECT ID FROM PARTNER WHERE CODE = ?) "
                + "AND BANK_ID = (SELECT ID FROM PARTNER WHERE CODE = ?)), "
                + "(SELECT ID FROM PARTNER WHERE CODE = ?), (SELECT ID FROM PARTNER WHERE CODE = ?), "
                + "(SELECT ID FROM PARTNER WHERE CODE = ?), 'T21', ?, ?)";
        final String insertAccountQuery = "INSERT INTO CORE_BANK_ACCOUNT"
                + " (ACCOUNT_ID, AGENCY_ID, BANK_ID, ACTIVE) VALUES (?, ?, ?, ?)";
        final String insertPIVQuery = "INSERT INTO PAYMENT_INVOICE_VERSION "
                + "(PAYMENT_ID, INVOICE_VERSION_ID, AMOUNT_PAID_FOR_INVOICE, RATE_USED_FOR_INVOICE) VALUES "
                + "(?, (SELECT ID FROM INVOICE_VERSION WHERE CODEDAP = "
                + "(SELECT CODEDAP FROM INVOICE_VERSION WHERE OLD_TO_NUMBER = ?)), "
                + "(SELECT BALANCE_AMOUNT FROM INVOICE_VERSION WHERE CODEDAP = "
                + "(SELECT CODEDAP FROM INVOICE_VERSION WHERE OLD_TO_NUMBER = ?)), "
                + "100)";

        final Map<Integer, String> paymentModesMap = new HashMap<>();

        paymentModesMap.put(1, "CASH");
        paymentModesMap.put(2, "CHECK");
        paymentModesMap.put(3, "COUNTER_TRANSFER");
        paymentModesMap.put(4, "BANK_TRANSFER");

        int count = 0;

        try (final Statement selectStatement = targetCon.createStatement();
                final ResultSet selectResultSet = selectStatement.executeQuery(selectQuery);
                final PreparedStatement insertPayStatement = targetCon.prepareStatement(insertPayQuery);
                final PreparedStatement insertSignatureStatement = targetCon.prepareStatement(insertSignatureQuery);
                final PreparedStatement insertTOStatement = targetCon.prepareStatement(insertTOQuery);
                final PreparedStatement insertAccountStatement = targetCon.prepareStatement(insertAccountQuery);
                final PreparedStatement insertPIVStatement = targetCon.prepareStatement(insertPIVQuery)) {

            while (selectResultSet.next()) {

                final BigDecimal paymentId = (BigDecimal) Utils.getNextId("PAYMENT_SEQ");

                if (count++ == POOL) {
                    targetCon.commit();
                    count = 0;
                }
            }
        }
    }

}
