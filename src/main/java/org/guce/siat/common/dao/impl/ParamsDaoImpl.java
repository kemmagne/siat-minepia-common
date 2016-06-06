package org.guce.siat.common.dao.impl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import org.guce.siat.common.dao.ParamsDao;
import org.guce.siat.common.dao.exception.DAOException;
import org.guce.siat.common.model.Params;
import org.guce.siat.common.utils.enums.ParamsCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class ParamsDaoImpl.
 */
@Repository("paramsDao")
@Transactional(propagation = Propagation.REQUIRED)
public class ParamsDaoImpl extends AbstractJpaDaoImpl<Params> implements ParamsDao
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ParamsDaoImpl.class);

	/**
	 * Instantiates a new params dao impl.
	 */
	public ParamsDaoImpl()
	{
		super();
		setClasse(Params.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.dao.ParamsDao#findParamsByName(java.lang.String)
	 */
	@Override
	public Params findParamsByName(final String name)
	{
		try
		{
			final String hqlQuery = "SELECT p FROM Params p WHERE p.name = :name";
			final TypedQuery<Params> query = super.entityManager.createQuery(hqlQuery, Params.class);
			query.setParameter("name", name);
			return query.getSingleResult();
		}
		catch (final NoResultException | NonUniqueResultException e)
		{
			LOG.info(Objects.toString(e));
			throw new DAOException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.dao.ParamsDao#findParamsByCategory(org.guce.siat.common.utils.enums.ParamsCategory)
	 */
	@Override
	public List<Params> findParamsByCategory(final ParamsCategory category)
	{
		if (category != null)
		{
			final String hqlQuery = "SELECT p FROM Params p WHERE p.paramsCategory = :paramsCateory";
			final TypedQuery<Params> query = super.entityManager.createQuery(hqlQuery, Params.class);
			query.setParameter("paramsCateory", category);
			return query.getResultList();
		}
		return Collections.emptyList();
	}

}
