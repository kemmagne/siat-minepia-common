package org.guce.epayment.core.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author tadzotsa
 */
public interface CoreUtils {

    static final Logger LOGGER = LoggerFactory.getLogger(CoreUtils.class);

    /**
     *
     * @param actual
     * @param flow
     * @param sep the separator
     *
     * @return the next element in flow
     */
    static Optional<String> findNext(final String actual, final String flow, final String sep) {

        final List<String> list = Arrays.asList(StringUtils.split(flow, sep));

        return Optional.ofNullable((list.indexOf(actual) + 1 < list.size()) ? list.get(list.indexOf(actual) + 1) : null);
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

            LOGGER.error(null, ex);

            return new Properties();
        }
    }

    static String extractDataFromCharacterData(final String cdata) {
        return cdata.split("![CDATA[|]]")[0];
    }

    /**
     * Compare la position de step1 par rapport à step2 dans flow
     *
     * @param flow
     * @param step1
     * @param step2
     * @param sep
     *
     * @return 0 si les deux on la même position ou n'y sont pas, un nombre
     * positif si step1 est après et un nombre négafif si step1 est avant
     */
    static int compare(final String flow, final String step1, final String step2, final String sep) {

        final List<String> list = Arrays.asList(StringUtils.split(flow, sep));

        return list.indexOf(step1) - list.indexOf(step2);
    }

    static String mapToString(final Map<String, String> map, final String joinChar) {

        final StringBuilder builder = new StringBuilder();

        map.entrySet().forEach(entry -> {
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            builder.append(String.format("%s=%s%s", key, value, joinChar));
        });

        return builder.substring(0, builder.lastIndexOf(joinChar));
    }

    static ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

}
