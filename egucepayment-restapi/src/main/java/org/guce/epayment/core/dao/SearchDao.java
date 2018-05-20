package org.guce.epayment.core.dao;

import org.guce.epayment.core.models.FilterInvoice;

/**
 *
 * @author tadzotsa
 */
public interface SearchDao {

    Object searchInvoices(FilterInvoice filter);

}
