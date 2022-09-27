package org.guce.siat.common.dao;

import java.util.List;

import org.guce.siat.common.model.HistoryTrace;
import org.guce.siat.common.model.User;
import org.guce.siat.common.utils.FilterTrace;

/**
 * The Interface HistoryTraceDao.
 */
public interface HistoryTraceDao extends AbstractJpaDao<HistoryTrace> {

    <T> void makeHistory(T entity, String operation, User user);

    <C, S extends C> void makeHistory(S entity, String operation, User user, Class<C> clazz);

    List<HistoryTrace> findTraceByUser(User coreUser);

    List<HistoryTrace> findTraceByFilter(FilterTrace filterTrace);

    List<String> findModelTrace();
}
