package org.guce.epayment.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Assert;

public class Application {

    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    public static String ORACLE_JDBC_DRIVER;

    public static String DB_SOURCE_URL;
    public static String DB_SOURCE_USER;
    public static String DB_SOURCE_PASSWORD;

    public static String DB_TARGET_URL;
    public static String DB_TARGET_USER;
    public static String DB_TARGET_PASSWORD;

    public static Connection sourceCon;
    public static Connection targetCon;

    public static final String SEPARATOR = "#";

    public static final int POOL = 100;

    static final String SUB_INVOICE_TYPE_CODE_FORMAT = "FACTURE_%s_%s";

    static final String SUB_INVOICE_NUMBER_FORMAT = "%s_%s_%s";

    public static void main(String[] args) throws Exception {
        run(args);
    }

    public static void run(String... args) throws Exception {

        try {

            try {

                final Properties properties = new Properties();

                properties.load(new ClassPathResource("db.properties").getInputStream());

                ORACLE_JDBC_DRIVER = properties.getProperty("driver-class-name");

                DB_SOURCE_URL = properties.getProperty("source.db.url");
                DB_SOURCE_USER = properties.getProperty("source.db.username");
                DB_SOURCE_PASSWORD = properties.getProperty("source.db.password");

                DB_TARGET_URL = properties.getProperty("target.db.url");
                DB_TARGET_USER = properties.getProperty("target.db.username");
                DB_TARGET_PASSWORD = properties.getProperty("target.db.password");
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }

            System.out.println("-------- Oracle JDBC Connection Testing ------");
            try {
                Class.forName(ORACLE_JDBC_DRIVER);
            } catch (ClassNotFoundException ex) {
                LOGGER.log(Level.SEVERE, "Unable to find Oracle JDBC Driver...", ex);
                return;
            }
            System.out.println("Oracle JDBC Driver Registered!");

            try {
                TimeZone timeZone = TimeZone.getTimeZone("GMT+1");
                TimeZone.setDefault(timeZone);
                sourceCon = DriverManager.getConnection(DB_SOURCE_URL, DB_SOURCE_USER, DB_SOURCE_PASSWORD);
                targetCon = DriverManager.getConnection(DB_TARGET_URL, DB_TARGET_USER, DB_TARGET_PASSWORD);
                targetCon.setAutoCommit(false);
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, "Connection(s) Failed! Check output console...", ex);
                return;
            }
            System.out.println("Connections have been estabilished...");
            validateConnections();

//            resetBD();
//            initBD();
//            StepCreator.create();
//            PaymentModeCreator.create();
//            RoleCreator.create();
//            PartnerTypeCreator.create();
//            InvoiceTypeCreator.create();
//            PartnerMigrator.migrate("BENEFICIAIRE");
//            PartnerMigrator.migrate("BANQUE");
//            PartnerMigrator.migrateBankAgencies();
//            PartnerMigrator.migrate("DONNEUR_ORDRE");
//            UserMigrator.migrate();
//            AccountMigrator.migrate();
//
//            InvoiceMigrator.init();
//            InvoiceMigrator.migrate();
//            InvoiceMigrator.createInvoiceLines();
//
//            TOMigrator.migrate();
            //
            closeConnections();
        } catch (SQLException ex) {
            targetCon.rollback();
            closeConnections();
            throw ex;
        }
    }

    private static void resetBD() throws SQLException {

        final String selectQuery = "SELECT 'DROP '||OBJECT_TYPE||' '||OBJECT_NAME|| DECODE(OBJECT_TYPE,'TABLE',' CASCADE CONSTRAINTS','') FROM USER_OBJECTS";
        //
        try (Statement selectStatement = targetCon.createStatement()) {
            ResultSet selectResultSet = selectStatement.executeQuery(selectQuery);
            String dropQuery;
            Statement dropStatement = targetCon.createStatement();
            while (selectResultSet.next()) {
                dropQuery = selectResultSet.getString(1);
//                System.out.println(dropQuery);
                if (dropQuery.contains("DROP LOB ") || dropQuery.contains("DROP INDEX ")) {
                    continue;
                }
                dropStatement.executeUpdate(dropQuery);
                dropStatement.executeUpdate("PURGE RECYCLEBIN");
            }
        }
    }

    private static void validateConnections() {
        Assert.notNull(sourceCon, "source connection can't be null");
        Assert.notNull(targetCon, "target connection can't be null");
    }

    private static void closeConnections() throws SQLException {
        sourceCon.close();
        targetCon.close();
    }

    private static void initBD() throws Exception {

        final BufferedReader br = new BufferedReader(new FileReader(new ClassPathResource("create_sequences.sql")
                .getFile()));

        String line;
        while ((line = br.readLine()) != null) {

            if (StringUtils.isNotBlank(line)) {
                line = line.substring(0, line.length() - 1);
            }

            if (StringUtils.isBlank(line)) {
                continue;
            }

            try {
                final Statement statement = targetCon.createStatement();
                statement.executeUpdate(line);
            } catch (SQLException ex) {
                if (!ex.getMessage().contains("ORA-00955")) {
                    throw ex;
                }
            }
        }
    }
}
