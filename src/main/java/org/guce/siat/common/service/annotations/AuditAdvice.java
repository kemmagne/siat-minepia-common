package org.guce.siat.common.service.annotations;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.guce.siat.common.dao.AuditDao;
import org.guce.siat.common.model.AbstractModel;
import org.guce.siat.common.model.AuditEntity;
import org.guce.siat.common.model.User;
import org.guce.siat.common.service.exception.BusinessException;
import org.guce.siat.common.utils.SecurityUtils;
import org.guce.siat.common.utils.enums.AuditConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * The Class AuditAdvice.
 */
@Component
@Scope("session")
@Aspect
public class AuditAdvice implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6853243507359066259L;
    /**
     * The audit dao.
     */
    @Autowired
    private AuditDao auditDao;

    /**
     * Audit around.
     *
     * @param proceedingJoinPoint the proceeding join point
     * @param auditAnnotation the audit annotation
     * @param parameter the parameter
     * @return the object
     * @throws Throwable the throwable
     */
    @Around("execution(public * org.guce.siat..*ServiceImpl.*(..)) && @annotation(auditAnnotation) && args(parameter)")
    public Object auditAround(final ProceedingJoinPoint proceedingJoinPoint, final Audit auditAnnotation, final Object parameter) throws Throwable {

        final AuditConstants operationType = auditAnnotation.operationType();

        if (null != operationType) {
            switch (operationType) {
                case SAVE:
                    return auditAroundForSaveOperation(proceedingJoinPoint, parameter, operationType);
                case UPDATE:
                    return auditAroundForUpdateOperation(proceedingJoinPoint, parameter, operationType);
                case DELETE:
                    return auditAroundForDeleteOperation(proceedingJoinPoint, parameter, operationType);
                case BAD_CREDENTIALS:
                    return auditAroundForBadCredentialsOperation(proceedingJoinPoint, parameter, operationType);
                case PASSWORD_RESET:
                    return auditAroundForPasswordResetOperation(proceedingJoinPoint, parameter, operationType);
                default:
                    break;
            }
        }
        return null;
    }

    /**
     * Audit around for delete operation.
     *
     * @param proceedingJoinPoint the proceeding join point
     * @param model the model
     * @param operationType the operation type
     * @return the object
     * @throws BusinessException the business exception
     * @throws Throwable the throwable
     */
    private Object auditAroundForDeleteOperation(final ProceedingJoinPoint proceedingJoinPoint, final Object model,
            final AuditConstants operationType) throws BusinessException, Throwable {
        Object finalTarget = proceedingJoinPoint.proceed();
        saveAuditLogMessage(operationType, model);
        return finalTarget;
    }

    /**
     * Audit around for save operation.
     *
     * @param proceedingJoinPoint the proceeding join point
     * @param model the model
     * @param operationType the operation type
     * @return the object
     * @throws BusinessException the business exception
     * @throws Throwable the throwable
     */
    private Object auditAroundForSaveOperation(final ProceedingJoinPoint proceedingJoinPoint, final Object model,
            final AuditConstants operationType) throws BusinessException, Throwable {
        Object finalTarget = proceedingJoinPoint.proceed();
        if (finalTarget == null) {
            finalTarget = model;
        }
        if (model instanceof List) {
            saveAuditListLogMessage(operationType, finalTarget);
        } else {
            saveAuditLogMessage(operationType, finalTarget);
        }
        return finalTarget;
    }

    /**
     * Audit around for update operation.
     *
     * @param proceedingJoinPoint the proceeding join point
     * @param model the model
     * @param operationType the operation type
     * @return the object
     * @throws BusinessException the business exception
     * @throws Throwable the throwable
     */
    private Object auditAroundForUpdateOperation(final ProceedingJoinPoint proceedingJoinPoint, final Object model,
            final AuditConstants operationType) throws BusinessException, Throwable {
        Object finalTarget = proceedingJoinPoint.proceed();
        saveAuditLogMessage(operationType, model);
        return finalTarget;
    }

    /**
     * Audit around for bad credentials operation.
     *
     * @param proceedingJoinPoint the proceeding join point
     * @param model the model
     * @param operationType the operation type
     * @return the object
     * @throws BusinessException the business exception
     * @throws Throwable the throwable
     */
    private Object auditAroundForBadCredentialsOperation(final ProceedingJoinPoint proceedingJoinPoint, final Object model,
            final AuditConstants operationType) throws BusinessException, Throwable {
        Object finalTarget = proceedingJoinPoint.proceed();
        saveAuditLogMessage(operationType, model);
        return finalTarget;
    }

    private Object auditAroundForPasswordResetOperation(final ProceedingJoinPoint proceedingJoinPoint, final Object model,
            final AuditConstants operationType) throws BusinessException, Throwable {
        Object finalTarget = proceedingJoinPoint.proceed();
        saveAuditLogMessage(operationType, model);
        return finalTarget;
    }

    /**
     * Save audit log message.
     *
     * @param operationType the operation type
     * @param model the model
     * @throws BusinessException the business exception
     */
    private void saveAuditLogMessage(final AuditConstants operationType, final Object model) throws BusinessException, IOException {
        final AuditEntity auditEntity = new AuditEntity();
        if (AuditConstants.BAD_CREDENTIALS.equals(operationType)) {
            auditEntity.setUsername(((User) model).getLogin());
        } else {
            auditEntity.setUsername(SecurityUtils.getCurrentUser());
        }
        auditEntity.setAuditDate(Calendar.getInstance().getTime());
        auditEntity.setAction(operationType.getCode());
        auditEntity.setModel(model.getClass().getSimpleName());
        auditEntity.setIpAddress(SecurityUtils.getCurrentAddressIp());
        auditEntity.setMacAddress(SecurityUtils.getCurrentMacAddress());

        final AbstractModel abstractModel = (AbstractModel) model;

        if (abstractModel.getId() != null) {
            auditEntity.setIdModel(abstractModel.getId());
        }

        if (AuditConstants.BAD_CREDENTIALS.equals(operationType)) {
            auditEntity.setValue(((User) model).getAuditBadCredentialsString());
        } else {
            auditEntity.setValue(abstractModel.toString());
        }

        auditDao.save(auditEntity);
    }

    /**
     * Save audit list log message.
     *
     * @param operation the operation
     * @param model the model
     * @throws BusinessException the business exception
     */
    private void saveAuditListLogMessage(final AuditConstants operation, final Object model) throws BusinessException {
        @SuppressWarnings("unchecked")
        final List<AbstractModel> abstractModelList = (List<AbstractModel>) model;

        final List<AuditEntity> auditEntitiesList = new ArrayList<>();

        for (final AbstractModel abstractModel : abstractModelList) {
            final AuditEntity auditEntity = new AuditEntity();
            auditEntity.setUsername(SecurityUtils.getCurrentUser());
            auditEntity.setAuditDate(Calendar.getInstance().getTime());
            auditEntity.setAction(operation.getCode());
            auditEntity.setModel(abstractModel.getClass().getSimpleName());
            auditEntity.setIpAddress(SecurityUtils.getCurrentAddressIp());
            auditEntity.setMacAddress(SecurityUtils.getCurrentMacAddress());

            if (abstractModel.getId() != null) {
                auditEntity.setIdModel(abstractModel.getId());
            }
            auditEntity.setValue(abstractModel.toString());

            auditEntitiesList.add(auditEntity);
        }

        auditDao.saveOrUpdateList(auditEntitiesList);
    }

    /**
     * Gets the audit dao.
     *
     * @return the audit dao
     */
    public AuditDao getAuditDao() {
        return auditDao;
    }

    /**
     * Sets the audit dao.
     *
     * @param auditDao the new audit dao
     */
    public void setAuditDao(final AuditDao auditDao) {
        this.auditDao = auditDao;
    }

}
