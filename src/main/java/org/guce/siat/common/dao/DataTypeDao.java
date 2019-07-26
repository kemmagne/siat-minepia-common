package org.guce.siat.common.dao;

import org.guce.siat.common.model.DataType;
import org.guce.siat.common.model.Flow;
import org.guce.siat.common.utils.enums.DataTypeNameEnnumeration;

/**
 * The Interface DataTypeDao.
 */
public interface DataTypeDao extends AbstractJpaDao<DataType> {

    /**
     * Find data type by name and flow code.
     *
     * @param flow the flow
     * @param dataTypeNameEnnumeration the data type name ennumeration
     * @return the data type
     */
    DataType findDataTypeByNameAndFlowCode(Flow flow, DataTypeNameEnnumeration dataTypeNameEnnumeration);

    /**
     * Find data type by name and flow code.
     *
     * @param flow the flow
     * @param code the code
     * @return the data type
     */
    DataType findDataTypeByCode(Flow flow, String code);

}
