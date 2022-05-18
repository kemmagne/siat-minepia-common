package org.guce.siat.common.dao;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.guce.siat.common.model.Administration;
import org.guce.siat.common.model.Authority;
import org.guce.siat.common.model.Bureau;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileType;
import org.guce.siat.common.model.Params;
import org.guce.siat.common.model.Step;
import org.guce.siat.common.model.Transfer;
import org.guce.siat.common.model.User;
import org.guce.siat.common.utils.enums.AuthorityConstants;
import org.guce.siat.common.utils.enums.AuthorityType;
import org.guce.siat.common.utils.enums.FileTypeCode;
import org.guce.siat.common.utils.enums.ParamsCategory;
import org.guce.siat.common.utils.enums.StepCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ht
 */
//@Transactional
//@Repository
public class TestDataBuilder {

    @Autowired
    private StepDao stepDao;
    @Autowired
    private FileTypeDao fileTypeDao;
    @Autowired
    private FileTypeStepDao fileTypeStepDao;
    @Autowired
    private TransferDao transferDao;
    @Autowired
    private FileDao fileDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private AdministrationDao administrationDao;
    @Autowired
    private BureauDao bureauDao;
    @Autowired
    private AuthorityDao authorityDao;
    @Autowired
    private UserAuthorityDao userAuthorityDao;
    @Autowired
    private ParamsDao paramsDao;

    public Params getParams() {
        Params params = new Params();
        params.setName(RandomStringUtils.randomAlphanumeric(10));
        params.setValue(RandomStringUtils.randomAlphanumeric(10));
        params.setParamsCategory(ParamsCategory.GR);
        return paramsDao.save(params);
    }

    public Bureau getBureau() {
        Bureau bureau = new Bureau();
        bureau.setLabelEn(RandomStringUtils.randomAlphanumeric(100));
        bureau.setLabelFr(RandomStringUtils.randomAlphanumeric(100));
        return bureauDao.save(bureau);
    }

    public User getUser() {
        User user = new User();
        user.setAdministration(getAdministration());
        user.setEnabled(true);
        return userDao.save(user);
    }

    public Step getStep() {
        Step step = new Step(StepCode.ST_CO_42);
        step.setIsFinal(Boolean.FALSE);
        step.setLabelEn(RandomStringUtils.randomAlphabetic(35));
        step.setLabelFr(RandomStringUtils.randomAlphabetic(35));
        return stepDao.save(step);
    }

    public FileType getFileType() {
        FileType fileType = new FileType();
        fileType.setCode(FileTypeCode.IDE);
        return fileTypeDao.save(fileType);
    }

    public File getFile() {
        File file = new File();
        file.setReferenceSiat(RandomStringUtils.randomAlphanumeric(35));
        file.setReferenceGuce(RandomStringUtils.randomAlphanumeric(35));
        file.setNumeroDossier(RandomStringUtils.randomAlphanumeric(35));
        file.setNumeroDemande(RandomStringUtils.randomAlphanumeric(35));
        file.setFileTypeGuce(RandomStringUtils.randomAlphanumeric(35));
        file.setEmetteur(RandomStringUtils.randomAlphanumeric(35));
        file.setDestinataire(RandomStringUtils.randomAlphanumeric(35));
        file.setFileType(getFileType());
        return fileDao.save(file);
    }

    public Transfer getTransfer() {
        Transfer transfer = new Transfer();
        transfer.setFile(getFile());
        transfer.setUser(getUser());
        transfer.setAssignedUser(getUser());
        transfer.setNumeroDemande(transfer.getFile().getNumeroDemande());
        return transferDao.save(transfer);
    }

    public Authority getAuthority(AuthorityConstants auth) {
        Authority authority = new Authority();
        authority.setId(NumberUtils.createLong(auth.hashCode() + ""));
        authority.setAuthorityType(AuthorityType.DECISION);
        authority.setLabelEn(auth.getLabel());
        authority.setLabelFr(auth.getLabel());
        authority.setRole(auth.getCode());
        return authorityDao.save(authority);
    }

    public Administration getAdministration() {
        Administration administration = new Administration();
        administration.setLabelEn(RandomStringUtils.randomAlphanumeric(10));
        administration.setLabelFr(RandomStringUtils.randomAlphanumeric(10));
        return administrationDao.save(administration);
    }

    public TransferDao getTransferDao() {
        return transferDao;
    }

    public FileDao getFileDao() {
        return fileDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public AdministrationDao getAdministrationDao() {
        return administrationDao;
    }

    public BureauDao getBureauDao() {
        return bureauDao;
    }

    public AuthorityDao getAuthorityDao() {
        return authorityDao;
    }

    public UserAuthorityDao getUserAuthorityDao() {
        return userAuthorityDao;
    }

    public ParamsDao getParamsDao() {
        return paramsDao;
    }

    public FileTypeDao getFileTypeDao() {
        return fileTypeDao;
    }

    public StepDao getStepDao() {
        return stepDao;
    }

    public FileTypeStepDao getFileTypeStepDao() {
        return fileTypeStepDao;
    }

}
