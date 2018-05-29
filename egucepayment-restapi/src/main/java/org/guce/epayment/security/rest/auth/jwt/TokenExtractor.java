package org.guce.epayment.security.rest.auth.jwt;

/**
 *
 * @author tadzotsa
 */
//@FunctionalInterface
public interface TokenExtractor {

    String extract(String payload);

}
