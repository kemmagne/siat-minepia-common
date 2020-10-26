package org.guce.siat.common.dao.impl;

import java.util.Collections;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import org.guce.siat.common.dao.TransferDao;
import org.guce.siat.common.model.Bureau;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.Transfer;
import org.guce.siat.common.model.Transfer_;
import org.guce.siat.common.model.User;
import org.guce.siat.common.model.User_;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author yenke
 */
@Repository("transferDao")
@Transactional
public class TransferDaoImpl extends AbstractJpaDaoImpl<Transfer> implements TransferDao {

    public TransferDaoImpl() {
        setClasse(Transfer.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Transfer> findByFile(File file) {

        if (file == null) {
            return Collections.emptyList();
        }

        CriteriaBuilder builder = super.entityManager.getCriteriaBuilder();
        CriteriaQuery cq = builder.createQuery(getClasse());
        Root<Transfer> root = cq.from(getClasse());
        cq.where(builder.equal(root.get(Transfer_.file), file));
        TypedQuery<Transfer> query = super.entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public Transfer findLastByNumeroDemandeAndBureau(String numeroDemande, Bureau currrentBureau) {

        CriteriaBuilder builder = super.entityManager.getCriteriaBuilder();
        CriteriaQuery cq = builder.createQuery(getClasse());
        Root<Transfer> root = cq.from(getClasse());
        Join<Transfer, User> assignedUser = root.join(Transfer_.assignedUser);
        cq.where(builder.and(
                builder.equal(assignedUser.get(User_.administration), currrentBureau),
                builder.equal(root.get(Transfer_.numeroDemande), numeroDemande)
        ));

        TypedQuery<Transfer> query = super.entityManager.createQuery(cq);
        query.setMaxResults(1);

        try {
            return query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

}
