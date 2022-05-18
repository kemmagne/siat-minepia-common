/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.siat.common.model.dto;

import java.io.Serializable;

/**
 *
 * @author mbezele
 */
public class FileItemFieldLiteDTO implements Serializable{
    
	private Long id;

	/** The code. */
	private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
        
}
