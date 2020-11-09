package org.guce.siat.common.utils;

/**
 *
 * @author ht
 */
public enum ParamsConstants {

    BACKUP_RECEVED_MESSAGES("backup.received.messages", "Backup received messages", "Sauvegarde des messages entrants");

    private final String paramsName;
    private final String paramsLabelEn;
    private final String paramsLabelFr;

    private ParamsConstants(String paramsName, String paramsLabelEn, String paramsLabelFr) {
        this.paramsName = paramsName;
        this.paramsLabelEn = paramsLabelEn;
        this.paramsLabelFr = paramsLabelFr;
    }

    public String getParamsName() {
        return paramsName;
    }

    public String getParamsLabelEn() {
        return paramsLabelEn;
    }

    public String getParamsLabelFr() {
        return paramsLabelFr;
    }

}
