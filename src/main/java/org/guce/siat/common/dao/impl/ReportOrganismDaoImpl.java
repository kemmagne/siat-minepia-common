package org.guce.siat.common.dao.impl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import org.guce.siat.common.dao.ReportOrganismDao;
import org.guce.siat.common.model.FileTypeFlowReport;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.ReportOrganism;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class ReportOrganismDaoImpl.
 */
@Repository("reportOrganismDao")
@Transactional(propagation = Propagation.REQUIRED)
public class ReportOrganismDaoImpl extends AbstractJpaDaoImpl<ReportOrganism> implements ReportOrganismDao
{


	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ReportOrganismDaoImpl.class);


	/**
	 * Instantiates a new report organism dao impl.
	 */
	public ReportOrganismDaoImpl()
	{
		super();
		setClasse(ReportOrganism.class);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.ReportOrganismDao#findReportByOrganism(org.guce.siat.common.model.Organism)
	 */
	@Override
	public List<ReportOrganism> findReportByOrganism(final Organism organism)
	{
		if (!Objects.equals(organism, null))
		{
			final String hqlString = "SELECT r FROM ReportOrganism r WHERE r.organism.id = :organismId  ";
			final TypedQuery<ReportOrganism> query = super.entityManager.createQuery(hqlString, ReportOrganism.class);
			query.setParameter("organismId", organism.getId());
			return query.getResultList();
		}
		return Collections.emptyList();
	}

	/* (non-Javadoc)
	 * @see org.guce.siat.common.dao.ReportOrganismDao#findReportByFileTypeFlowReport(org.guce.siat.common.model.FileTypeFlowReport)
	 */
	@Override
	public ReportOrganism findReportByFileTypeFlowReport(final FileTypeFlowReport fileTypeFlowReport)
	{
		try
		{
			final String hqlString = "SELECT r FROM ReportOrganism r WHERE r.fileTypeFlowReport.id = :fileTypeFlowReportId  ";
			final TypedQuery<ReportOrganism> query = super.entityManager.createQuery(hqlString, ReportOrganism.class);
			query.setParameter("fileTypeFlowReportId", fileTypeFlowReport.getId());
			return query.getSingleResult();

		}
		catch (final NoResultException | NonUniqueResultException e)
		{
			LOG.info(Objects.toString(e));
			return null;
		}
	}

}
