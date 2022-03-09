package org.guce.siat.common.dao;

import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileMarshall;

public interface FileMarshallDao extends AbstractJpaDao {

    FileMarshall findByFile(File var1);
}
