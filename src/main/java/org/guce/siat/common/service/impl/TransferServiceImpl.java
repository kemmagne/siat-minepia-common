package org.guce.siat.common.service.impl;

import java.util.List;
import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.TransferDao;
import org.guce.siat.common.model.Bureau;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.Transfer;
import org.guce.siat.common.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author yenke
 */
@Service("transferService")
@Transactional(readOnly = true)
public class TransferServiceImpl extends AbstractServiceImpl<Transfer> implements TransferService {

    @Autowired
    private TransferDao transferDao;

    @Override
    public AbstractJpaDao<Transfer> getJpaDao() {
        return transferDao;
    }

    @Override
    public void setJpaDao(AbstractJpaDao<Transfer> jpaDao) {
        this.transferDao = (TransferDao) jpaDao;
    }

    @Override
    public List<Transfer> findByFile(File file) {
        return transferDao.findByFile(file);
    }

    @Override
    public Transfer findLastByNumeroDemandeAndBureau(String numeroDemande, Bureau currrentBureau) {
        return transferDao.findLastByNumeroDemandeAndBureau(numeroDemande, currrentBureau);
    }

}
