/**
 *
 */
package org.guce.siat.common.dao.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.TypedQuery;
import org.guce.siat.common.dao.AdditionnalDeclarationDao;

import org.guce.siat.common.dao.CarDao;
import org.guce.siat.common.dao.ContainerDao;
import org.guce.siat.common.model.AdditionnalDeclaration;
import org.guce.siat.common.model.Car;
import org.guce.siat.common.model.Container;
import org.guce.siat.common.model.Organism;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class CarDaoImpl.
 */
@Repository("AdditionnalDeclarationDao")
@Transactional(propagation = Propagation.REQUIRED)
public class AdditionnalDeclarationDaoImpl extends AbstractJpaDaoImpl<AdditionnalDeclaration> implements AdditionnalDeclarationDao {

    /**
     * Instantiates a new car dao impl.
     */
    public AdditionnalDeclarationDaoImpl() {
        super();
        setClasse(AdditionnalDeclaration.class);
    }
}
