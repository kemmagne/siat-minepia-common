package org.guce.epayment.data;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import static org.guce.epayment.data.Application.targetCon;

/**
 *
 * @author tadzotsa
 */
public interface RoleCreator {

    static void create() throws SQLException {

        final String query = "INSERT INTO ROLES (ID, NAME, DESCRIPTION) VALUES (ROLE_SEQ.NEXTVAL, ?, ?)";
        try (final PreparedStatement statement = targetCon.prepareStatement(query)) {
            //
            statement.setString(1, "ADMIN");
            statement.setString(2, "Administrateur de l'application");
            statement.executeUpdate();
            //
            statement.setString(1, "DONNEUR_ORDRE");
            statement.setString(2, "Donneur d'ordre");
            statement.executeUpdate();
            //
            statement.setString(1, "CONTROLEUR_DONNEUR_ORDRE");
            statement.setString(2, "Controleur Donneur d'ordre");
            statement.executeUpdate();
            //
            statement.setString(1, "AUDITEUR_DONNEUR_ORDRE");
            statement.setString(2, "Auditeur Donneur d'ordre");
            statement.executeUpdate();
            //
            statement.setString(1, "CAISSIER");
            statement.setString(2, "Caissier");
            statement.executeUpdate();
            //
            statement.setString(1, "CONTROLEUR_AGENCE");
            statement.setString(2, "Controleur Agence");
            statement.executeUpdate();
            //
            statement.setString(1, "CONTROLEUR_BANQUE");
            statement.setString(2, "Controleur Banque");
            statement.executeUpdate();
            //
            statement.setString(1, "AUDITEUR_BANQUE");
            statement.setString(2, "Auditeur Banque");
            statement.executeUpdate();
            //
            statement.setString(1, "BENEFICIAIRE");
            statement.setString(2, "Bénéficiaire");
            statement.executeUpdate();
            //
            statement.setString(1, "DECIDEUR");
            statement.setString(2, "Décideur");
            statement.executeUpdate();

            //
            targetCon.commit();
        }
    }

}
