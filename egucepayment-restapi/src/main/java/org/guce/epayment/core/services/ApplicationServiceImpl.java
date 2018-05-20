package org.guce.epayment.core.services;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Service;

@Data
@Setter(AccessLevel.NONE)
@PropertySources(value = @PropertySource("classpath:global-config.properties"))
@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Value("${application.version}")
    private String appVersion;
    @Value("${application.environment}")
    private String appEnv;
    @Value("${columnSeparator}")
    private String colSep;
    @Value("${rowSeparator}")
    private String rowSep;
    @Value("${repeatableSeparator}")
    private String repeatableSep;
    @Value("${customColumnSeparator}")
    private String customColSep;
    @Value("${customRowSeparator}")
    private String customRowSep;

}
