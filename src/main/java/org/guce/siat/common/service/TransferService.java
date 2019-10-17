/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.siat.common.service;

import java.util.List;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.Transfer;

/**
 *
 * @author yenke
 */
public interface TransferService extends AbstractService<Transfer>{
    List<Transfer> findByFile(File file);
}
