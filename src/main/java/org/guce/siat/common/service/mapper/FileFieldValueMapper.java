/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.siat.common.service.mapper;

import java.util.List;
import java.util.Map;
import org.guce.siat.common.form.entities.CoreGood;
import org.guce.siat.common.form.entities.CoreStakeHolder;
import org.guce.siat.common.model.FileFieldValue;
import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileItemFieldValue;

/**
 *
 * @author boris.tomfeu
 */
public interface FileFieldValueMapper {

    List<FileFieldValue> toListOfFileFieldValue(final Map<String, String> codes, Object object, File file);

    List<FileItemFieldValue> toListOfFileItemValue(final Map<String, String> codes, CoreGood coreGoods, FileItem fileItem);
}
