/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.siat.common.utils;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.modelmapper.ModelMapper;

/**
 *
 * @author jim
 */
public class TransformObject<T, D> extends AbstractTransformObject<T, D> {

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public D transformToDto(T entity, Class entityClass) {
        
        D result = null;
        Class objectDto = null;

        objectDto = this.getObjectDTO(entityClass);
        if (objectDto != null) {
            // Logger.getLogger(" ObjectDto est non null " + objectDto);
            result = (D) modelMapper.map(entity, objectDto);
        }

        return result;

    }

    public Class getObjectDTO(Class entityClass) {
        Class objectDto = null;

        //Class objectEntity = Class.forName(entity.getClass().getName());
        // avoir le DTO d'une entité
        //renvoit le DTO avec le package
        String packageName = entityClass.getPackage().getName();
        //renvoit le DTO sans le package
        String packageNameDTO = packageName + ".dto";
        String simpleNameDTO = entityClass.getSimpleName() + "DTO";

        try {
            if (entityClass.getClass().equals(Object.class)) {
                objectDto = null;
                return objectDto;
            }
            //Logger.getLogger(getClass().getName()).log(Level.INFO, " Record superclass **********  " + entityClass.getSuperclass(), entityClass.getClass());

            objectDto = Class.forName(packageNameDTO + "." + simpleNameDTO);
            Logger.getLogger(" Object transformé " + objectDto);

        }
        catch (ClassNotFoundException ex) {

            Logger.getLogger(getClass().getName()).log(Level.INFO, " dans le catch **********  " + entityClass.getSuperclass(), entityClass.getClass());

            Logger.getLogger(TransformObject.class.getName()).log(Level.SEVERE, null, ex);
            return getObjectDTO(entityClass.getSuperclass());
        }
        return objectDto;
    }
}
