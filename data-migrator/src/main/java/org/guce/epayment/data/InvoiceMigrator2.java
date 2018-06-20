package org.guce.epayment.data;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.Properties;
import static org.guce.epayment.Application.POOL;
import static org.guce.epayment.Application.SUB_INVOICE_NUMBER_FORMAT;
import static org.guce.epayment.Application.SUB_INVOICE_TYPE_CODE_FORMAT;
import static org.guce.epayment.Application.sourceCon;
import static org.guce.epayment.Application.targetCon;
import org.guce.epayment.data.util.Utils;

/**
 *
 * @author tadzotsa
 */
public class InvoiceMigrator2 {

    public static void migrate() throws SQLException {

        init();
    }

    private static void migrateParents() throws SQLException {

        try (final Statement codedapStatement = targetCon.createStatement();
                final ResultSet codedapResultSet = codedapStatement
                        .executeQuery("SELECT CODEDAP FROM INVOICE_VERSION WHERE INVOICE_ID IN "
                                + "(SELECT ID FROM INVOICE WHERE PARENT_ID IS NULL) "
                                + "ORDER BY CODEDAP DESC")) {

            final Long lastCodedap = codedapResultSet.next() ? codedapResultSet.getLong(1) : null;
            String selectQuery = "SELECT CODEDAP, NUMERODOSSIERPARTENAIRE, VERSION, CODEDO, "
                    + "MONTANTAPAYER, ETATDOSSIER, CODEBENIF, TYPEDAP, NUMEROVIREMENT, MENTIONS_SPECIALES "
                    + "FROM DOSSIERAPAYER";
            if (lastCodedap != null) {
                selectQuery += " WHERE DOSSIER_PARENT IS NULL AND CODEDAP > " + lastCodedap;
            } else {
                selectQuery += " WHERE DOSSIER_PARENT IS NULL";
            }
            selectQuery += " ORDER BY CODEDAP";

            final String invInsertQuery = "INSERT INTO INVOICE (ID, AMOUNT, BENEF_REFERENCE, LAST_VERSION_DATE, "
                    + "LAST_VERSION_NUMBER, INVOICE_NUMBER, PAID_AMOUNT, STATUS, BENEFICIARY_ID, OWNER_ID, PARENT_ID, "
                    + "SUB_TYPE_ID, TYPE_ID, OLD_NUMBER) "
                    + "VALUES (?, ?, NULL, sysdate, 1, ?, ?, ?, NULL, "
                    + "(SELECT ID FROM PARTNER WHERE CODE = ?), NULL, "
                    + "(SELECT ID FROM INVOICE_TYPE WHERE CODE = ?), (SELECT ID FROM INVOICE_TYPE WHERE CODE = ?), ?)";
            final String invUpdateQuery = "UPDATE INVOICE SET AMOUNT = ?, LAST_VERSION_DATE = sysdate, "
                    + "LAST_VERSION_NUMBER = ?, PAID_AMOUNT = ?, STATUS = ? WHERE ID = ?";
            final String invVersInsertQuery = "INSERT INTO INVOICE_VERSION (ID, BALANCE_AMOUNT, CODEDAP, VERSION_DATE, "
                    + "VERSION_NUMBER, PAYMENT_DATE, VERSION_AMOUNT, INVOICE_ID) "
                    + "VALUES (INVOICE_VERSION_SEQ.NEXTVAL, ?, ?, sysdate, ?, ?, ?, ?)";
            final String invTypeParamsQuery = "SELECT PARAMS FROM INVOICE_TYPE WHERE CODE = ?";
            final String selectInvQuery = "SELECT ID, LAST_VERSION_NUMBER, AMOUNT, PAID_AMOUNT "
                    + "FROM INVOICE WHERE INVOICE_NUMBER = ? AND TYPE_ID = (SELECT ID FROM INVOICE_TYPE WHERE CODE = ?)";
            final String montantDapQuery = "SELECT SUM(MONTANTAPAYER) FROM DOSSIERAPAYER "
                    + "WHERE NUMERODOSSIERPARTENAIRE = ? AND ETATDOSSIERPARTENAIRE = 'valide'";
            final String montantPayeDapQuery = "SELECT SUM(MONTANTAPAYER) FROM DOSSIERAPAYER "
                    + "WHERE NUMERODOSSIERPARTENAIRE = ? AND ETATDOSSIER = 'P'";
            final String selectVersAmountQuery = "SELECT VERSION_AMOUNT FROM INVOICE_VERSION WHERE INVOICE_ID = ? AND VERSION_NUMBER = ?";
            int count = 0;
            boolean balance = false;

            try (final Statement selectDapStatement = sourceCon.createStatement();
                    final ResultSet selectDapResultSet = selectDapStatement.executeQuery(selectQuery);
                    final PreparedStatement invInsertStatement = targetCon.prepareStatement(invInsertQuery);
                    final PreparedStatement invUpdateStatement = targetCon.prepareStatement(invUpdateQuery);
                    final PreparedStatement invVersInsertStatement = targetCon.prepareStatement(invVersInsertQuery);
                    final PreparedStatement invTypeParamsStatement = targetCon.prepareStatement(invTypeParamsQuery);
                    final PreparedStatement selectInvStatement = targetCon.prepareStatement(selectInvQuery);
                    final PreparedStatement montantDapStatement = sourceCon.prepareStatement(montantDapQuery);
                    final PreparedStatement montantPayeDapStatement = sourceCon.prepareStatement(montantPayeDapQuery);
                    final PreparedStatement selectVersAmountStatement = targetCon.prepareStatement(selectVersAmountQuery)) {

                while (selectDapResultSet.next()) {

                    final String numDap = selectDapResultSet.getString("NUMERODOSSIERPARTENAIRE");
                    final long codedap = selectDapResultSet.getLong("CODEDAP");
                    final BigDecimal montantAPayer = selectDapResultSet.getBigDecimal("MONTANTAPAYER");
                    final String etatDossier = selectDapResultSet.getString("ETATDOSSIER");
                    final String invoiceTypeCode = selectDapResultSet.getString("TYPEDAP");

                    if ("ALCGS".equalsIgnoreCase(invoiceTypeCode) || "DM601".equalsIgnoreCase(invoiceTypeCode)) {
                        continue;
                    }

                    invTypeParamsStatement.setString(1, invoiceTypeCode);
                    try (final ResultSet resultSet = invTypeParamsStatement.executeQuery()) {
                        final Properties props = resultSet.next()
                                ? Utils.getParams(Optional.ofNullable(resultSet.getString(1)))
                                : new Properties();
                        balance = Boolean.parseBoolean(props.getProperty("balance", "false"));
                    }

                    selectInvStatement.setString(1, numDap);
                    selectInvStatement.setString(2, invoiceTypeCode);
                    final BigDecimal invoiceId, versionAmount, balanceAmount;
                    final int versionNumber;
                    final boolean insert;
                    try (final ResultSet resultSet = selectInvStatement.executeQuery()) {
                        if (!resultSet.next()) { // nouvelle facture
                            invoiceId = (BigDecimal) Utils.getNextId("INVOICE_SEQ");
                            versionAmount = montantAPayer;
                            balanceAmount = montantAPayer;
                            versionNumber = 1;
                            insert = true;
                        } else {
                            invoiceId = resultSet.getBigDecimal("ID");
                            versionNumber = resultSet.getInt("LAST_VERSION_NUMBER") + 1;
                            insert = false;
                            // on récupère le montant version de la version précédente
                            selectVersAmountStatement.setBigDecimal(1, invoiceId);
                            selectVersAmountStatement.setInt(1, versionNumber - 1);
                            try (final ResultSet resultSet1 = selectVersAmountStatement.executeQuery()) {
                                resultSet1.next();
                                final BigDecimal amount = resultSet1.getBigDecimal(1);
                                if ("P".equalsIgnoreCase(etatDossier)) {
                                    versionAmount = amount.add(montantAPayer);
                                    balanceAmount = montantAPayer;
                                } else {
                                    versionAmount = montantAPayer;
                                    balanceAmount = montantAPayer.subtract(amount);
                                }
                            }
                        }
                    }
                    // montant facture
                    montantDapStatement.setString(1, numDap);
                    BigDecimal invoiceAmount;
                    try (final ResultSet resultSet = montantDapStatement.executeQuery()) {
                        resultSet.next();
                        invoiceAmount = resultSet.getBigDecimal(1);
                        if (invoiceAmount == null) {
                            invoiceAmount = montantAPayer;
                        }
                    }
                    // montant payé
                    montantPayeDapStatement.setString(1, numDap);
                    BigDecimal paidAmount;
                    try (final ResultSet resultSet = montantPayeDapStatement.executeQuery()) {
                        resultSet.next();
                        paidAmount = resultSet.getBigDecimal(1);
                        if (paidAmount == null) {
                            paidAmount = BigDecimal.ZERO;
                        }
                    }
                    // status
                    final String status = invoiceAmount.equals(paidAmount) ? "PAID" : "UNPAID";
                    if (insert) {
                        invInsertStatement.setBigDecimal(1, invoiceId);
                        invInsertStatement.setBigDecimal(2, invoiceAmount);
                        invInsertStatement.setString(3, numDap);
                        invInsertStatement.setBigDecimal(4, paidAmount);
                        invInsertStatement.setString(5, status);
                        invInsertStatement.setString(6, selectDapResultSet.getString("CODEDO"));
                        invInsertStatement.setString(7, selectDapResultSet.getString("MENTIONS_SPECIALES"));
                        invInsertStatement.setString(8, invoiceTypeCode);
                        invInsertStatement.setString(9, numDap);
                        // execute
                        invInsertStatement.executeUpdate();
                    } else {
                        invUpdateStatement.setBigDecimal(1, invoiceAmount);
                        invUpdateStatement.setInt(2, versionNumber);
                        invUpdateStatement.setBigDecimal(3, paidAmount);
                        invUpdateStatement.setString(4, status);
                        invUpdateStatement.setBigDecimal(5, invoiceId);
                        // execute
                        invUpdateStatement.executeUpdate();
                    }

                    // version
                    invVersInsertStatement.setBigDecimal(1, balanceAmount);
                    invVersInsertStatement.setLong(2, codedap);
                    invVersInsertStatement.setInt(3, versionNumber);
                    invVersInsertStatement.setDate(4, "PAID".equals(status) ? new Date(System.currentTimeMillis()) : null);
                    invVersInsertStatement.setBigDecimal(5, versionAmount);
                    invVersInsertStatement.setBigDecimal(6, invoiceId);
                    // execute
                    invVersInsertStatement.executeUpdate();

                    count++;
                    if (count == POOL) {
//                            targetCon.commit();
                        count = 0;
                    }
                }
//                    targetCon.commit();
            }
        }
    }

