package org.guce.siat.common.service;

import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileMarshall;
import org.guce.siat.common.service.AbstractService;

public interface FileMarshallService extends AbstractService {

   FileMarshall findByFile(File var1);
}
