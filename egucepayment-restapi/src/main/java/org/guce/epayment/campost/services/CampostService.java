package org.guce.epayment.campost.services;

import java.util.List;
import java.util.Map;
import org.guce.epayment.campost.exceptions.NoCampostAccountException;

/**
 *
 * @author tadzotsa
 */
public interface CampostService {

    Map<String, String> first(List<Map<String, Object>> invoices, Map<String, String> requestParams,
            String beneficiaryCode) throws NoCampostAccountException;

    Map<String, String> second(Map<String, String> infos, String paymentReference, boolean cancel);

}
