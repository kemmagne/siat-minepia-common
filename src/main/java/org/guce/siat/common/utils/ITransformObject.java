/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.siat.common.utils;

/**
 *
 * @author jim
 */
public interface ITransformObject <T,D> {
    D transformToDto(T entity, Class entityClass); 
    //T transformToEntity(D dto); 
}
