package org.guce.siat.common.service;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Map;
import javax.persistence.PersistenceException;
import org.apache.chemistry.opencmis.commons.exceptions.CmisConnectionException;
import org.guce.siat.common.model.File;
import org.guce.siat.common.utils.exception.ValidationException;

/**
 *
 * @author tadzotsa
 */
public abstract class AbstractXmlConverterService implements XmlConverterService {

    @Override
    public void rollbackFile(File destinationFile, File previousFile) {
    }

    @Override
    public File saveReceivedFileAndAttachmentsAndExecuteFlow(Serializable document, Map<String, byte[]> attachementsMap) throws ParseException, PersistenceException, NullPointerException, ValidationException, CmisConnectionException, IOException {
        return null;
    }

}
