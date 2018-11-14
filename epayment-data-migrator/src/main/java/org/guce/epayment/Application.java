package org.guce.epayment;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.TimeZone;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Assert;

/**
 *
 * @author tadzotsa
 */
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static String CONFIG_FILE_PATH = "db.properties";

    public static String ARG;

    public static String DB_SOURCE_URL;
    public static String DB_SOURCE_USER;
    public static String DB_SOURCE_PASSWORD;

    public static String DB_TARGET_URL;
    public static String DB_TARGET_USER;
    public static String DB_TARGET_PASSWORD;

    public static Connection SOURCE_CONNECTION;
    public static Connection TARGET_CONNECTION;

    public static final String SEPARATOR = "#";

    public static int POOL;

    public static void main(String[] args) throws Exception {

        final int nbArgs = args.length;
        if (nbArgs == 1 || nbArgs == 2) {
            ARG = args[0].trim();
            if (nbArgs == 2) {
                CONFIG_FILE_PATH = args[1];
            }
            init();
        } else {
            LOGGER.debug("Paramètres absents ou incorrets");
            return;
        }

        final ArgCode argCode = ArgCode.valueOf(ARG);
        switch (argCode) {
            case INIT:
                initBD();
                break;
            case RESET:
                resetBD();
                break;
        }

        LOGGER.info("closing connections...");
        closeConnections();
    }

    private static void init() throws Exception {

        final Properties properties = new Properties();

        properties.load(new FileInputStream(CONFIG_FILE_PATH));

        DB_SOURCE_URL = properties.getProperty("source.db.url");
        DB_SOURCE_USER = properties.getProperty("source.db.username");
        DB_SOURCE_PASSWORD = properties.getProperty("source.db.password");

        DB_TARGET_URL = properties.getProperty("target.db.url");
        DB_TARGET_USER = properties.getProperty("target.db.username");
        DB_TARGET_PASSWORD = properties.getProperty("target.db.password");

        POOL = Integer.parseInt(properties.getProperty("pool"));

        Class.forName("oracle.jdbc.driver.OracleDriver");

        final TimeZone timeZone = TimeZone.getTimeZone("GMT+1");
        TimeZone.setDefault(timeZone);

        SOURCE_CONNECTION = DriverManager.getConnection(DB_SOURCE_URL, DB_SOURCE_USER, DB_SOURCE_PASSWORD);
        TARGET_CONNECTION = DriverManager.getConnection(DB_TARGET_URL, DB_TARGET_USER, DB_TARGET_PASSWORD);

        validateConnections();

        TARGET_CONNECTION.setAutoCommit(false);

        LOGGER.info("Connections have been estabilished...");
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
                final Statement statement = TARGET_CONNECTION.createStatement();
                statement.executeUpdate(line);
            } catch (SQLException ex) {
                if (ex.getErrorCode() != 955) {
                    throw ex;
                }
            }
        }

        LOGGER.info("target database has been initialized");
    }

    private static void resetBD() throws SQLException {

        final String selectQuery = "SELECT 'DROP '||OBJECT_TYPE||' '||OBJECT_NAME|| DECODE(OBJECT_TYPE,'TABLE',' CASCADE CONSTRAINTS','') FROM USER_OBJECTS";
        //
        try (Statement selectStatement = TARGET_CONNECTION.createStatement()) {
            ResultSet selectResultSet = selectStatement.executeQuery(selectQuery);
            String dropQuery;
            Statement dropStatement = TARGET_CONNECTION.createStatement();
            while (selectResultSet.next()) {
                dropQuery = selectResultSet.getString(1);
                if (dropQuery.contains("DROP LOB ") || dropQuery.contains("DROP INDEX ")) {
                    continue;
                }
                dropStatement.executeUpdate(dropQuery);
                dropStatement.executeUpdate("PURGE RECYCLEBIN");
            }
        }

        LOGGER.info("target database has been reset");
    }

    private static void validateConnections() {
        Assert.notNull(SOURCE_CONNECTION, "source connection can't be null");
        Assert.notNull(TARGET_CONNECTION, "target connection can't be null");
    }

    private static void closeConnections() throws SQLException {
        SOURCE_CONNECTION.close();
        TARGET_CONNECTION.close();
    }

}
