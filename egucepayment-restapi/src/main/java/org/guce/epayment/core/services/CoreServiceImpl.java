package org.guce.epayment.core.services;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import org.guce.epayment.core.dao.CoreDao;
import org.guce.epayment.core.entities.RepPartnerGroup;
import org.guce.epayment.core.entities.Receipt;
import org.guce.epayment.core.repositories.RepPartnerGroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.guce.epayment.core.repositories.ReceiptRepository;

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
    private ReceiptRepository receiptRepository;

    @Override
    public <T> Optional<T> findByUniqueKey(final String uniqueKey, final String uniqueValue, final Class<T> clazz) {

        try {

            final Object repository = getRepository(clazz);
            final String methodName = "findBy" + uniqueKey.substring(0, 1).toUpperCase() + uniqueKey.substring(1);
            final Method method = repository.getClass().getMethod(methodName, String.class);

            return Optional.ofNullable(((Optional<T>) method.invoke(repository, uniqueValue)).orElse(null));
        } catch (Exception ex) {
            LOGGER.error(null, ex);
            throw new RuntimeException(ex);
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
            throw new RuntimeException(ex);
        }
    }

    @Override
    public <T> Optional<T> findById(final Object id, final Class<T> clazz) {

        try {
            return coreDao.findById(id, clazz);
        } catch (Exception ex) {
            LOGGER.error(null, ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public <T> T save(final Object entity, final Class<T> clazz) {

        try {

            final Object repository = getRepository(clazz);
            final Method method = repository.getClass().getMethod("saveAndFlush", Object.class);

            return (T) method.invoke(repository, entity);
        } catch (Exception ex) {
            LOGGER.error(null, ex);
            throw new RuntimeException(ex);
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
    public List<Receipt> findReceiptsByInvoiceType(final String invoiceTypeCode) {
        return receiptRepository.findByInvoiceType(invoiceTypeCode);
    }

    @Override
    public <T> void delete(T entity, Class<T> entityClass) {

        try {

            final Object repository = getRepository(entityClass);
            final Method method = repository.getClass().getMethod("delete", entityClass);

            method.invoke(repository, entity);
        } catch (Exception ex) {
            LOGGER.error(null, ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteById(Object id, Class entityClass, Class idClass) {

        try {

            final Object repository = getRepository(entityClass);
            final Method method = repository.getClass().getMethod("deleteById", idClass);

            method.invoke(repository, id);
        } catch (Exception ex) {
            LOGGER.error(null, ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public <T> List<T> findRange(Class<T> entityClass, int start, int end) {
        return coreDao.findRange(entityClass, start, end);
    }

}
