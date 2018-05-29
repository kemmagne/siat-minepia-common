package org.guce.epayment.core.services;

import java.util.List;
import org.guce.epayment.core.entities.BankAccount;

/**
 *
 * @author tadzotsa
 */
public interface BankAccountService {

    List<BankAccount> findByOwner(String ownerCode);

}
