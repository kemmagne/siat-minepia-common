package org.guce.epayment.core.services;

import org.guce.epayment.core.dao.SearchDao;
import org.guce.epayment.core.models.FilterInvoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchDao searchDao;

    @Override
    public Object searchInvoices(final FilterInvoice filter) {

        return searchDao.searchInvoices(filter);
    }

}
