package org.guce.siat.common.service;

import org.guce.siat.common.utils.exception.ValidationException;
import org.w3c.dom.Element;

/**
 * The Interface ValidationFlowService.
 */
public interface ValidationFlowService {

    /**
     * Validate flow from guce.
     *
     * @param rootElement the root element
     * @return the boolean
     * @throws ValidationException the validation exception
     */
    boolean validateFlowFromGuce(Element rootElement) throws ValidationException;

}
