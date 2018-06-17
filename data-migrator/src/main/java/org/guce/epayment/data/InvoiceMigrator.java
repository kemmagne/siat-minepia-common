package org.guce.epayment.data;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.guce.epayment.data.Application.POOL;
import static org.guce.epayment.data.Application.SUB_INVOICE_NUMBER_FORMAT;
import static org.guce.epayment.data.Application.SUB_INVOICE_TYPE_CODE_FORMAT;
import static org.guce.epayment.data.Application.sourceCon;
import static org.guce.epayment.data.Application.targetCon;
import org.guce.epayment.data.util.Utils;

/**
 *
 * @author tadzotsa
 */
public class InvoiceMigrator {

    private static final Logger LOGGER = Logger.getLogger(AccountMigrator.class.getName());

    public static void migrate() throws SQLException {

        try (final Statement codedapStatement = targetCon.createStatement();
                final ResultSet codedapResultSet = codedapStatement
                        .executeQuery("SELECT CODEDAP FROM INVOICE_VERSION ORDER BY CODEDAP DESC")) {

            final Long lastCodedap = codedapResultSet.next() ? codedapResultSet.getLong(1) : null;
            String selectQuery = "SELECT CODEDAP, DAP.NUMERODOSSIERPARTENAIRE NUMERODOSSIERPARTENAIRE, VERSION, CODEDO, "
                    + "DOSSIER_PARENT, MONTANTAPAYER, ETATDOSSIER, CODEBENIF, TYPEDAP, MENTIONS_SPECIALES, "
                    + "NUMERODOSSIERGUCE, DATEETATPARTENAIRE, DATECREATIONORDRE DATE_PAIEMENT, DATEQUITTANCE "
                    + "FROM DOSSIERAPAYER DAP "
                    + "LEFT JOIN ORDREVIREMENTDO O ON O.NUMEROVIREMENT = DAP.NUMEROVIREMENT "
                    + "LEFT JOIN QUITTANCE Q ON Q.NUMERODOSSIERPARTENAIRE = DAP.NUMERODOSSIERPARTENAIRE";
            if (lastCodedap != null) {
                selectQuery += " WHERE CODEDAP > " + lastCodedap;
            }
            selectQuery += " ORDER BY DOSSIER_PARENT DESC, CODEDAP";

            final String invInsertQuery = "INSERT INTO INVOICE (ID, AMOUNT, BENEF_REFERENCE, LAST_VERSION_DATE, "
                    + "LAST_VERSION_NUMBER, INVOICE_NUMBER, PAID_AMOUNT, STATUS, BENEFICIARY_ID, OWNER_ID, PARENT_ID, "
                    + "SUB_TYPE_ID, TYPE_ID, OLD_NUMBER) "
                    + "VALUES (?, ?, NULL, sysdate, 1, ?, ?, ?, (SELECT ID FROM PARTNER WHERE CODE = ?), "
                    + "(SELECT ID FROM PARTNER WHERE CODE = ?), "
                    + "(SELECT INVOICE_ID FROM INVOICE_VERSION WHERE CODEDAP = ?), "
                    + "(SELECT ID FROM INVOICE_TYPE WHERE CODE = ?), (SELECT ID FROM INVOICE_TYPE WHERE CODE = ?), ?)";
            final String invUpdateQuery = "UPDATE INVOICE SET AMOUNT = ?, LAST_VERSION_DATE = sysdate, "
                    + "LAST_VERSION_NUMBER = ?, PAID_AMOUNT = ?, STATUS = ? WHERE ID = ?";
            final String invVersInsertQuery = "INSERT INTO INVOICE_VERSION (ID, BALANCE_AMOUNT, CODEDAP, VERSION_DATE, "
                    + "VERSION_NUMBER, PAYMENT_DATE, VERSION_AMOUNT, INVOICE_ID, PAY_CONFIRM_DATE, "
                    + "PAY_ACK_DATE, E_GUCE_REFERENCE) "
                    + "VALUES (INVOICE_VERSION_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            final String invTypeParamsQuery = "SELECT PARAMS FROM INVOICE_TYPE WHERE CODE = ?";
            final String selectInvQuery = "SELECT ID, LAST_VERSION_NUMBER, AMOUNT, PAID_AMOUNT "
                    + "FROM INVOICE WHERE INVOICE_NUMBER = ? AND TYPE_ID = (SELECT ID FROM INVOICE_TYPE WHERE CODE = ?)";
            final String montantDapQuery = "SELECT SUM(MONTANTAPAYER) FROM DOSSIERAPAYER "
                    + "WHERE NUMERODOSSIERPARTENAIRE = ? AND ETATDOSSIERPARTENAIRE = 'valide'";
            final String montantPayeDapQuery = "SELECT SUM(MONTANTAPAYER) FROM DOSSIERAPAYER "
                    + "WHERE NUMERODOSSIERPARTENAIRE = ? AND ETATDOSSIER = 'P'";
            final String selectVersAmountQuery = "SELECT VERSION_AMOUNT FROM INVOICE_VERSION WHERE INVOICE_ID = ? AND VERSION_NUMBER = ?";
            final String selectNumdapParentQuery = "SELECT NUMERODOSSIERPARTENAIRE FROM DOSSIERAPAYER WHERE CODEDAP = ?";
            final String selectInvTypeParentQuery = "SELECT CODE, PARAMS FROM INVOICE_TYPE WHERE ID = "
                    + "(SELECT TYPE_ID FROM INVOICE WHERE ID = (SELECT INVOICE_ID FROM INVOICE_VERSION WHERE CODEDAP = ?))";
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
                    final PreparedStatement selectVersAmountStatement = targetCon.prepareStatement(selectVersAmountQuery);
                    final PreparedStatement selectNumdapParentStatement = sourceCon.prepareStatement(selectNumdapParentQuery);
                    final PreparedStatement selectInvTypeParentStatement = targetCon.prepareStatement(selectInvTypeParentQuery)) {

                while (selectDapResultSet.next()) {

                    final Long parent = selectDapResultSet.getLong("DOSSIER_PARENT");
                    final String numDap = selectDapResultSet.getString("NUMERODOSSIERPARTENAIRE");
                    final long codedap = selectDapResultSet.getLong("CODEDAP");
                    final BigDecimal montantAPayer = selectDapResultSet.getBigDecimal("MONTANTAPAYER");
                    final String etatDossier = selectDapResultSet.getString("ETATDOSSIER");
                    final String typdedap = selectDapResultSet.getString("TYPEDAP");
                    final String codeBenef = selectDapResultSet.getString("CODEBENIF");

                    if ("ALCGS".equalsIgnoreCase(typdedap) || "DM601".equalsIgnoreCase(typdedap)) {
                        continue;
                    }

                    final String typedapParent;
                    if (parent == 0) {
                        invTypeParamsStatement.setString(1, typdedap);
                        try (final ResultSet resultSet = invTypeParamsStatement.executeQuery()) {
                            final Properties props = resultSet.next()
                                    ? Utils.getParams(Optional.ofNullable(resultSet.getString(1)))
                                    : new Properties();
                            balance = Boolean.parseBoolean(props.getProperty("balance", "false"));
                            typedapParent = null;
                        }
                    } else {
                        selectInvTypeParentStatement.setLong(1, parent);
                        try (final ResultSet resultSet = selectInvTypeParentStatement.executeQuery()) {
                            resultSet.next();
                            typedapParent = resultSet.getString(1);
                            final Properties props = resultSet.next()
                                    ? Utils.getParams(Optional.ofNullable(resultSet.getString(2)))
                                    : new Properties();
                            balance = Boolean.parseBoolean(props.getProperty("balance", "false"));
                        }
                    }

                    final String numdapParent;
                    if (parent == 0) {
                        // les n° des factures parentes sont sauvergardées en supprimant le suffixe PARENT s'il existe
                        selectInvStatement.setString(1, numDap.replace("PARENT", ""));
                        selectInvStatement.setString(2, typdedap);
                        numdapParent = null;
                    } else {
                        // on va chercher le numero dossier partenaire de son parent
                        selectNumdapParentStatement.setLong(1, parent);
                        try (final ResultSet resultSet = selectNumdapParentStatement.executeQuery()) {
                            resultSet.next();
                            numdapParent = resultSet.getString(1).replace("PARENT", "");
                            selectInvStatement.setString(1,
                                    getSubInvoiceNb(numdapParent,
                                            typedapParent, codeBenef));
                            selectInvStatement.setString(2, getSubInvoiceTypeCode(typedapParent, codeBenef));
                        }
                    }
                    final BigDecimal invoiceId, versionAmount, balanceAmount;
                    final int versionNumber;
                    final boolean insert;
                    try (final ResultSet resultSet = selectInvStatement.executeQuery()) {
                        if (!resultSet.next()) { // nouvelle facture
                            invoiceId = (BigDecimal) Utils.getNextId("INVOICE_SEQ");
                            versionAmount = montantAPayer;
                            balanceAmount = montantAPayer;
                            versionNumber = 0;
                            insert = true;
                        } else {
                            invoiceId = resultSet.getBigDecimal("ID");
                            versionNumber = resultSet.getInt("LAST_VERSION_NUMBER") + 1;
                            insert = false;
                            // on récupère le montant version de la version précédente
                            selectVersAmountStatement.setBigDecimal(1, invoiceId);
                            selectVersAmountStatement.setInt(2, versionNumber - 1);
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
                        if (parent == 0) {
                            invInsertStatement.setString(3, numDap.replace("PARENT", ""));
                            // pour les factures parentes le benef doit être nul
                            invInsertStatement.setString(6, codeBenef + "###");
                            invInsertStatement.setLong(8, -111111111);
                            invInsertStatement.setString(10, typdedap);
                        } else {
                            invInsertStatement.setString(3, getSubInvoiceNb(numdapParent,
                                    typedapParent, codeBenef));
                            invInsertStatement.setString(6, codeBenef);
                            invInsertStatement.setLong(8, parent);
                            invInsertStatement.setString(10, getSubInvoiceTypeCode(typedapParent, codeBenef));
                            //
                        }
                        invInsertStatement.setBigDecimal(4, paidAmount);
                        invInsertStatement.setString(5, status);
                        invInsertStatement.setString(7, selectDapResultSet.getString("CODEDO"));
                        invInsertStatement.setString(9, selectDapResultSet.getString("MENTIONS_SPECIALES"));
                        invInsertStatement.setString(11, numDap);
                        // execute
                        try {
                            invInsertStatement.executeUpdate();
                        } catch (SQLException ex) {
                            if (ex.getMessage().contains("ORA-01400") && ex.getMessage().contains("OWNER_ID")) {
                                LOGGER.log(Level.INFO, null, ex);
                                continue;
                            } else {
                                throw ex;
                            }
                        }
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
                    invVersInsertStatement.setDate(3, selectDapResultSet.getDate("DATEETATPARTENAIRE"));
                    invVersInsertStatement.setInt(4, versionNumber);
                    invVersInsertStatement.setDate(5, selectDapResultSet.getDate("DATE_PAIEMENT"));
                    invVersInsertStatement.setBigDecimal(6, versionAmount);
                    invVersInsertStatement.setBigDecimal(7, invoiceId);
                    invVersInsertStatement.setDate(8, selectDapResultSet.getDate("DATE_PAIEMENT"));
                    invVersInsertStatement.setDate(9, selectDapResultSet.getDate("DATEQUITTANCE"));
                    invVersInsertStatement.setString(10, selectDapResultSet.getString("NUMERODOSSIERGUCE"));
                    // execute
                    invVersInsertStatement.executeUpdate();

                    count++;
                    if (count == POOL) {
                        targetCon.commit();
                        count = 0;
                    }
                }
                targetCon.commit();
            }
        }
    }

    public static void createInvoiceLines() throws SQLException {

        final String selectInvQuery = "SELECT INV.ID INV_ID, INV.INVOICE_NUMBER INVOICE_NUMBER, INV.AMOUNT AMOUNT, "
                + "INV.PAID_AMOUNT PAID_AMOUNT, INV.LAST_VERSION_NUMBER LAST_VERSION_NUMBER, "
                + "INV.LAST_VERSION_DATE LAST_VERSION_DATE, INV.STATUS STATUS, IVT.CODE IVT_CODE, INV.SUB_TYPE_ID SUB_TYPE, "
                + "INV.OWNER_ID OWNER, INV.OLD_NUMBER OLD_NUMBER FROM INVOICE INV JOIN INVOICE_TYPE IVT ON INV.TYPE_ID = IVT.ID "
                + "WHERE INV.PARENT_ID IS NULL AND INV.ID NOT IN "
                + "(SELECT ID FROM INVOICE WHERE PARENT_ID IS NOT NULL)";
        final String selectDapInfQuery = "SELECT CODEBENIF FROM DOSSIERAPAYER WHERE NUMERODOSSIERPARTENAIRE = ? AND ROWNUM = 1";
        final String invInsertQuery = "INSERT INTO INVOICE (ID, AMOUNT, BENEF_REFERENCE, LAST_VERSION_DATE, "
                + "LAST_VERSION_NUMBER, INVOICE_NUMBER, PAID_AMOUNT, STATUS, BENEFICIARY_ID, OWNER_ID, PARENT_ID, "
                + "SUB_TYPE_ID, TYPE_ID, OLD_NUMBER) "
                + "VALUES (?, ?, NULL, ?, ?, ?, ?, ?, (SELECT ID FROM PARTNER WHERE CODE = ?), ?, ?, NULL, "
                + "(SELECT ID FROM INVOICE_TYPE WHERE CODE = ?), NULL)";
        final String selectInvVersQuery = "SELECT VERSION_NUMBER, VERSION_DATE, VERSION_AMOUNT, BALANCE_AMOUNT, "
                + "PAYMENT_DATE, CODEDAP FROM INVOICE_VERSION WHERE INVOICE_ID = ?";
        final String invVersInsertQuery = "INSERT INTO INVOICE_VERSION (ID, BALANCE_AMOUNT, CODEDAP, VERSION_DATE, "
                + "VERSION_NUMBER, PAYMENT_DATE, VERSION_AMOUNT, INVOICE_ID) "
                + "VALUES (INVOICE_VERSION_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";

        try (final Statement selectInvStatement = targetCon.createStatement();
                final ResultSet selectInvResultSet = selectInvStatement.executeQuery(selectInvQuery);
                final PreparedStatement selectDapInfStatement = sourceCon.prepareStatement(selectDapInfQuery);
                final PreparedStatement invInsertStatement = targetCon.prepareStatement(invInsertQuery);
                final PreparedStatement selectInvVersStatement = targetCon.prepareStatement(selectInvVersQuery);
                final PreparedStatement invVersInsertStatement = targetCon.prepareStatement(invVersInsertQuery)) {

            while (selectInvResultSet.next()) {

                final BigDecimal invoiceId = (BigDecimal) Utils.getNextId("INVOICE_SEQ");
                final BigDecimal parentId = selectInvResultSet.getBigDecimal("INV_ID");

                invInsertStatement.setBigDecimal(1, invoiceId);
                invInsertStatement.setBigDecimal(2, selectInvResultSet.getBigDecimal("AMOUNT"));
                invInsertStatement.setDate(3, selectInvResultSet.getDate("LAST_VERSION_DATE"));
                invInsertStatement.setInt(4, selectInvResultSet.getInt("LAST_VERSION_NUMBER"));

                selectDapInfStatement.setString(1, selectInvResultSet.getString("OLD_NUMBER"));
                final String invNb, ivtCode;
                try (final ResultSet resultSet = selectDapInfStatement.executeQuery()) {
                    resultSet.next();
                    final String parentIvtCode = selectInvResultSet.getString("IVT_CODE");
                    final String codeBenef = resultSet.getString(1);
                    invNb = getSubInvoiceNb(selectInvResultSet.getString("INVOICE_NUMBER"),
                            parentIvtCode, codeBenef);
                    invInsertStatement.setString(5, invNb);
                    invInsertStatement.setString(8, codeBenef);
                    ivtCode = getSubInvoiceTypeCode(parentIvtCode, codeBenef);
                    invInsertStatement.setString(11, ivtCode);
                }
                invInsertStatement.setBigDecimal(6, selectInvResultSet.getBigDecimal("PAID_AMOUNT"));
                invInsertStatement.setString(7, selectInvResultSet.getString("STATUS"));
                invInsertStatement.setBigDecimal(9, selectInvResultSet.getBigDecimal("OWNER"));
                invInsertStatement.setBigDecimal(10, parentId);

                try {
                    invInsertStatement.executeUpdate();
                } catch (SQLException ex) {
                    if (ex.getMessage().contains("ORA-00001")) {
                        LOGGER.log(Level.INFO, null, ex);
                        continue;
                    } else {
                        throw ex;
                    }
                }
                // on va recupérer toutes les version du parent pour affecter au fils
                selectInvVersStatement.setBigDecimal(1, parentId);
                try (final ResultSet selectInvVersResultSet = selectInvVersStatement.executeQuery()) {
                    while (selectInvVersResultSet.next()) {
                        invVersInsertStatement.setBigDecimal(1, selectInvVersResultSet.getBigDecimal("BALANCE_AMOUNT"));
                        invVersInsertStatement.setLong(2, selectInvVersResultSet.getLong("CODEDAP"));
                        invVersInsertStatement.setDate(3, selectInvVersResultSet.getDate("VERSION_DATE"));
                        invVersInsertStatement.setInt(4, selectInvVersResultSet.getInt("VERSION_NUMBER"));
                        invVersInsertStatement.setDate(5, selectInvVersResultSet.getDate("PAYMENT_DATE"));
                        invVersInsertStatement.setBigDecimal(6, selectInvVersResultSet.getBigDecimal("VERSION_AMOUNT"));
                        invVersInsertStatement.setBigDecimal(7, invoiceId);
                        //
                        invVersInsertStatement.executeUpdate();
                    }
                }
            }
            targetCon.commit();
        }

    }

    public static void init() throws SQLException {

        // création les types factures pour les lignes de facture
        final String selectTypedapQuery = "SELECT DISTINCT TYPEDAP FROM DOSSIERAPAYER WHERE DOSSIER_PARENT IS NULL";

        final String selectBenefQuery = "SELECT DISTINCT CODEBENIF FROM DOSSIERAPAYER WHERE (DOSSIER_PARENT "
                + "IN (SELECT CODEDAP FROM DOSSIERAPAYER WHERE TYPEDAP = ?))";

        final String selectBenefNoSubQuery = "SELECT DISTINCT CODEBENIF FROM DOSSIERAPAYER WHERE TYPEDAP = ?";

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

                    boolean hasSubInvs = selectBenefResultSet.next();
                    if (hasSubInvs) {
                        do {

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

                        } while (selectBenefResultSet.next());
                    }

                    selectBenefNoSubStatement.setString(1, typedap);

                    try (final ResultSet selectBenefNoSubResultSet = selectBenefNoSubStatement.executeQuery()) {

                        while (selectBenefNoSubResultSet.next()) {

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

    private InvoiceMigrator() {
    }

}
