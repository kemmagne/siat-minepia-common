package org.guce.siat.common.service;

import java.util.List;

import org.guce.siat.common.model.FileTypeFlowReport;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.ReportOrganism;


/**
 * The Interface ReportOrganismService.
 */
public interface ReportOrganismService extends AbstractService<ReportOrganism>
{
	
	
	/**
	 * Find report by organism.
	 *
	 * @param organism the organism
	 * @return the list
	 */
	List<ReportOrganism> findReportByOrganism(Organism organism);
	
	/**
	 * Find report by file type flow report.
	 *
	 * @param fileTypeFlowReport the file type flow report
	 * @return the report organism
	 */
	ReportOrganism findReportByFileTypeFlowReport(FileTypeFlowReport fileTypeFlowReport);
}
