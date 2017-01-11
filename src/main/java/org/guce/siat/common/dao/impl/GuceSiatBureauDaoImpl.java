/**
 *
 */
package org.guce.siat.common.dao.impl;

import java.util.Objects;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import org.guce.siat.common.dao.GuceSiatBureauDao;
import org.guce.siat.common.model.Bureau;
import org.guce.siat.common.model.GuceSiatBureau;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class ItemFlowDaoImpl.
 */
@Repository("guceSiatBureauDao")
@Transactional(propagation = Propagation.REQUIRED)
public class GuceSiatBureauDaoImpl extends AbstractJpaDaoImpl<GuceSiatBureau> implements GuceSiatBureauDao
{
	private static final Logger LOG = LoggerFactory.getLogger(GuceSiatBureauDaoImpl.class);

	/**
	 * Instantiates a new item flow dao impl.
	 */
	public GuceSiatBureauDaoImpl()
	{
		super();
		setClasse(GuceSiatBureau.class);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.dao.GuceSiatBureauDao#findByBureauSiat(org.guce.siat.common.model.Bureau)
	 */
	@Override
	public GuceSiatBureau findByBureauSiat(final Bureau bureau)
	{
		final TypedQuery<GuceSiatBureau> query = entityManager.createQuery(
				"SELECT b FROM GuceSiatBureau b WHERE b.siatBureau=:siatBureau", GuceSiatBureau.class);
		query.setParameter("siatBureau", bureau.getCode());
		try
		{
			return query.getSingleResult();
		}
		catch (NoResultException | NonUniqueResultException e)
		{
			LOG.error(Objects.toString(e));
			return null;
		}
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.dao.GuceSiatBureauDao#findByBureauGuce(java.lang.String)
	 */
	@Override
	public GuceSiatBureau findByBureauGuce(final String bureauGuceCode)
	{

		try
		{
			final String hqlString = "SELECT b FROM GuceSiatBureau b WHERE b.bureauGuceCode = :bureauGuceCode";
			final TypedQuery<GuceSiatBureau> query = super.entityManager.createQuery(hqlString, GuceSiatBureau.class);
			query.setParameter("bureauGuceCode", bureauGuceCode);
			return query.getSingleResult();
		}
		catch (NoResultException | NonUniqueResultException e)
		{
			LOG.info(Objects.toString(e));
			return null;
		}

		/*
		 * if (StringUtils.isNotBlank(bureauGuceCode)) { try { final TypedQuery<GuceSiatBureau> query =
		 * entityManager.createQuery( "SELECT b FROM GuceSiatBureau b WHERE b.bureauGuceCode= :bureauGuceCode",
		 * GuceSiatBureau.class); query.setParameter("bureauGuceCode", bureauGuceCode); return query.getSingleResult(); }
		 * catch (NoResultException | NonUniqueResultException e) { //throw new
		 * ValidationException("code bureau invalide"); LOG.error("code bureau invalide"); } } return null;
		 */
	}
}
