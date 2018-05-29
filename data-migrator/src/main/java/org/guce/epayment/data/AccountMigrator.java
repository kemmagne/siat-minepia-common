package org.guce.epayment.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.guce.epayment.data.Application.SEPARATOR;
import static org.guce.epayment.data.Application.sourceCon;
import static org.guce.epayment.data.Application.targetCon;

/**
 *
 * @author tadzotsa
 */
public class AccountMigrator {

    private static final Logger LOGGER = Logger.getLogger(AccountMigrator.class.getName());

    public static void migrate() throws SQLException {
        migrate("COMPTEBENIF", "IDBENIFICIAIRE");
        migrate("COMPTEBANQUEDO", "IDCLIENTBANQUE");
    }

    private static void migrate(String table, String ownerCol) throws SQLException {

        final String selectQuery = String.format("SELECT ACTIF, CLERIBCOMPTE, CODEAGENCE, CODEBANQUE, %s, IDCOMPTE, "
                + "LIBELLECOMPTE, NUMEROCOMPTE, SOLDECOMPTE FROM %s", ownerCol, table);
        final String insertQuery = "INSERT INTO BANK_ACCOUNT (ID, ACTIVE, BALANCE, ACCOUNT_KEY, LABEL, ACCOUNT_NUMBER, "
                + "OLD_ID, AGENCY_ID, BANK_ID, OWNER_ID) VALUES "
                + "(BANK_ACCOUNT_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, (SELECT ID FROM PARTNER WHERE CODE = ?), "
                + "(SELECT ID FROM PARTNER WHERE CODE = ?), (SELECT ID FROM PARTNER WHERE CODE = ?))";

        try (final Statement selectStatement = sourceCon.createStatement();
                final ResultSet selectResultSet = selectStatement.executeQuery(selectQuery);
                final PreparedStatement insertStatement = targetCon.prepareStatement(insertQuery)) {

            while (selectResultSet.next()) {

                final Object state = selectResultSet.getObject("ACTIF");

                insertStatement.setBoolean(1, state != null && new Integer(1).equals(new Integer(state.toString())));
                insertStatement.setBigDecimal(2, selectResultSet.getBigDecimal("SOLDECOMPTE"));
                insertStatement.setString(3, selectResultSet.getString("CLERIBCOMPTE"));
                insertStatement.setString(4, selectResultSet.getString("LIBELLECOMPTE"));
                insertStatement.setString(5, selectResultSet.getString("NUMEROCOMPTE"));
                insertStatement.setBigDecimal(6, selectResultSet.getBigDecimal("IDCOMPTE"));

                final String codeBanque = selectResultSet.getString("CODEBANQUE");

                insertStatement.setString(7, codeBanque + SEPARATOR + selectResultSet.getString("CODEAGENCE"));
                insertStatement.setString(8, codeBanque);
                insertStatement.setString(9, selectResultSet.getString(ownerCol));

                try {
                    insertStatement.executeUpdate();
                } catch (SQLException ex) {
                    if (!ex.getMessage().contains("ORA-01400")) {
                        throw ex;
                    } else {
                        LOGGER.log(Level.INFO, "impossible d'insérer une colonne nulle", ex);
                    }
                }
            }
            targetCon.commit();
        }
    }

    private AccountMigrator() {
    }

}
