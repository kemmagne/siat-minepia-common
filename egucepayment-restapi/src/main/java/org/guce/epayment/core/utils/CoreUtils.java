package org.guce.epayment.core.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;
import org.guce.util.CipherUtils;
import org.guce.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

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

    static String getToSendMessage(final byte[] message, final String receiverPrefix) {

        try {
            final Properties properties = new Properties();

            properties.load(new ClassPathResource("global-config.properties").getInputStream());

            final String secretKey = properties.getProperty("secret.key");
            final String cipheredMessage = CipherUtils.aesEncrypt(message, secretKey);
            final String privateKey = FileUtil
                    .getFileContent(new ClassPathResource(properties.getProperty("private.key.file"))
                            .getFile());
            final String signature = CipherUtils.sign(message, privateKey);
            final String receiverPublicKey = FileUtil
                    .getFileContent(new ClassPathResource(properties.getProperty(receiverPrefix + ".public.key.file"))
                            .getFile());
            final String cipheredSecretKey = CipherUtils.rsaEncrypt(receiverPublicKey, secretKey);
            final ObjectMapper mapper = new ObjectMapper();
            final JsonNode rootNode = mapper.createObjectNode();

            ((ObjectNode) rootNode).put("CLE_SECRETE_CHIFFREE", cipheredSecretKey);
            ((ObjectNode) rootNode).put("MESSAGE_ORIGINE_CHIFFRE", cipheredMessage);
            ((ObjectNode) rootNode).put("SIGNATURE", signature);

            return mapper.writeValueAsString(rootNode);
        } catch (IOException ex) {
            LOGGER.error(null, ex);
            return null;
        } catch (Exception ex) {
            LOGGER.error(null, ex);
            return null;
        }

    }

    static String getProperty(String key) {

        final Properties properties = new Properties();

        try {
            properties.load(new ClassPathResource("global-config.properties").getInputStream());

            return properties.getProperty(key);
        } catch (IOException ex) {
            LOGGER.error(null, ex);
            return null;
        }
    }

}
