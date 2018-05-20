package org.guce.epayment.core.services;

import org.guce.epayment.core.models.FilterInvoice;

/**
 *
 * @author tadzotsa
 */
public interface SearchService {

    Object searchInvoices(FilterInvoice filter);

}
