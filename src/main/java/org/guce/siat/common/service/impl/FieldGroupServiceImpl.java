package org.guce.siat.common.service.impl;

import java.util.List;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.FieldGroupDao;
import org.guce.siat.common.model.FieldGroup;
import org.guce.siat.common.model.FileType;
import org.guce.siat.common.service.FieldGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * The Class FieldGroupServiceImpl.
 */
@Service("fieldGroupService")
@Transactional(readOnly = true)
public class FieldGroupServiceImpl extends AbstractServiceImpl<FieldGroup> implements FieldGroupService
{

	/** The field group dao. */
	@Autowired
	private FieldGroupDao fieldGroupDao;



	/**
	 * Instantiates a new field group service impl.
	 */
	public FieldGroupServiceImpl()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<FieldGroup> getJpaDao()
	{
		return fieldGroupDao;
	}

	@Override
	public void setJpaDao(final AbstractJpaDao<FieldGroup> jpaDao)
	{
		this.fieldGroupDao = (FieldGroupDao) jpaDao;

	}

	@Override
	public List<FieldGroup> findAllByFileType(final FileType fileType, final String type)
	{
		return fieldGroupDao.findAllByFileType(fileType, type);
	}
}
