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
public class WrapperFileItemFieldValue implements Serializable {
    FileItemFieldValue fileItemFieldValue;
    String oldValue;

    public FileItemFieldValue getFileItemFieldValue() {
        return fileItemFieldValue;
    }

    public void setFileItemFieldValue(FileItemFieldValue fileItemFieldValue) {
        this.fileItemFieldValue = fileItemFieldValue;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    
}
