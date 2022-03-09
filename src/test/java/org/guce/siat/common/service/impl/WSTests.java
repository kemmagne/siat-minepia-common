package org.guce.siat.common.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.IOUtils;
//import org.guce.siat.common.utils.SecurityUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author tadzotsa
 */
public class WSTests {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("_yyyyMMdd_HHmmss.SSS");

    private static final String LOGIN = "@4wWYa3!9fhMS@dqMlKY";
    private static final String PASSWORD = "ek5zD]hKv4@WuD$5";

    private final String webserviceUrl = "http://localhost:9759/orchestra/privacy/siat/messageService/ebxml";

    /**
     * The rest template.
     */
    private RestTemplate restTemplate;

    @Ignore
    @Before
    public void before() {
        restTemplate = new RestTemplate();
        final SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setBufferRequestBody(false);
        restTemplate.setRequestFactory(requestFactory);
    }

    @Ignore
    @Test
    public void test() throws Exception {

        InputStream input = new FileInputStream("/media/tadzotsa/Windows/Users/tadzotsa/NetBeansProjects/orchestra-esb/workspace/backup/in_message_backup/SIAT/2019/03/19/20190316-091124-87600@127.0.0.1.ebxml");
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        IOUtils.copy(input, output);

        final byte[] ebxmlData = output.toByteArray();
        final InputStream in = new ByteArrayInputStream(ebxmlData);
        final RequestCallback requestCallback = new RequestCallback() {
            @Override
            public void doWithRequest(ClientHttpRequest request) throws IOException {
                request.getHeaders().add("Content-type", MediaType.APPLICATION_OCTET_STREAM_VALUE);
//                request.getHeaders().add("Authorization", SecurityUtils.getBasicAuth(LOGIN, PASSWORD));
                IOUtils.copy(in, request.getBody());
            }
        };

        final HttpMessageConverterExtractor<String> responseExtractor = new HttpMessageConverterExtractor<>(String.class, restTemplate.getMessageConverters());

        restTemplate.execute(webserviceUrl, HttpMethod.POST, requestCallback, responseExtractor);
    }

    @Ignore
    @Test
    public void test1() {
        String login = "LetRaBleMPbraPsYNeor";
        String password = "rVckh5Td9SsB5G897U3y";
//        System.out.println(SecurityUtils.getBasicAuth(login, password));
    }

    @Ignore
    @Test
    public void testFormat() {
        System.out.println(DATE_FORMAT.format(new Date()));
    }

    @Ignore
    @Test
    public void testReplace() {
        String old = "<<<<de005740.pdf>>>>";
        String new1 = old.replaceAll("<|>", "");
        System.out.println(new1);
        String new2 = old.replace("<|>", "");
        System.out.println(new2);
    }

}
