/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.siat.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.guce.siat.common.service.ObjectMapperService;

/**
 *
 * @author jim
 */
public class ObjectMapperFacade implements ObjectMapperService{
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public String getTraceAsJson(Object object) {
        
        
        mapper.enable(SerializationFeature.INDENT_OUTPUT); 
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);  
         
        
        String json = null;
        try {
            json = mapper.writeValueAsString(object);
        }
        catch (IOException ex) {
            Logger.getLogger(ObjectMapperFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("ResultingJSONstring = " + json);
        //System.out.println(json);
        return json;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getJsonAsTrace(String value, Class t ) {
        Object objFromJson = null;
        try {
            //Convert to json to person object
            objFromJson = mapper.readValue(value, t);
            System.out.println(objFromJson);
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        catch (JsonProcessingException ex) {
            Logger.getLogger(ObjectMapperFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println("Objet transformé " + objFromJson.toString());
        return objFromJson;
    }
    
}
