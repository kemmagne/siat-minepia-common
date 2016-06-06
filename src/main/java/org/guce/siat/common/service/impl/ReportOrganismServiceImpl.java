package org.guce.siat.common.service.impl;

import java.util.List;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.ReportOrganismDao;
import org.guce.siat.common.model.FileTypeFlowReport;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.ReportOrganism;
import org.guce.siat.common.service.ReportOrganismService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class ReportOrganismServiceImpl.
 */
@Service("reportOrganismService")
@Transactional(readOnly = true)
public class ReportOrganismServiceImpl extends AbstractServiceImpl<ReportOrganism> implements ReportOrganismService
{


	/** The report organism dao. */
	@Autowired
	private ReportOrganismDao reportOrganismDao;


	/**
	 * Instantiates a new report organism service impl.
	 */
	public ReportOrganismServiceImpl()
	{
		super();
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<ReportOrganism> getJpaDao()
	{
		return reportOrganismDao;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<ReportOrganism> jpaDao)
	{
		this.reportOrganismDao = (ReportOrganismDao) jpaDao;
	}


	@Override
	public List<ReportOrganism> findReportByOrganism(Organism organism)
	{

		return reportOrganismDao.findReportByOrganism(organism);
	}


	/* (non-Javadoc)
	 * @see org.guce.siat.common.service.ReportOrganismService#findReportByFileTypeFlowReport(org.guce.siat.common.model.FileTypeFlowReport)
	 */
	@Override
	public ReportOrganism findReportByFileTypeFlowReport(FileTypeFlowReport fileTypeFlowReport)
	{
		return reportOrganismDao.findReportByFileTypeFlowReport(fileTypeFlowReport);
	}


}
