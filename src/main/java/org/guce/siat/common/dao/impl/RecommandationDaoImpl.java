package org.guce.siat.common.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.TypedQuery;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.guce.siat.common.dao.RecommandationDao;
import org.guce.siat.common.model.Authority;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.Recommandation;
import org.guce.siat.common.utils.enums.AuthorityConstants;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class RecommandationDaoImpl.
 */
@Repository("recommandationDao")
@Transactional(propagation = Propagation.REQUIRED)
public class RecommandationDaoImpl extends AbstractJpaDaoImpl<Recommandation> implements RecommandationDao
{

	/**
	 * Instantiates a new recommandation dao impl.
	 */
	public RecommandationDaoImpl()
	{
		super();
		setClasse(Recommandation.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.core.ct.dao.RecommandationDao#findRecommandationByFile(java.lang.Long)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Recommandation> findRecommandationByFileAndAuthorties(final File file, final List<Authority> authorities)
	{
		if (file != null)
		{
			final StringBuilder hqlQueryBuilder = new StringBuilder();
			hqlQueryBuilder
					.append("SELECT DISTINCT r FROM Recommandation r LEFT JOIN r.authorizedAuthorityList a   WHERE r.file.id = :fileId");
			final Map<String, Object> params = new HashMap<String, Object>();
			params.put("fileId", file.getId());
			final List<String> rolesList = (List<String>) CollectionUtils.collect(authorities, new Transformer()
			{
				@Override
				public Object transform(final Object input)
				{
					return ((Authority) input).getRole();
				}
			});
			if (!rolesList.contains(AuthorityConstants.SUPERVISEUR.getCode()))
			{
				hqlQueryBuilder.append(" AND a.primaryKey.authority.role IN(:authorities) AND a.primaryKey.recommandation.id=r.id");
				params.put("authorities", rolesList);
			}

			final TypedQuery<Recommandation> query = super.entityManager.createQuery(hqlQueryBuilder.toString(),
					Recommandation.class);

			for (final Entry<String, Object> entry : params.entrySet())
			{
				query.setParameter(entry.getKey(), entry.getValue());
			}
			return query.getResultList();
		}
		return Collections.emptyList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.core.ct.dao.RecommandationDao#findRecommandationByFileItem(java.lang.Long)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Recommandation> findRecommandationByFileItemAndAuthorties(final FileItem fileItem,
			final List<Authority> authorities)
	{
		if (fileItem != null)
		{
			final StringBuilder hqlQueryBuilder = new StringBuilder();
			hqlQueryBuilder
					.append("SELECT DISTINCT r FROM Recommandation r JOIN r.authorizedAuthorityList a WHERE r.fileItem.id = :fileItemId");
			final Map<String, Object> params = new HashMap<String, Object>();
			params.put("fileItemId", fileItem.getId());
			final List<String> rolesList = (List<String>) CollectionUtils.collect(authorities, new Transformer()
			{
				@Override
				public Object transform(final Object input)
				{
					return ((Authority) input).getRole();
				}
			});
			if (!rolesList.contains(AuthorityConstants.SUPERVISEUR.getCode()))
			{
				hqlQueryBuilder.append(" AND a.primaryKey.authority.role IN(:authorities)  AND a.primaryKey.recommandation.id=r.id");
				params.put("authorities", rolesList);
			}

			final TypedQuery<Recommandation> query = super.entityManager.createQuery(hqlQueryBuilder.toString(),
					Recommandation.class);

			for (final Entry<String, Object> entry : params.entrySet())
			{
				query.setParameter(entry.getKey(), entry.getValue());
			}
			return query.getResultList();
		}
		return Collections.emptyList();
	}

}
