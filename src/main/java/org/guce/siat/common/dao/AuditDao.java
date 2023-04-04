package org.guce.siat.common.dao;

import java.util.List;
import org.guce.siat.common.model.AuditEntity;
import org.guce.siat.common.model.User;
import org.guce.siat.common.utils.filter.AuditFilter;

/**
 * The Interface AuditDao.
 */
public interface AuditDao extends AbstractJpaDao<AuditEntity> {

    /**
     * Find by filter.
     *
     * @param filter the filter
     * @return the list
     */
    List<AuditEntity> findByFilter(AuditFilter filter);

    /**
     * Find by filter and model.
     *
     * @param filter the filter
     * @param alertName the alert name
     * @return the object
     */
    List<AuditEntity> findByFilterAndModel(AuditFilter filter, String alertName);

    /**
     * Find by username.
     *
     * @param username the user name
     * @return the list
     */
    List<AuditEntity> findByUsername(String username);

    /**
     * Find by model.
     *
     * @param model the model
     * @return the list
     */
    List<AuditEntity> findByModel(String model);

    /**
     * Find by admin.
     *
     * @param admin the admin
     * @return the list
     */
    List<AuditEntity> findByAdmin(User admin);

}
