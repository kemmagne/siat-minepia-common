/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.siat.common.service.mapper.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.ArrayUtils;
import org.guce.siat.common.dao.FileFieldDao;
import org.guce.siat.common.dao.FileItemFieldDao;
import org.guce.siat.common.form.entities.CoreGood;
import org.guce.siat.common.form.entities.CoreStakeHolder;
import org.guce.siat.common.model.FileField;
import org.guce.siat.common.model.FileFieldValue;
import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileItemField;
import org.guce.siat.common.model.FileItemFieldValue;
import org.guce.siat.common.service.mapper.FileFieldValueMapper;
import org.guce.siat.common.utils.enums.FileTypeCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author boris.tomfeu
 */
@Service("fileFieldValueMapperImpl")
@Transactional(readOnly = true)
public class FileFieldValueMapperImpl implements FileFieldValueMapper {

    @Autowired
    private FileFieldDao fileFieldDao;

    @Autowired
    private FileItemFieldDao fileItemFieldDao;

    @Override
    public List<FileFieldValue> toListOfFileFieldValue(Map<String, String> codes, Object object, File file) {
        List<FileFieldValue> fileFieldValues = new ArrayList<>();

        Class clazz = object.getClass();
        final Field[] fields = clazz.getDeclaredFields();
        final Method[] methods = clazz.getMethods();

        Method method = methods[0];
        Type type = method.getGenericReturnType();
        List<Field> fieldList = Arrays.asList(fields);
        Map<String, Class> mapAttribut = new HashMap<>();

        for (Field field : fieldList) {
            mapAttribut.put(field.getName(), field.getType());
        }

        FileFieldValue fileFieldValue;
        FileField fileField;
        for (Map.Entry<String, String> entry : codes.entrySet()) {
            try {
                fileFieldValue = new FileFieldValue();
                Class fieldType = mapAttribut.get(entry.getKey());
                String getter;
                if (!boolean.class.equals(fieldType)) {
                    getter = "get" + entry.getKey().substring(0, 1).toUpperCase() + entry.getKey().substring(1);
                } else {
                    getter = "is" + entry.getKey().substring(0, 1).toUpperCase() + entry.getKey().substring(1);
                }

                Method m = object.getClass().getMethod(getter);

                fileField = fileFieldDao.findFileFieldByCodeAndFileType(entry.getValue(), FileTypeCode.CCT_CT_E);
                fileFieldValue.setFileField(fileField);
                fileFieldValue.setFile(file);
                Object invoke = m.invoke(object);
                
                if (invoke instanceof BigDecimal) {
                    BigDecimal data = (BigDecimal) invoke;
                    fileFieldValue.setValue(data.toString());
                } else if (invoke instanceof Long) {
                    fileFieldValue.setValue(String.valueOf(invoke));
                } else {
                    fileFieldValue.setValue((String) m.invoke(object));
                }

                fileFieldValues.add(fileFieldValue);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(FileFieldValueMapperImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return fileFieldValues;
    }

    @Override
    public List<FileItemFieldValue> toListOfFileItemValue(Map<String, String> codes, CoreGood coreGood, FileItem fileItem) {
        List<FileItemFieldValue> fileItemFieldValues = new ArrayList<>();

        FileItemFieldValue fileItemFieldValue;
        FileItemField fileItemField;

        Class clazz = coreGood.getClass();
        final Field[] fields = clazz.getDeclaredFields();
        List<Field> fieldList = Arrays.asList(fields);
        Map<String, Class> mapAttribut = new HashMap<>();

        for (Field field : fieldList) {
            mapAttribut.put(field.getName(), field.getType());
        }

        for (Map.Entry<String, String> entry : codes.entrySet()) {

            try {

                Class fieldType = mapAttribut.get(entry.getKey());
                String getter;
                if (!boolean.class.equals(fieldType)) {
                    getter = "get" + entry.getKey().substring(0, 1).toUpperCase() + entry.getKey().substring(1);
                } else {
                    getter = "is" + entry.getKey().substring(0, 1).toUpperCase() + entry.getKey().substring(1);
                }

                fileItemFieldValue = new FileItemFieldValue();
                fileItemField = fileItemFieldDao.findFileItemFieldByCodeAndFileType(entry.getValue(), FileTypeCode.CCT_CT_E);
                fileItemFieldValue.setFileItemField(fileItemField);
                fileItemFieldValue.setFileItem(fileItem);
                Method m = coreGood.getClass().getMethod(getter);
                Object invoke = m.invoke(coreGood);
                if (invoke instanceof BigDecimal) {
                    BigDecimal data = (BigDecimal) invoke;
                    fileItemFieldValue.setValue(data.toString());
                } else if (invoke instanceof Long) {
                    fileItemFieldValue.setValue(String.valueOf(invoke));
                } else {
                    fileItemFieldValue.setValue((String) invoke);
                }
                fileItemFieldValues.add(fileItemFieldValue);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(FileFieldValueMapperImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return fileItemFieldValues;
    }

}
