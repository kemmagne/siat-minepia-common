package org.guce.epayment.data;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import static org.guce.epayment.data.Application.targetCon;

/**
 *
 * @author tadzotsa
 */
public interface StepCreator {

    static void create() throws SQLException {

        final String query = "INSERT INTO STEP (ID, CODE, LABEL) VALUES (STEP_SEQ.NEXTVAL, ?, ?)";

        try (final PreparedStatement statement = targetCon.prepareStatement(query)) {
            //
            statement.setString(1, "A11");
            statement.setString(2, "Apurement d'avoir");
            statement.executeUpdate();
            //
            statement.setString(1, "CANCELED");
            statement.setString(2, "Paiement Annulé");
            statement.executeUpdate();
            //
            statement.setString(1, "REJECTED");
            statement.setString(2, "Paiement Rejété");
            statement.executeUpdate();
            //
            statement.setString(1, "T11");
            statement.setString(2, "Validation Donneur d'ordre");
            statement.executeUpdate();
            //
            statement.setString(1, "T21");
            statement.setString(2, "Validation Banque Donneur d'ordre");
            statement.executeUpdate();
            //
            statement.setString(1, "T31");
            statement.setString(2, "Validation Banque Bénéficiaire");
            statement.executeUpdate();
            //
            statement.setString(1, "T41");
            statement.setString(2, "Validation Bénéficiaire");
            statement.executeUpdate();

            //
            targetCon.commit();
        }
    }

}
