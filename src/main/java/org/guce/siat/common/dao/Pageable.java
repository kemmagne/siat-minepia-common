package org.guce.siat.common.dao;

import java.util.List;
import java.util.Map;

/**
 *
 * @author ayefou
 * @param <T>
 */
public interface Pageable<T> {

    static final int MATCH_MODE_EQUAL = 1;

    static final int MATCH_MODE_CONTAINS = 2;

    static final int MATCH_MODE_END_WITH = 3;

    static final int MATCH_MODE_START_WITH = 4;

    static final int MATCH_MODE_LESS_THAN = 5;

    static final int MATCH_MODE_MORE_THAN = 6;

    static final int MATCH_MODE_LESS_OR_EQUAL_THAN = 7;

    static final int MATCH_MODE_MORE_OR_EQUAL_THAN = 8;

    static final int MATCH_MODE_NOT_EQUAL = 9;

    static final int MATCH_MODE_NOT_CONTAINS = 10;

    static final int MATCH_MODE_NOT_END_WITH = 11;

    static final int MATCH_MODE_NOT_START_WITH = 12;

    static final int MATCH_MODE_IN = 13;

    static final int MATCH_MODE_NOT_IN = 14;

    int paginateCount(Map<String, ? extends Object> filters, int matchMode, Map<String, Integer> matchModes);

    /**
     *
     * @param filters
     * @param sortField
     * @param sortOrder
     * @param first
     * @param limit
     * @param end
     * @param matchModes
     * @param matchMode une des valeur
     * MATCH_MODE_EQUAL,MATCH_MODE_CONTAINS,MATCH_MODE_END_WITH,MATCH_MODE_START_WITH
     * @return
     */
    List<T> paginate(Map<String, ? extends Object> filters, String sortField, String sortOrder, int first, int limit, int matchMode, Map<String, Integer> matchModes);

}
