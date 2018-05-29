package org.guce.epayment.data;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.guce.epayment.data.Application.sourceCon;
import static org.guce.epayment.data.Application.targetCon;
import org.guce.epayment.data.util.Utils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author tadzotsa
 */
public class UserMigrator {

    private static final Logger LOGGER = Logger.getLogger(AccountMigrator.class.getName());

    public static void migrate() throws SQLException {

        final String sequence = "USER_SEQ";
        final String insertQuery = "INSERT INTO USERS (ID, LOGIN, TITLE, LOCALE, FIRST_NAME, LAST_NAME, EMAIL, "
                + "RESET_PASSWORD, LOCKED, ACTIVE, CREATION_DATE, PARTNER_ID) VALUES "
                + "(?, ?, 'M.', 'fr', ?, ?, 'email@email.com', 1, 0, 1, sysdate, (SELECT ID FROM PARTNER WHERE CODE LIKE ? AND ROWNUM = 1))";
        final String selectQuery = "SELECT LOGINUSER, NOMUSER, PRENOMUSER, IDCLIENT, TYPEUSER FROM CONNEXION "
                + "ORDER BY TYPEUSER";

        try (final Statement selectStatement = sourceCon.createStatement();
                final ResultSet selectResultSet = selectStatement.executeQuery(selectQuery);
                final PreparedStatement insertStatement = targetCon.prepareStatement(insertQuery)) {

            while (selectResultSet.next()) {

                final String typeUser = selectResultSet.getString("TYPEUSER");

                if (typeUser == null) {
                    continue;
                }

                final BigDecimal userId = (BigDecimal) Utils.getNextId(sequence);
                final String loginUser = selectResultSet.getString("LOGINUSER");
                final String nomUser = selectResultSet.getString("NOMUSER");

                insertStatement.setBigDecimal(1, userId);
                insertStatement.setString(2, loginUser);
                insertStatement.setString(3, selectResultSet.getString("PRENOMUSER"));
                insertStatement.setString(4, nomUser != null ? nomUser : loginUser);

                if ("BANQUEAGENT".equalsIgnoreCase(typeUser) || "BANKAGENT".equalsIgnoreCase(typeUser)) {
                    insertStatement.setString(5, selectResultSet.getString("IDCLIENT") + "#%");
                } else if ("ADMIN".equalsIgnoreCase(typeUser)) {
                    insertStatement.setString(5, "GUCE");
                } else {
                    insertStatement.setString(5, selectResultSet.getString("IDCLIENT"));
                }

                try {
                    insertStatement.executeUpdate();
                } catch (SQLException ex) {
                    if (!ex.getMessage().contains("ORA-01400")) {
                        throw ex;
                    } else {
                        LOGGER.log(Level.INFO, "impossible d'insérer une colonne nulle", ex);
                        continue;
                    }
                }

                associateUserToRole(userId, typeUser);
                createUserCrentials(userId);
            }
            targetCon.commit();
        }
    }

    private static void associateUserToRole(BigDecimal userId, String sourceRoleName) throws SQLException {

        final Map<String, String> map = new HashMap<>();

        map.put("ADMIN", "ADMIN");
        map.put("BANQUEAGENT", "CAISSIER");
        map.put("BANKAGENT", "CAISSIER");
        map.put("BANQUECONTROL", "CONTROLEUR_BANQUE");
        map.put("BENIF", "BENEFICIAIRE");
        map.put("DECIDEUR", "DECIDEUR");
        map.put("DO", "DONNEUR_ORDRE");
        map.put("ENTERPRISE_DO", "DONNEUR_ORDRE");
        map.put("ENTERPRISE_AUDIT", "AUDITEUR_DONNEUR_ORDRE");
        map.put("TPG", "DECIDEUR");
        map.put("CSD", "DECIDEUR");
        map.put("MANAGER", "AUDITEUR_BANQUE");

        final String query = "INSERT INTO USER_ROLE (USER_ID, ROLE_ID) "
                + "VALUES (?, (SELECT ID FROM ROLES WHERE NAME = ?))";

        try (final PreparedStatement insertStatement = targetCon.prepareStatement(query)) {
            //
            insertStatement.setBigDecimal(1, userId);
            insertStatement.setString(2, map.get(sourceRoleName));

            insertStatement.executeUpdate();
        }
    }

    private static void createUserCrentials(BigDecimal userId) throws SQLException {

        final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        final String password = "0123456789";

        final String query = "INSERT INTO CREDENTIALS (ID, OWNER_ID, PASSWORD, SALT, CREATION_DATE) "
                + "VALUES (CREDENTIALS_SEQ.NEXTVAL, ?, ?, NULL, sysdate)";

        try (final PreparedStatement insertStatement = targetCon.prepareStatement(query)) {
            //
            insertStatement.setBigDecimal(1, userId);
            insertStatement.setString(2, passwordEncoder.encode(password));

            insertStatement.executeUpdate();
        }
    }

    private UserMigrator() {
    }

}
