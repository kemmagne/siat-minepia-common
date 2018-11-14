package org.guce.epayment.rest.controllers.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.guce.epayment.core.utils.InvoiceConstants;
import org.guce.epayment.rest.dto.IncomingMessageDto;
import org.guce.epayment.rest.dto.InvoiceDto;
import org.guce.util.CipherUtils;
import org.guce.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

/**
 *
 * @author tadzotsa
 */
public interface RestUtils {

    static final Logger LOGGER = LoggerFactory.getLogger(RestUtils.class);

    static List<Map<String, Object>> getInvoicesMap(List<InvoiceDto> invoiceDtos) {

        return invoiceDtos.stream().map(invoiceDto -> {

            final Map<String, Object> invoiceInfo = new HashMap<>();

            invoiceInfo.put(InvoiceConstants.INVOICE_ID, invoiceDto.getId());
            invoiceInfo.put(InvoiceConstants.INVOICE_VERSION_NUMBER, invoiceDto.getNumber());
            invoiceInfo.put(InvoiceConstants.INVOICE_AMOUNT_TO_PAY, invoiceDto.getAmountToPay());

            return invoiceInfo;
        }).collect(Collectors.toList());
    }

    static String getClientIp(HttpServletRequest request) {

        String remoteAddr = "";

        if (request != null) {

            remoteAddr = request.getHeader("X-FORWARDED-FOR");

            if (StringUtils.isBlank(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }

    static ResourceBundle getResourceBundle(String locale) {

        final String BUNDLE_PATH = "locale/locale";

        return ResourceBundle.getBundle(BUNDLE_PATH, new Locale(locale.toUpperCase()));
    }

    static byte[] getOriginalMessage(final IncomingMessageDto messageDto, final String senderPrefix) {

        try {

            final Properties props = new Properties();

            props.load(new ClassPathResource("global-config.properties").getInputStream());

            final String privateKey = FileUtil
                    .getFileContent(new ClassPathResource(props.getProperty("private.key.file")).getFile());
            final String secretKey = CipherUtils.rsaDecrypt(privateKey, messageDto.getCipheredSecretKey());
            final byte[] bytes = CipherUtils.aesDecrypt(messageDto.getCipheredOriginMessage(), secretKey);
            final String originalSignature = messageDto.getSignature();
            final String senderPublicKey = FileUtil
                    .getFileContent(new ClassPathResource(props.getProperty(senderPrefix + ".public.key.file"))
                            .getFile());
            if (CipherUtils.verify(bytes, originalSignature, senderPublicKey)) {
                return bytes;
            } else {
                return new byte[0];
            }
        } catch (Exception ex) {
            LOGGER.error(null, ex);
            return null;
        }
    }

    static <SUPER, SUB extends SUPER> SUB downCast(Class<SUPER> superClass, Class<SUB> subClass, SUPER entity) {

        if (entity == null) {
            return null;
        }

        try {

            final SUB result = subClass.newInstance();

            PropertyUtils.copyProperties(result, entity);

            return result;
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException
                | InstantiationException ex) {
            LOGGER.error(null, ex);
        }
        return null;
    }

}

