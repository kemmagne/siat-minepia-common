/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.siat.common.service;

import java.util.List;
import org.guce.siat.common.model.HistoryTrace;
import org.guce.siat.common.model.User;
import org.guce.siat.common.utils.FilterTrace;

/**
 *
 * @author jim
 */
public interface HistoryTraceService extends AbstractService<HistoryTrace> {
        <T> void makeHistory(T entity, String operation, User user);
        <C,S extends C> void makeHistory(S entity, String operation, User user, Class<C> clazz);

	List<HistoryTrace> findTraceByUser(User coreUser);

	List<HistoryTrace> findTraceByFilter(FilterTrace filterTrace);
        
        List<String> findModelTrace();
}
