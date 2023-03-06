/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.siat.common.service;


/**
 *
 * @author jim
 */
public interface ObjectMapperService {

    /**
     *
     * @param object
     * @return
     */
    public String getTraceAsJson(Object  object);

    /**
     *
     * @param json
     */
    public Object getJsonAsTrace(String  json, Class t);
}
