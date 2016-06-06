package org.guce.siat.common.service.impl;

import org.guce.siat.common.service.AlfrescoPropretiesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class AlfrescoPropretiesServiceImpl.
 */
@Service("alfrescoPropretiesService")
@Transactional(readOnly = true)
@PropertySource("classpath:alfresco-config.properties")
public class AlfrescoPropretiesServiceImpl implements AlfrescoPropretiesService
{

	/** The ip repo. */
	@Value("${ipRepo}")
	private String ipRepo;

	/** The url atom repo. */
	@Value("${urlAtomRepo}")
	private String urlAtomRepo;

	/** The user name repo. */
	@Value("${userNameRepo}")
	private String userNameRepo;

	/** The pwd repo. */
	@Value("${pwdRepo}")
	private String pwdRepo;

	/** The id repo. */
	@Value("${idRepo}")
	private String idRepo;

	/** The url repo. */
	@Value("${urlRepo}")
	private String urlRepo;


	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.AlfrescoPropretiesService#getIpRepoValue()
	 */
	@Override
	public String getIpRepoValue()
	{
		return getIpRepo();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.AlfrescoPropretiesService#getUrlAtomRepoValue()
	 */
	@Override
	public String getUrlAtomRepoValue()
	{
		return getUrlAtomRepo();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.AlfrescoPropretiesService#getUserNameRepoValue()
	 */
	@Override
	public String getUserNameRepoValue()
	{
		return getUserNameRepo();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.AlfrescoPropretiesService#getPwdRepoValue()
	 */
	@Override
	public String getPwdRepoValue()
	{
		return getPwdRepo();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.AlfrescoPropretiesService#getIdRepoValue()
	 */
	@Override
	public String getIdRepoValue()
	{
		return getIdRepo();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.AlfrescoPropretiesService#getUrlRepoValue()
	 */
	@Override
	public String getUrlRepoValue()
	{
		return getUrlRepo();
	}

	/**
	 * Gets the ip repo.
	 *
	 * @return the ipRepo
	 */
	public String getIpRepo()
	{
		return ipRepo;
	}

	/**
	 * Gets the url atom repo.
	 *
	 * @return the urlAtomRepo
	 */
	public String getUrlAtomRepo()
	{
		return urlAtomRepo;
	}

	/**
	 * Gets the user name repo.
	 *
	 * @return the userNameRepo
	 */
	public String getUserNameRepo()
	{
		return userNameRepo;
	}

	/**
	 * Gets the pwd repo.
	 *
	 * @return the pwdRepo
	 */
	public String getPwdRepo()
	{
		return pwdRepo;
	}

	/**
	 * Gets the id repo.
	 *
	 * @return the idRepo
	 */
	public String getIdRepo()
	{
		return idRepo;
	}

	/**
	 * Gets the url repo.
	 *
	 * @return the urlRepo
	 */
	public String getUrlRepo()
	{
		return urlRepo;
	}


}
