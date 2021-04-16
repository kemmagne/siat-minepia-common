package org.guce.siat.common.service;

import java.util.List;

import org.guce.siat.common.model.MessageToSend;




/**
 * The Interface MessageToSendService.
 */
public interface MessageToSendService extends AbstractService<MessageToSend>
{


	/**
	 * Find by resendRetryNumber.
	 * 
	 * @return the list
	 */
	List<MessageToSend> findByRetryNumberLessThanFive();

}
