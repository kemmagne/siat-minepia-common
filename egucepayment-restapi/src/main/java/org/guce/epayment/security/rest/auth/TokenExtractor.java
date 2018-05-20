package org.guce.epayment.security.rest.auth;

/**
 *
 * @author tadzotsa
 */
//@FunctionalInterface
public interface TokenExtractor {

    String extract(String payload);

}
