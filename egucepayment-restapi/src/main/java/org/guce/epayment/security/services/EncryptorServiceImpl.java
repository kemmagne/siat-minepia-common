package org.guce.epayment.security.services;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Service;

@Service
@PropertySources(value = @PropertySource("classpath:global-config.properties"))
public class EncryptorServiceImpl implements EncryptorService {

    @Value("${keystore.password}")
    private String keystorePassword;
    @Value("${keystore.key.password}")
    private String keystoreKeyPassword;
    @Value("${keystore.alias}")
    private String keystoreAlias;

    @PostConstruct
    public void init() {
    }

    @Override
    public String encrypt(final String clearText) {
        return clearText;
    }

    @Override
    public String decrypt(final String cypherText) {
        return cypherText;
    }

}
