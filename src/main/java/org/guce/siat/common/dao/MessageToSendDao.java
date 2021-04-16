package org.guce.siat.common.dao;

import java.util.List;

import org.guce.siat.common.model.Car;
import org.guce.siat.common.model.MessageToSend;
import org.guce.siat.common.model.Organism;




/**
 * The Interface MessageToSendDao.
 */
public interface MessageToSendDao extends AbstractJpaDao<MessageToSend>
{

	
	List<MessageToSend> findByRetryNumberLessThanFive();

}
