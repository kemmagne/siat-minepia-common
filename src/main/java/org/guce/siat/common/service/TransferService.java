package org.guce.siat.common.service;

import java.util.List;
import org.guce.siat.common.model.Bureau;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.Transfer;

/**
 *
 * @author yenke
 */
public interface TransferService extends AbstractService<Transfer> {

    List<Transfer> findByFile(File file);

    Transfer findLastByNumeroDemandeAndBureau(String numeroDemande, Bureau currrentBureau);

}
