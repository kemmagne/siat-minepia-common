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
import org.guce.siat.common.dao.DeclarationDao;
import org.guce.siat.common.model.AdditionnalDeclaration;
import org.guce.siat.common.model.Car;
import org.guce.siat.common.model.Container;
import org.guce.siat.common.model.Declaration;
import org.guce.siat.common.model.Organism;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class CarDaoImpl.
 */
@Repository("DeclarationDao")
@Transactional(propagation = Propagation.REQUIRED)
public class DeclarationDaoImpl extends AbstractJpaDaoImpl<Declaration> implements DeclarationDao {

    /**
     * Instantiates a new car dao impl.
     */
    public DeclarationDaoImpl() {
        super();
        setClasse(Declaration.class);
    }
}
