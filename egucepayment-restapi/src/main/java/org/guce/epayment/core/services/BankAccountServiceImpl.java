package org.guce.epayment.core.services;

import java.util.List;
import org.guce.epayment.core.entities.BankAccount;
import org.guce.epayment.core.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankAccountRepository accountRepository;

    @Override
    public List<BankAccount> findByOwner(final String ownerCode) {
        return accountRepository.findByOwner(ownerCode);
    }

}
