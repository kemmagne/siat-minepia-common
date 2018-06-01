package org.guce.epayment.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static org.guce.epayment.data.Application.sourceCon;
import static org.guce.epayment.data.Application.targetCon;

/**
 *
 * @author tadzotsa
 */
public class TOMigrator {

    public static void migrate() throws SQLException {

        try (final Statement statement = targetCon.createStatement();
                final ResultSet resultSet = statement
                        .executeQuery("SELECT OLD_TRANSFER_NUMBER FROM TRANSFER_ORDER ORDER BY OLD_TRANSFER_NUMBER DESC")) {

            String dernierNumVirement = null;
            if (resultSet.next()) {
                dernierNumVirement = resultSet.getString(1);
            }

            String selectQuery = "SELECT NUMEROVIREMENT, CODEMODEPAIEMENT, IDDONNEURORDRE, IDBENIFICIAIRE, "
                    + "MONTANTAPAYERCHIFFRE, NUMCOMPTEBENIF, CLERIBBENIF, CODEBANQUEDO, CODEAGENCEDO, CODEBANQUEBENIF, CODEAGENCEBENIF, LOGINAGENT, "
                    + "DATEACCUSE, NCONTRIBUABLE, DATECREATIONORDRE, DATECLOTUREORDRE, DATESIGNATURE, DATEORDRESEANCE FROM ORDREVIREMENTDO";
            if (null != dernierNumVirement) {
                selectQuery += " WHERE NUMEROVIREMENT > " + dernierNumVirement;
            }
            selectQuery += " ORDER BY NUMEROVIREMENT";

//            final String selectTo
            try (final Statement selectStatement = sourceCon.createStatement();
                    final ResultSet selectResultSet = selectStatement.executeQuery(selectQuery)) {

            }
        }
    }

}
