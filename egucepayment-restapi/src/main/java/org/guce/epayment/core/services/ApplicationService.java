package org.guce.epayment.core.services;

/**
 *
 * @author tadzotsa
 */
public interface ApplicationService {

    String getAppVersion();

    String getAppEnv();

    String getColSep();

    String getRowSep();

    String getRepeatableSep();

    String getCustomColSep();

    String getCustomRowSep();

}
