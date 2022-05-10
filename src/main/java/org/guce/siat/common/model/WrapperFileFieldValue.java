/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.siat.common.model;

import java.io.Serializable;

/**
 *
 * @author jim
 */
public class WrapperFileFieldValue implements Serializable {
    FileFieldValue filefieldValue;
    String oldValue;

    public FileFieldValue getFilefieldValue() {
        return filefieldValue;
    }

    public void setFilefieldValue(FileFieldValue filefieldValue) {
        this.filefieldValue = filefieldValue;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    
}
