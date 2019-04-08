package org.guce.siat.common.service;

import org.guce.siat.common.model.File;

/**
 *
 * @author tadzotsa
 */
public abstract class AbstractXmlConverterService implements XmlConverterService {

    @Override
    public void rollbackFile(File destinationFile, File previousFile) {
    }

}
