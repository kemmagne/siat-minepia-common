package org.guce.epayment.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import static org.guce.epayment.Application.DB_SOURCE_USER;
import static org.guce.epayment.Application.sourceCon;
import static org.guce.epayment.Application.targetCon;

/**
 *
 * @author tadzotsa
 */
public class ToMigrator1 {

    public static void migrate() throws SQLException {

        try (final Statement oldToStatement = targetCon.createStatement();
                final ResultSet oldToResultSet = oldToStatement
                        .executeQuery("SELECT OLD_TO_NUMBER FROM TRANSFER_ORDER ORDER BY OLD_TO_NUMBER DESC")) {

            String oldToNumber = null;
            if (oldToResultSet.next()) {
                oldToNumber = oldToResultSet.getString(1);
            }

            String selectQuery = "SELECT O.NUMEROVIREMENT NUMEROVIREMENT, CODEMODEPAIEMENT, IDDONNEURORDRE, IDBENIFICIAIRE, "
                    + "MONTANTAPAYERCHIFFRE, NUMCOMPTEBENIF, CLERIBBENIF, CODEBANQUEDO, CODEAGENCEDO, CODEBANQUEBENIF, "
                    + "CODEAGENCEBENIF, LOGINAGENT, DATEACCUSE, NCONTRIBUABLE, DATECREATIONORDRE, DATECLOTUREORDRE, "
                    + "DATESIGNATURE, DATEORDRESEANCE "//, DAP.CODEDAP CODEDAP
                    + "FROM ORDREVIREMENTDO O";
//                    + "JOIN DOSSIERAPAYER DAP ON DAP.NUMEROVIREMENT = O.NUMEROVIREMENT";
            if (null != oldToNumber) {
                selectQuery += " WHERE O.NUMEROVIREMENT > " + oldToNumber;
            }
            selectQuery += " ORDER BY O.NUMEROVIREMENT";
            final String insertAccountQuery = "INSERT INTO CORE_BANK_ACCOUNT"
                    + " (ACCOUNT_ID, AGENCY_ID, BANK_ID, ACTIVE) VALUES (?, ?, ?, ?)";
            final String insertPayQuery = "INSERT INTO CORE_PAYMENT "
                    + "(ID, P_TYPE, AMOUNT, PARTNER_REFERENCE, P_REFERENCE, STATUS, COMMITER_ID, MODE_ID, BANK_GATEWAY_ID, STARTED_DATE, VALIDATION_DATE)"
                    + " VALUES (?, 'TRANSFER_ORDER', ?, NULL, ?, ?, (SELECT ID FROM PARTNER WHERE CODE = ?), "
                    + "(SELECT ID FROM PAYMENT_MODE WHERE CODE = ?), (SELECT ID FROM PARTNER WHERE CODE = ?), ?, ?)";
            final String insertTOQuery = "INSERT INTO TRANSFER_ORDER "
                    + "(ID, DEBIT_ACCOUNT_ID, RECEPTIVE_AGENCY_ID, INVOICE_TYPE_ID, CREDIT_ACCOUNT_ID, BENEFICIARY_AGENCY_ID, BENEFICIARY_BANK_ID, TAX_PAYER_ID, FLOW_IN_USE, SITTING_DATE, OLD_TO_NUMBER)"
                    + " VALUES (?, ?, (SELECT ID FROM PARTNER WHERE CODE = ?), "
                    + "(SELECT TYPE_ID FROM INVOICE WHERE ID = (SELECT INVOICE_ID FROM INVOICE_VERSION WHERE CODEDAP = "
                    + "(SELECT DAP.CODEDAP FROM " + DB_SOURCE_USER + ".DOSSIERAPAYER DAP WHERE DAP.NUMEROVIREMENT = ?))), "
                    + "(SELECT ID FROM BANK_ACCOUNT WHERE AND ACCOUNT_KEY = ? AND ACCOUNT_NUMBER = ? "
                    + "AND AGENCY_ID = (SELECT ID FROM PARTNER WHERE CODE = ?) "
                    + "AND BANK_ID = (SELECT ID FROM PARTNER WHERE CODE = ?)), "
                    + "(SELECT ID FROM PARTNER WHERE CODE = ?), (SELECT ID FROM PARTNER WHERE CODE = ?), "
                    + "(SELECT ID FROM PARTNER WHERE CODE = ?), 'T21', ?, ?)";
            final String insertPIVQuery = "INSERT INTO PAYMENT_INVOICE_VERSION "
                    + "(PAYMENT_ID, INVOICE_VERSION_ID, AMOUNT_PAID_FOR_INVOICE, RATE_USED_FOR_INVOICE) VALUES "
                    + "(?, (SELECT ID FROM INVOICE_VERSION WHERE CODEDAP = "
                    + "(SELECT DAP.CODEDAP FROM " + DB_SOURCE_USER + ".DOSSIERAPAYER DAP WHERE DAP.NUMEROVIREMENT = ?)), "
                    + "(SELECT BALANCE_AMOUNT FROM INVOICE_VERSION WHERE CODEDAP = "
                    + "(SELECT DAP.CODEDAP FROM " + DB_SOURCE_USER + ".DOSSIERAPAYER DAP WHERE DAP.NUMEROVIREMENT = ?)), "
                    + "100)";
            final String insertSignatureQuery = "INSERT INTO SIGNATURE (ID, ORIGIN_MESSAGE, SIGNATURE, "
                    + "SIGNATURE_DATE, LEVEL_VALUE, USER_ID, STEP_ID, PAYMENT_ID)"
                    + " VALUES (SIGNATURE_SEQ.NEXTVAL, 'ORIGIN_MESSAGE', 'SIGNATURE', ?, ?, "
                    + "(SELECT ID FROM USER WHERE LOGIN = ?), (SELECT ID FROM STEP WHERE CODE = 'T21'), ?)";

            final Map<Integer, String> paymentModesMap = new HashMap<>();

            paymentModesMap.put(1, "CASH");
            paymentModesMap.put(2, "CHECK");
            paymentModesMap.put(3, "COUNTER_TRANSFER");
            paymentModesMap.put(4, "BANK_TRANSFER");

            try (final Statement selectStatement = sourceCon.createStatement();
                    final ResultSet selectResultSet = selectStatement.executeQuery(selectQuery);
                    final PreparedStatement insertAccountStatement = targetCon.prepareStatement(insertAccountQuery);
                    final PreparedStatement insertPayStatement = targetCon.prepareStatement(insertPayQuery);
                    final PreparedStatement insertTOStatement = targetCon.prepareStatement(insertTOQuery);
                    final PreparedStatement insertPIVStatement = targetCon.prepareStatement(insertPIVQuery);
                    final PreparedStatement insertSignatureStatement = targetCon.prepareStatement(insertSignatureQuery);) {

            }
        }
    }

}
