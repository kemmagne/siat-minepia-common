package org.guce.siat.common.dao;

import java.util.List;
import org.guce.siat.common.model.Params;
import org.guce.siat.common.utils.enums.ParamsCategory;

/**
 * The Interface ParamsDao.
 */
public interface ParamsDao extends AbstractJpaDao<Params> {

    /**
     * Find params by name.
     *
     * @param name the name
     * @return the params
     */
    Params findParamsByName(String name);

    /**
     * Find params by category.
     *
     * @param category the category
     * @return the list
     */
    List<Params> findParamsByCategory(ParamsCategory category);

}
