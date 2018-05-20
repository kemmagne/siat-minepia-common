package org.guce.epayment.core.dao;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.lang3.StringUtils;
import org.guce.epayment.core.models.FilterInvoice;
import org.springframework.stereotype.Repository;

@Repository
public class SearchDaoImpl implements SearchDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Object searchInvoices(final FilterInvoice filter) {

        final StringBuilder queryBuilder = new StringBuilder("SELECT " + (!filter.isCount() ? "i" : "COUNT(i)") + " FROM CoreInvoice i WHERE ");
        final StringBuilder whereBuilder = new StringBuilder();

        if (StringUtils.isNotBlank(filter.getInvoiceNumber())) {
            whereBuilder.append("i.number LIKE '%").append(filter.getInvoiceNumber()).append("%' AND ");
        }

        if (StringUtils.isNotBlank(filter.getTaxPayerNumber())) {
            whereBuilder.append("i.owner.taxPayerNumber = '").append(filter.getTaxPayerNumber()).append("' AND ");
        }

        if (StringUtils.isNotBlank(filter.getInvoiceTypeCode())) {
            whereBuilder.append("i.type.code = '").append(filter.getInvoiceTypeCode()).append("' AND ");
        }

        if (StringUtils.isNotBlank(filter.getSubTypeCode())) {
            whereBuilder.append("i.subType IS NOT NULL AND i.subType.code = '").append(filter.getSubTypeCode()).append("' AND ");
        }

        if (StringUtils.isNotBlank(filter.getBeneficiaryCode())) {
            whereBuilder.append("i.beneficiary.code = '").append(filter.getBeneficiaryCode()).append("' AND ");
        }

        if (StringUtils.isNotBlank(filter.getInvoiceStatus())) {
            whereBuilder.append("i.status = '").append(filter.getInvoiceStatus()).append("' AND ");
        }

        if (filter.getInvoiceMinAmount() != null) {
            whereBuilder.append("i.amount >= '").append(filter.getInvoiceMinAmount()).append("' AND ");
        }

        if (filter.getInvoiceMaxAmount() != null) {
            whereBuilder.append("i.amount <= '").append(filter.getInvoiceMaxAmount()).append("' AND ");
        }

        if (0 < whereBuilder.length()) {

            whereBuilder.append("i.parent IS NULL");
            queryBuilder.append(whereBuilder);
        } else {
            return new ArrayList<>();
        }

        final Query query = em.createQuery(queryBuilder.toString());
        if (!filter.isCount()) {

            query.setMaxResults(filter.getEnd() - filter.getStart());
            query.setFirstResult(filter.getStart());
        } else {
            return query.getSingleResult();
        }

        return new ArrayList<>();
    }

}
