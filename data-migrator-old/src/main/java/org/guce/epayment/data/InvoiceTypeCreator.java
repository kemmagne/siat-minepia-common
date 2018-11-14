package org.guce.epayment.data;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import static org.guce.epayment.Application.targetCon;

/**
 *
 * @author tadzotsa
 */
public interface InvoiceTypeCreator {

    static void create() throws SQLException {

        final String query = "INSERT INTO INVOICE_TYPE (ID, CODE, LABEL, PARAMS, SUB_TYPE, STANDALONE) VALUES (INVOICE_TYPE_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";

        try (final PreparedStatement statement = targetCon.prepareStatement(query)) {
            //
            statement.setString(1, "CUSDEC");
            statement.setString(2, "DECLARATION EN DOUANE");
            statement.setString(3, "transfer.last.status=T21\nasset.rate=15");
            statement.setBoolean(4, false);
            statement.setBoolean(5, true);
            statement.executeUpdate();
            //
            statement.setString(1, "CACCAF");
            statement.setString(2, "Redevances CACAO CAFE");
            statement.setString(3, "transfer.last.status=T21");
            statement.setBoolean(4, false);
            statement.setBoolean(5, true);
            statement.executeUpdate();
            //
            statement.setString(1, "DISGS");
            statement.setString(2, "TAXE DE VERIFICATION SGS");
            statement.setString(3, "transfer.last.status=T31");
            statement.setBoolean(4, false);
            statement.setBoolean(5, true);
            statement.executeUpdate();
            //
            statement.setString(1, "ALCGS");
            statement.setString(2, "ASSURANCE LOCATION CONTENEUR");
            statement.setString(3, "transfer.last.status=T31");
            statement.setBoolean(4, false);
            statement.setBoolean(5, true);
            statement.executeUpdate();
            //
            statement.setString(1, "CIVIO");
            statement.setString(2, "CIVIO");
            statement.setString(3, "transfer.last.status=T31");
            statement.setBoolean(4, false);
            statement.setBoolean(5, true);
            statement.executeUpdate();
            //
            statement.setString(1, "EFSD");
            statement.setString(2, "EFSD");
            statement.setString(3, "transfer.last.status=T31");
            statement.setBoolean(4, false);
            statement.setBoolean(5, true);
            statement.executeUpdate();
            //
            statement.setString(1, "EXFSD");
            statement.setString(2, "EXFSD");
            statement.setString(3, "transfer.last.status=T31");
            statement.setBoolean(4, false);
            statement.setBoolean(5, true);
            statement.executeUpdate();
            //
            statement.setString(1, "REDNAV");
            statement.setString(2, "Redevance navire");
            statement.setString(3, "transfer.last.status=T31");
            statement.setBoolean(4, false);
            statement.setBoolean(5, true);
            statement.executeUpdate();
            //
            statement.setString(1, "PVI");
            statement.setString(2, "DECLARATIONS SOUMISES AU PVI");
            statement.setString(3, null);
            statement.setBoolean(4, true);
            statement.setBoolean(5, false);
            statement.executeUpdate();
            //
            statement.setString(1, "NPVI");
            statement.setString(2, "DECLARATIONS HORS PVI");
            statement.setString(3, null);
            statement.setBoolean(4, true);
            statement.setBoolean(5, false);
            statement.executeUpdate();

            //
            targetCon.commit();
        }
    }

}
