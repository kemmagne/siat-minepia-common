package org.guce.epayment.core.utils;

import java.math.BigDecimal;

/**
 *
 * @author tadzotsa
 */
public interface Constants {

    static final BigDecimal BIG_DECIMAL_100 = new BigDecimal(100);

    static final BigDecimal BIG_DECIMAL_MINUS_ONE = new BigDecimal(-1);

    static final Integer ZERO = 0;

    static final Integer ONE = 1;

    static final Integer TWO = 2;

    static final Integer THREE = 3;

    static final Integer HUNDRED = 100;

    static final String ALL = "ALL".intern();

    static final int DAY_OF_WEEK = 7;

    static final int DAY_OF_MONTH = 5;

    static final int DAY_OF_YEAR = 6;

    static final String GLOBAL_SEPERATOR = "#".intern();

    static final String UK_USER_LOGIN = "login".intern();

    static final String UK_ROLE_NAME = "name".intern();

    static final String LOCALE_FR = "fr".intern();

    static final String LOCALE_EN = "en".intern();

}
