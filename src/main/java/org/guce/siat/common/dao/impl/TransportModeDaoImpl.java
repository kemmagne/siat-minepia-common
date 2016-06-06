/**
 *
 */
package org.guce.siat.common.dao.impl;

import java.util.Objects;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import org.guce.siat.common.dao.TransportModeDao;
import org.guce.siat.common.model.TransportMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



/**
 * The Class TransportModeDaoImpl.
 */
@Repository("transportModeDao")
@Transactional(propagation = Propagation.REQUIRED)
public class TransportModeDaoImpl extends AbstractJpaDaoImpl<TransportMode> implements TransportModeDao
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(TransportModeDaoImpl.class);

	/**
	 * Instantiates a new flow dao impl.
	 */
	public TransportModeDaoImpl()
	{
		super();
		setClasse(TransportMode.class);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.core.ct.dao.TransportModeDao#findByTransportModeCode(java.lang.Long)
	 */
	@Override
	public TransportMode findByTransportModeCode(final Long transportModeCode)
	{
		try
		{
			final String hqlString = "SELECT t FROM TransportMode t WHERE t.modeTransportCode = :modeTransportCode";
			final TypedQuery<TransportMode> query = super.entityManager.createQuery(hqlString, TransportMode.class);
			query.setParameter("modeTransportCode", transportModeCode);
			return query.getSingleResult();
		}
		catch (final NoResultException | NonUniqueResultException e)
		{
			LOG.info(Objects.toString(e));
			return null;
		}
	}
}
