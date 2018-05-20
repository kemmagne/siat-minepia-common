package org.guce.epayment.core.services;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.guce.epayment.core.dao.CoreDao;
import org.guce.epayment.core.entities.RepPartnerGroup;
import org.guce.epayment.core.entities.RepReceipt;
import org.guce.epayment.core.repositories.RepPartnerGroupRepository;
import org.guce.epayment.core.repositories.RepReceiptRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CoreServiceImpl implements CoreService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoreServiceImpl.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private CoreDao coreDao;
    @Autowired
    private RepPartnerGroupRepository partnerGroupRepository;
    @Autowired
    private RepReceiptRepository receiptRepository;

    @Override
    public <T> Optional<T> findByUniqueKey(final String uniqueKey, final String uniqueValue, final Class<T> clazz) {

        try {

            final Object repository = getRepository(clazz);
            final String methodName = "findBy" + uniqueKey.substring(0, 1).toUpperCase() + uniqueKey.substring(1);
            final Method method = repository.getClass().getMethod(methodName, String.class);

            return Optional.ofNullable(((Optional<T>) method.invoke(repository, uniqueValue)).orElse(null));
        } catch (Exception ex) {
            LOGGER.error(null, ex);

            return Optional.empty();
        }
    }

    @Override
    public <T> List<T> findAll(final Class<T> clazz) {

        try {

            final Object repository = getRepository(clazz);
            final Method method = repository.getClass().getMethod("findAll");

            return (List<T>) method.invoke(repository);
        } catch (Exception ex) {
            LOGGER.error(null, ex);

            return new ArrayList<>();
        }
    }

    @Override
    public <T> Optional<T> findById(final Object id, final Class<T> clazz) {

        try {
            return coreDao.findById(id, clazz);
        } catch (Exception ex) {
            LOGGER.error(null, ex);

            return Optional.empty();
        }
    }

    @Override
    public void save(final Object entity, final Class clazz) {

        try {

            final Object repository = getRepository(clazz);
            final Method method = repository.getClass().getMethod("save", Object.class);

            method.invoke(repository, entity);
        } catch (Exception ex) {
            LOGGER.error(null, ex);
        }
    }

    private Object getRepository(final Class clazz) {

        final String repositoryName = clazz.getSimpleName().toLowerCase() + "Repository";

        return Optional.of(applicationContext.getBean(repositoryName)).orElseThrow(() -> new RuntimeException("Repository doesn't exist : " + repositoryName));
    }

    @Override
    public List<RepPartnerGroup> findAllPartnerGroups() {
        return partnerGroupRepository.findAll();
    }

    @Override
    public List<RepReceipt> findReceiptsByInvoiceType(final String invoiceTypeCode) {
        return receiptRepository.findByInvoiceType(invoiceTypeCode);
    }

}
