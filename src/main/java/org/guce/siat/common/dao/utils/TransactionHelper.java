package org.guce.siat.common.dao.utils;

import javax.annotation.PostConstruct;
import org.apache.commons.lang3.mutable.MutableObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 *
 * @author tadzotsa
 */
@Component("transactionHelper")
public class TransactionHelper {

    @Autowired
    private PlatformTransactionManager transactionManager;

    private DefaultTransactionDefinition definition;

    @PostConstruct
    public void init() {
        definition = new DefaultTransactionDefinition();
    }

    public MutableObject<TransactionStatus> beginTransaction() {
        return beginTransaction(null);
    }

    public MutableObject<TransactionStatus> beginTransaction(DefaultTransactionDefinition pDefinition) {
        DefaultTransactionDefinition vDefinition = pDefinition != null ? pDefinition : definition;
        TransactionStatus vStatus = transactionManager.getTransaction(vDefinition);
        return new MutableObject<>(vStatus);
    }

    public void commit(MutableObject<TransactionStatus> pStatus) {
        if (pStatus != null && pStatus.getValue() != null) {
            pStatus.setValue(null);
            transactionManager.commit(pStatus.getValue());
        }
    }

    public void rollback(MutableObject<TransactionStatus> pStatus) {
        if (pStatus != null && pStatus.getValue() != null) {
            pStatus.setValue(null);
            transactionManager.rollback(pStatus.getValue());
        }
    }

}
