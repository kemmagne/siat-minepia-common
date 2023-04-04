/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mbezele
 */

package org.guce.siat.common.service;

import org.guce.siat.common.model.StampSignature;

/**
 * The Interface StampSignatureService.
 */
public interface StampSignatureService extends AbstractService<StampSignature> {

    /**
     * Find bureau by type and organism.
     *
     * 
     * @param name the name
     * @return object
     */
    StampSignature findByName(String name);
}
