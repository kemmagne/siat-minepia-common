package org.guce.epayment.security.services;

/**
 *
 * @author tadzotsa
 */
public interface EncryptorService {

    String encrypt(String clearText);

    String decrypt(String cypherText);

}