    private static void init() throws SQLException {

        // création les types factures pour les lignes de facture
        final String selectTypedapQuery = "SELECT DISTINCT TYPEDAP FROM DOSSIERAPAYER WHERE DOSSIER_PARENT IS NULL";

        final String selectBenefQuery = "SELECT DISTINCT CODEBENIF FROM DOSSIERAPAYER WHERE DOSSIER_PARENT "
                + "IN (SELECT CODEDAP FROM DOSSIERAPAYER WHERE TYPEDAP = ? AND DOSSIER_PARENT IS NULL)";

        final String selectBenefNoSubQuery = "SELECT CODEBENIF FROM DOSSIERAPAYER WHERE TYPEDAP = ? AND ROWNUM = 1";

        final String insertInvTypeQuery = "INSERT INTO INVOICE_TYPE (ID, CODE, LABEL, PARAMS, SUB_TYPE, "
                + "STANDALONE) "
                + "VALUES (INVOICE_TYPE_SEQ.NEXTVAL, ?, (SELECT 'Facture '||NAME FROM PARTNER WHERE CODE = ?), "
                + "NULL, 0, 0)";

        try (final Statement selectTypedapStatement = sourceCon.createStatement();
                final ResultSet selectTypedapResultSet = selectTypedapStatement.executeQuery(selectTypedapQuery);
                final PreparedStatement selectBenefStatement = sourceCon.prepareStatement(selectBenefQuery);
                final PreparedStatement selectBenefNoSubStatement = sourceCon.prepareStatement(selectBenefNoSubQuery);
                final PreparedStatement insertInvTypeStatement = targetCon.prepareStatement(insertInvTypeQuery)) {

            while (selectTypedapResultSet.next()) {

                final String typedap = selectTypedapResultSet.getString(1);

                selectBenefStatement.setString(1, typedap);

                try (final ResultSet selectBenefResultSet = selectBenefStatement.executeQuery()) {

                    boolean hasSubInvs = false;
                    while (selectBenefResultSet.next()) {

                        hasSubInvs = true;

                        final String benefCode = selectBenefResultSet.getString(1);

                        insertInvTypeStatement.setString(1, getSubInvoiceTypeCode(typedap, benefCode));
                        insertInvTypeStatement.setString(2, benefCode);

                        try {
                            insertInvTypeStatement.executeUpdate();
                        } catch (SQLException ex) {
                            if (!ex.getMessage().contains("ORA-00001")) {
                                throw ex;
                            }
                        }

                    }

                    if (!hasSubInvs) {

                        selectBenefNoSubStatement.setString(1, typedap);

                        try (final ResultSet selectBenefNoSubResultSet = selectBenefNoSubStatement.executeQuery()) {

                            if (!selectBenefNoSubResultSet.next()) {
                                continue;
                            }

                            final String benefCode = selectBenefNoSubResultSet.getString(1);

                            insertInvTypeStatement.setString(1, getSubInvoiceTypeCode(typedap, benefCode));
                            insertInvTypeStatement.setString(2, benefCode);

                            try {
                                insertInvTypeStatement.executeUpdate();
                            } catch (SQLException ex) {
                                if (!ex.getMessage().contains("ORA-00001") && !ex.getMessage().contains("ORA-01400")) {
                                    throw ex;
                                }
                            }
                        }
                    }
                }
            }
            targetCon.commit();
        }
    }

    private static String getSubInvoiceTypeCode(String parentInvTypeCode, String benefCode) {
        return String.format(SUB_INVOICE_TYPE_CODE_FORMAT, parentInvTypeCode, benefCode);
    }

    private static String getSubInvoiceNb(String parentInvNb, String parentInvTypeCode, String benefCode) {
        return String.format(SUB_INVOICE_NUMBER_FORMAT, parentInvNb, parentInvTypeCode, benefCode);
    }

    private InvoiceMigrator2() {
    }

}
