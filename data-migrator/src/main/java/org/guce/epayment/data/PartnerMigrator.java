package org.guce.epayment.data;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import static org.guce.epayment.Application.POOL;
import static org.guce.epayment.Application.SEPARATOR;
import static org.guce.epayment.Application.sourceCon;
import static org.guce.epayment.Application.targetCon;
import org.guce.epayment.data.util.Utils;
import org.springframework.util.StringUtils;

/**
 *
 * @author tadzotsa
 */
public class PartnerMigrator {

    public static void migrate(final String partnerType) throws SQLException {

        final Map<Integer, String> map = new HashMap<>();

        switch (partnerType) {

            case "BANQUE":

                map.put(2, "CODEBANQUE");
                map.put(3, "LIBELLEBANQUE");
                map.put(4, "FAXBANQUE");
                map.put(5, "TELEPHONEBANQUE");
                map.put(6, "EMAILBANQUE");
                map.put(7, "VILLEBANQUE");
                map.put(8, "BOITEPOSTALEBANQUE");
                map.put(9, "ADRESSEBANQUE");
                map.put(10, "CODEBANQUE");

                migrate("BANQUE", map, partnerType);
                break;

            case "BENEFICIAIRE":

                map.put(2, "NUMCONTRIBUABLE");
                map.put(3, "NOMBENIFICIAIRE");
                map.put(4, "FAXBENIFICIAIRE");
                map.put(5, "TELEPHONEBENIFICIAIRE");
                map.put(6, "EMAILBENIFICIAIRE");
                map.put(7, "VILLEBENIFICIAIRE");
                map.put(8, "BOITEPOSTALBENIFICIAIRE");
                map.put(9, "ADRESSEBENIFICIAIRE");
                map.put(10, "NUMCONTRIBUABLE");

                migrate("BENIFICIAIRE", map, partnerType);
                break;

            case "DONNEUR_ORDRE":

                map.put(2, "NUMCONTRIBUABLE");
                map.put(3, "NOMDO");
                map.put(4, "FAXDO");
                map.put(5, "TELEPHONEDO");
                map.put(6, "EMAILDO");
                map.put(7, "VILLEDO");
                map.put(8, "BOITEPOSTALEDO");
                map.put(9, "ADRESSEDO");
                map.put(10, "NUMCONTRIBUABLE");

                migrate("DONNEURORDRE", map, partnerType);
        }
    }

    private static void migrate(final String table, final Map<Integer, String> map, final String partnerType) throws SQLException {

        final String sequence = "PARTNER_SEQ";
        final String insertQuery = "INSERT INTO PARTNER "
                + "(ID, CODE, NAME, FAX, PHONE, EMAIL, CITY, PO_BOX, ADDRESS, TAX_PAYER_NUMBER)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        final String selectQuery = String.format("SELECT %s FROM %s",
                StringUtils.collectionToCommaDelimitedString(map.values()), table);
        try (final Statement selectStatement = sourceCon.createStatement();
                final ResultSet selectResultSet = selectStatement.executeQuery(selectQuery);
                final PreparedStatement insertStatement = targetCon.prepareStatement(insertQuery)) {

            int count = 0;
            String code = null;

            while (selectResultSet.next()) {

                final BigDecimal partnerId = (BigDecimal) Utils.getNextId(sequence);

                insertStatement.setBigDecimal(1, partnerId);
                //
                for (Map.Entry<Integer, String> entry : map.entrySet()) {

                    final Integer key = entry.getKey();
                    final String value = entry.getValue();

                    if ("BENEFICIAIRE".equals(partnerType) && key.equals(2)) {
                        code = value;
                    }

                    insertStatement.setString(key, selectResultSet.getString(value));
                }

                try {
                    insertStatement.executeUpdate();
                } catch (SQLException ex) {
                    if (!ex.getMessage().contains("ORA-00001")) {
                        throw ex;
                    } else {
                        continue;
                    }
                }

                associatePartnerToType(partnerId, partnerType);

                // GUCE doit être partenaire admin
                if (Objects.equals("GUCE", code)) {
                    associatePartnerToType(partnerId, "ADMIN");
                }

                count++;
                if (count == POOL) {
                    targetCon.commit();
                    count = 0;
                }
            }
            targetCon.commit();
        }

    }

    public static void migrateBankAgencies() throws SQLException {

        final String sequence = "PARTNER_SEQ";
        final String insertQuery = "INSERT INTO PARTNER "
                + "(ID, CODE, NAME, FAX, PHONE, EMAIL, CITY, PO_BOX, ADDRESS, TAX_PAYER_NUMBER, PARENT_ID)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, (SELECT ID FROM PARTNER WHERE CODE = ?))";

        final String selectQuery = "SELECT CODEAGENCE, CODEBANQUE, LIBELLEAGENCE, ADRESSEAGENCE, VILLEAGENCE, "
                + "BOITEPOSTALEAGENCE, TELEPHONEAGENCE, FAXAGENCE, EMAILAGENCE FROM AGENCE";

        try (final PreparedStatement insertStatement = targetCon.prepareStatement(insertQuery);
                final Statement selectStatement = sourceCon.createStatement();
                final ResultSet selectResultSet = selectStatement.executeQuery(selectQuery)) {

            while (selectResultSet.next()) {

                final BigDecimal partnerId = (BigDecimal) Utils.getNextId(sequence);

                insertStatement.setBigDecimal(1, partnerId);

                final String bankCode = selectResultSet.getString("CODEBANQUE");
                final String agencyCode = bankCode + SEPARATOR + selectResultSet.getString("CODEAGENCE");

                insertStatement.setString(2, agencyCode);
                insertStatement.setString(3, selectResultSet.getString("LIBELLEAGENCE"));
                insertStatement.setString(4, selectResultSet.getString("FAXAGENCE"));
                insertStatement.setString(5, selectResultSet.getString("TELEPHONEAGENCE"));
                insertStatement.setString(6, selectResultSet.getString("EMAILAGENCE"));
                insertStatement.setString(7, selectResultSet.getString("VILLEAGENCE"));
                insertStatement.setString(8, selectResultSet.getString("BOITEPOSTALEAGENCE"));
                insertStatement.setString(9, selectResultSet.getString("ADRESSEAGENCE"));
                insertStatement.setString(10, agencyCode);
                insertStatement.setString(11, bankCode);

                insertStatement.executeUpdate();

                associatePartnerToType(partnerId, "AGENCE_BANQUE");
            }
            targetCon.commit();
        }
    }

    private static void associatePartnerToType(BigDecimal partnerId, String typeCode) throws SQLException {

        final String query = "INSERT INTO PARTNER_PARTNER_TYPE (PARTNER_ID, PARTNER_TYPE_ID) "
                + "VALUES (?, (SELECT ID FROM PARTNER_TYPE WHERE CODE = ?))";

        try (final PreparedStatement statement = targetCon.prepareStatement(query)) {
            //
            statement.setBigDecimal(1, partnerId);
            statement.setString(2, typeCode);

            try {
                statement.executeUpdate();
            } catch (SQLException ex) {
                if (!ex.getMessage().contains("ORA-00001")) {
                    throw ex;
                }
            }
        }
    }

    private PartnerMigrator() {
    }

}
