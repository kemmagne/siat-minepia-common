/**
 *
 */
package org.guce.siat.common.dao.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.TypedQuery;

import org.guce.siat.common.dao.CarDao;
import org.guce.siat.common.dao.ContainerDao;
import org.guce.siat.common.model.Car;
import org.guce.siat.common.model.Container;
import org.guce.siat.common.model.Organism;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class CarDaoImpl.
 */
@Repository("ContainerDao")
@Transactional(propagation = Propagation.REQUIRED)
public class ContainerDaoImpl extends AbstractJpaDaoImpl<Container> implements ContainerDao
{

	/**
	 * Instantiates a new car dao impl.
	 */
	public ContainerDaoImpl()
	{
		super();
		setClasse(Container.class);
	}
}
