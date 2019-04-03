package org.guce.siat.common.service.impl;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.FileMarshallDao;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileMarshall;
import org.guce.siat.common.service.FileMarshallService;
import org.guce.siat.common.service.impl.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("fileMarshallService")
@Transactional(
   readOnly = true
)
public class FileMarshallServiceImpl extends AbstractServiceImpl implements FileMarshallService {

   @Autowired
   private FileMarshallDao fileMarshallDao;


   public AbstractJpaDao getJpaDao() {
      return this.fileMarshallDao;
   }

   public void setJpaDao(AbstractJpaDao jpaDao) {
      this.fileMarshallDao = (FileMarshallDao)jpaDao;
   }

   public FileMarshall findByFile(File file) {
      return this.fileMarshallDao.findByFile(file);
   }
}
