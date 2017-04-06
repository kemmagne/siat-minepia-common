package org.guce.siat.common.dao.impl;

import java.util.Objects;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import org.guce.siat.common.dao.DataTypeDao;
import org.guce.siat.common.model.DataType;
import org.guce.siat.common.model.Flow;
import org.guce.siat.common.utils.enums.DataTypeNameEnnumeration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class DataTypeDaoImpl.
 */
@Repository("dataTypeDao")
@Transactional(propagation = Propagation.REQUIRED)
public class DataTypeDaoImpl extends AbstractJpaDaoImpl<DataType> implements DataTypeDao {

	/**
	 * The Constant LOG.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);

	/**
	 * Instantiates a new flow dao impl.
	 */
	public DataTypeDaoImpl() {
		super();
		setClasse(DataType.class);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.DataTypeDao#findDataTypeByNameAndFlowCode(org.guce.siat.common.model.Flow)
	 */
	@Override
	public DataType findDataTypeByNameAndFlowCode(final Flow flow, final DataTypeNameEnnumeration dataTypeNameEnnumeration) {

		if (flow != null) {
			try {
				final String hqlString = "SELECT d FROM DataType d WHERE d.flow.id = :flowId AND d.label = :dataTypeNameEnnumeration ";
				final TypedQuery<DataType> query = super.entityManager.createQuery(hqlString, DataType.class);
				query.setParameter("flowId", flow.getId());
				query.setParameter("dataTypeNameEnnumeration", dataTypeNameEnnumeration.getCode());

				return query.getSingleResult();
			} catch (final NoResultException | NonUniqueResultException e) {
				LOG.error(Objects.toString(e));
			}
		}
		return null;
	}
}
