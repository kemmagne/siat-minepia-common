package org.guce.epayment.data.util;

import java.io.IOException;
import java.io.StringReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;
import org.guce.epayment.data.Application;

/**
 *
 * @author tadzotsa
 */
public interface Utils {

    static final int UNIQUE_VALUE_LENGTH = 10;

    static Number getNextId(String sequence) throws SQLException {

        final String query = "SELECT " + sequence + ".NEXTVAL FROM DUAL";

        try (final Statement statement = Application.targetCon.createStatement()) {

            final ResultSet resultSet = statement.executeQuery(query);

            resultSet.next();

            return (Number) resultSet.getObject(1);
        }
    }

    static String generateUniqueValue(String sequence, String prefix) throws SQLException {

        final Number nextId = getNextId(sequence);

        return new DecimalFormat(prefix + StringUtils.repeat("0", UNIQUE_VALUE_LENGTH)).format(nextId);
    }

    static Properties getParams(final Optional<String> paramsString) {

        if (!paramsString.isPresent()) {
            return new Properties();
        }

        try {

            final Properties props = new Properties();

            props.load(new StringReader(paramsString.get()));

            return props;
        } catch (IOException ex) {

//            LOGGER.error(null, ex);
            return new Properties();
        }
    }

}
