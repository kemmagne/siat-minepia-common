package org.guce.epayment.data;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import static org.guce.epayment.Application.targetCon;

/**
 *
 * @author tadzotsa
 */
public interface PaymentModeCreator {

    static void create() throws SQLException {

        final String query = "INSERT INTO PAYMENT_MODE (ID, CODE, LABEL, GLOBAL_FLOW, DIRECT) VALUES (PAYMENT_MODE_SEQ.NEXTVAL, ?, ?, ?, ?)";
        try (final PreparedStatement statement = targetCon.prepareStatement(query)) {
            //
            statement.setString(1, "ASSET");
            statement.setString(2, "Apurement d'avoir");
            statement.setString(3, "A11");
            statement.setBoolean(4, false);
            statement.executeUpdate();
            //
            statement.setString(1, "CAMPOST_WALLET");
            statement.setString(2, "Paiement Par Campost Wallet");
            statement.setString(3, null);
            statement.setBoolean(4, true);
            statement.executeUpdate();
            //
            statement.setString(1, "E_TRANSFER");
            statement.setString(2, "Paiement par ordre de virement électronique");
            statement.setString(3, "T11,T21,T31");
            statement.setBoolean(4, false);
            statement.executeUpdate();
            //
            statement.setString(1, "BANK_TRANSFER");
            statement.setString(2, "Payment par virement bancaire");
            statement.setString(3, "T21,T31");
            statement.setBoolean(4, false);
            statement.executeUpdate();
            //
            statement.setString(1, "COUNTER_TRANSFER");
            statement.setString(2, "Payment par ordre de virement au guichet");
            statement.setString(3, "T21,T31");
            statement.setBoolean(4, false);
            statement.executeUpdate();
            //
            statement.setString(1, "CASH");
            statement.setString(2, "Paiement en espèces");
            statement.setString(3, "T21,T31");
            statement.setBoolean(4, false);
            statement.executeUpdate();
            //
            statement.setString(1, "CHECK");
            statement.setString(2, "Paiement par chèque");
            statement.setString(3, "T21,T31");
            statement.setBoolean(4, false);
            statement.executeUpdate();

            //
            targetCon.commit();
        }
    }

}
