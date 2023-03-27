package org.guce.siat.common.dao.impl;

import java.util.Objects;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import org.guce.siat.common.dao.FileMarshallDao;
import org.guce.siat.common.dao.impl.AbstractJpaDaoImpl;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileMarshall;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("fileMarshallDao")
@Transactional(
   propagation = Propagation.REQUIRED
)
public class FileMarshallDaoImpl extends AbstractJpaDaoImpl implements FileMarshallDao {

   private static final Logger LOG = LoggerFactory.getLogger(FileMarshallDaoImpl.class);


   public FileMarshallDaoImpl() {
      this.setClasse(FileMarshall.class);
   }

   public FileMarshall findByFile(File file) {
      TypedQuery query = this.entityManager.createQuery("SELECT m FROM FileMarshall m WHERE m.file= :file", FileMarshall.class);
      query.setParameter("file", file);

      try {
         return (FileMarshall)query.getSingleResult();
      } catch (NonUniqueResultException var4) {
         LOG.error(Objects.toString(var4));
         return null;
      }
   }

}
