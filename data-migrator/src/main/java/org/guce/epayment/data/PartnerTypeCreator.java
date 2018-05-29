package org.guce.epayment.data;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import static org.guce.epayment.data.Application.targetCon;

/**
 *
 * @author tadzotsa
 */
public interface PartnerTypeCreator {

    static void create() throws SQLException {

        final String query = "INSERT INTO PARTNER_TYPE (ID, CODE, LABEL) VALUES (PARTNER_TYPE_SEQ.NEXTVAL, ?, ?)";

        try (final PreparedStatement statement = targetCon.prepareStatement(query)) {
            //
            statement.setString(1, "DONNEUR_ORDRE");
            statement.setString(2, "Partenaire Donneur d'ordre");
            statement.executeUpdate();
            //
            statement.setString(1, "BENEFICIAIRE");
            statement.setString(2, "Partenaire Bénéficiaire");
            statement.executeUpdate();
            //
            statement.setString(1, "BANQUE");
            statement.setString(2, "Partenaire Banque");
            statement.executeUpdate();
            //
            statement.setString(1, "AGENCE_BANQUE");
            statement.setString(2, "Partenaire Agence Banque");
            statement.executeUpdate();
            //
            statement.setString(1, "ADMIN");
            statement.setString(2, "Partenaire Administrateur");
            statement.executeUpdate();

            //
            targetCon.commit();
        }
    }

}
