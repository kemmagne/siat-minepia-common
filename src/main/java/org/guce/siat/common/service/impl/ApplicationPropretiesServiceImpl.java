package org.guce.siat.common.service.impl;

import org.guce.siat.common.service.ApplicationPropretiesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * The Class ApplicationPropretiesServiceImpl.
 */
@Service("applicationPropretiesService")
@Transactional(readOnly = true)
@PropertySources(value =
{ @PropertySource("classpath:application.properties"), @PropertySource("classpath:version.properties"),
		@PropertySource("classpath:siat-jms.properties") })
public class ApplicationPropretiesServiceImpl implements ApplicationPropretiesService
{

	/** The column separator. */
	@Value("${columnSeparator}")
	private String columnSeparator;

	/** The row separator. */
	@Value("${rowSeparator}")
	private String rowSeparator;

	/** The app env. */
	@Value("${application.environment}")
	private String appEnv;

	/** The app version. */
	@Value("${application.version}")
	private String appVersion;

	/** The attachement folder. */
	@Value("${attachement.folder}")
	private String attachementFolder;

	/** The repeatable separator. */
	@Value("${repeatableSeparator}")
	private String repeatableSeparator;

	/**
	 * Gets the column separator.
	 *
	 * @return the column separator
	 */
	public String getColumnSeparator()
	{
		return columnSeparator;
	}

	/**
	 * Gets the row separator.
	 *
	 * @return the row separator
	 */
	public String getRowSeparator()
	{
		return rowSeparator;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.ApplicationPropretiesService#getAppVersion()
	 */
	@Override
	public String getAppVersion()
	{
		return appVersion;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.ApplicationPropretiesService#getAppEnv()
	 */
	@Override
	public String getAppEnv()
	{
		return appEnv;
	}

	/**
	 * Gets the attachement folder.
	 *
	 * @return the attachementFolder
	 */
	public String getAttachementFolder()
	{
		return attachementFolder;
	}

	/**
	 * Sets the attachement folder.
	 *
	 * @param attachementFolder
	 *           the attachementFolder to set
	 */
	public void setAttachementFolder(final String attachementFolder)
	{
		this.attachementFolder = attachementFolder;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.ApplicationPropretiesService#getRepeatableSeparator()
	 */
	@Override
	public String getRepeatableSeparator()
	{
		return repeatableSeparator;
	}


}
