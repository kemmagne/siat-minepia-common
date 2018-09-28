package org.guce.siat.common.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.FileDao;
import org.guce.siat.common.dao.UserAuthorityFileTypeDao;
import org.guce.siat.common.model.Administration;
import org.guce.siat.common.model.Company;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileFieldValue;
import org.guce.siat.common.model.FileType;
import org.guce.siat.common.model.User;
import org.guce.siat.common.service.FileService;
import org.guce.siat.common.utils.enums.FileTypeCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class FileServiceImpl.
 */
@Service("fileService")
@Transactional(readOnly = true)
public class FileServiceImpl extends AbstractServiceImpl<File> implements FileService {

	/**
	 * The file dao.
	 */
	@Autowired
	private FileDao fileDao;

	/**
	 * The user authority file type dao.
	 */
	@Autowired
	private UserAuthorityFileTypeDao userAuthorityFileTypeDao;

	/**
	 * Instantiates a new file service impl.
	 */
	public FileServiceImpl() {
		super();
	}

	/*
         * (non-Javadoc)
         *
         * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<File> getJpaDao() {
		return fileDao;
	}

	/*
         * (non-Javadoc)
         *
         * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<File> jpaDao) {
		this.fileDao = (FileDao) jpaDao;
	}

	/*
         * (non-Javadoc)
         *
         * @see org.guce.siat.core.ct.service.FileService#findFileFieldValueByFieldCode(org.guce.siat.core.ct.model.File,
         * java.lang.String)
	 */
	public FileFieldValue findFileFieldValueByFieldCode(final File file, final String fieldCode) {
		return fileDao.findFileFieldValueByFieldCode(file, fieldCode);
	}

	/*
         * (non-Javadoc)
         *
         * @see org.guce.siat.common.service.FileService#quickSearch(java.lang.String,
         * org.guce.siat.common.model.Administration, org.guce.siat.common.model.User)
	 */
	@Override
	public File quickSearch(final String documentNumberFilter, final Administration administration, final User loggedUser) {
		final List<FileType> fileTypesByUser = userAuthorityFileTypeDao.findFilesTypesByAuthorizedUser(loggedUser);

		@SuppressWarnings("unchecked")
		final List<FileTypeCode> fileTypeCodes = (List<FileTypeCode>) CollectionUtils.collect(fileTypesByUser, new Transformer() {
			@Override
			public Object transform(final Object fileType) {
				return ((FileType) fileType).getCode();
			}
		});
		List<Administration> administrations = new ArrayList<>();
		administrations.add(administration);
		if (loggedUser.getAdministrationExtendRoles() != null) {
			administrations.add(loggedUser.getAdministrationExtendRoles());
		}
		//file file_user_user_authority must be added to the search filter
		return fileDao.quickSearch(documentNumberFilter, administrations, fileTypeCodes);
	}

	/*
         * (non-Javadoc)
         *
         * @see
         * org.guce.siat.common.service.FileService#findMatchingBetweenManifestAndPriorNotice(org.guce.siat.common.model.
         * Company, org.guce.siat.common.utils.enums.FileTypeCode, org.guce.siat.common.model.FileFieldValue,
         * org.guce.siat.common.model.FileFieldValue)
	 */
	@Override
	public File findMatchingBetweenManifestAndPriorNotice(final Company client, final List<FileTypeCode> fileTypeCodeList,
			final FileFieldValue fileFieldValue1, final FileFieldValue fileFieldValue2, final String fileFieldCode1,
			final String fileFieldCode2) {
		return fileDao.findMatchingBetweenManifestAndPriorNotice(client, fileTypeCodeList, fileFieldValue1, fileFieldValue2,
				fileFieldCode1, fileFieldCode2);
	}

	/*
         * (non-Javadoc)
         *
         * @see org.guce.siat.common.service.FileService#findDraftFilesByUsr(org.guce.siat.common.model.User)
	 */
	@Override
	public List<File> findDraftFilesByUsr(final User user) {
		return fileDao.findDraftFilesByUsr(user);
	}

	/**
	 * Find fimex filesby bureaux.
	 *
	 * @param fileTypeCode the file type code
	 * @return the list
	 */
	@Override
	public List<File> findFilesByFileTypeCode(final FileTypeCode fileTypeCode) {
		return fileDao.findFilesByFileTypeCode(fileTypeCode);
	}

	/*
         * (non-Javadoc)
         *
         * @see org.guce.siat.common.service.FileService#findCountByFileType(org.guce.siat.common.utils.enums.FileTypeCode)
	 */
	@Override
	public Long findCountByFileType(final FileTypeCode fileTypeCode) {
		return fileDao.findCountByFileType(fileTypeCode);
	}

	/*
         * (non-Javadoc)
         *
         * @see org.guce.siat.common.service.FileService#findByNumeroDemandeAndFileTypeGuce(java.lang.String, java.lang.String)
	 */
	@Override
	public File findByNumeroDemandeAndFileTypeGuce(String numeroDemande, String fileTypeGuce) {
		return fileDao.findByNumeroDemandeAndFileTypeGuce(numeroDemande, fileTypeGuce);
	}

	/*
         * (non-Javadoc)
         *
         * @see org.guce.siat.common.service.FileService#findMatchingBetweenFimexAndPriorNotice(java.lang.String,
         * java.util.List)
	 */
	@Override
	public File findMatchingBetweenFimexAndPriorNotice(final String numContribuable, final List<FileTypeCode> fileTypeCodeList) {
		return fileDao.findMatchingBetweenFimexAndPriorNotice(numContribuable, fileTypeCodeList);
	}

	/*
         * (non-Javadoc)
         *
         * @see org.guce.siat.common.service.FileService#updateSpecificColumn(java.util.Map, org.guce.siat.common.model.File)
	 */
	@Override
	public void updateSpecificColumn(final Map<String, ?> paramsMap, final File file) {
		fileDao.updateSpecificColumn(paramsMap, file);

	}
}
