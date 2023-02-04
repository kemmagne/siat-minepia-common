package org.guce.siat.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.BooleanUtils;
import org.guce.siat.common.lookup.ServiceUtility;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileFieldValue;
import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.FileItemFieldValue;
import org.guce.siat.common.service.FileFieldValueService;
import org.guce.siat.common.service.FileItemFieldValueService;
import org.guce.siat.common.service.FileItemService;
import org.guce.siat.common.utils.enums.FileTypeCode;
import org.guce.siat.common.utils.enums.InformationSystemCode;

/**
 *
 * @author tadzotsa
 */
public class FileUtils {

    public static void applyModifications(File source, File dest) {

        if (!source.getFileType().equals(dest.getFileType())) {
            return;
        }

        List<FileFieldValue> sourceFileFieldValues = source.getFileFieldValueList();
        List<FileFieldValue> destFileFieldValues = dest.getFileFieldValueList();
        List<FileFieldValue> toUpdateFfv = new ArrayList<>();
        for (FileFieldValue dffv : destFileFieldValues) {
            if (!BooleanUtils.toBoolean(dffv.getFileField().getUpdatable())) {
                continue;
            }
            for (FileFieldValue sffv : sourceFileFieldValues) {
                if (dffv.getFileField().getCode().equals(sffv.getFileField().getCode()) && !dffv.getValue().equals(sffv.getValue())) {
                    dffv.setValue(sffv.getValue());
                    toUpdateFfv.add(dffv);
                    break;
                }
            }
        }

        List<FileItem> toUpdateFi = new ArrayList<>();
        List<FileItemFieldValue> toUpdateFifv = new ArrayList<>();
        for (FileItem dfi : dest.getFileItemsList()) {
            for (FileItem sfi : source.getFileItemsList()) {
                if (dfi.getLineNumber().equals(sfi.getLineNumber())) {
                    dfi.setQuantity(sfi.getQuantity());
                    dfi.setFobValue(sfi.getFobValue());
                    toUpdateFi.add(dfi);
                    toUpdateFifv.addAll(applyModificationsOnFileItemFields(sfi, dfi));
                    break;
                }
            }
        }

        FileFieldValueService fileFieldValueService = ServiceUtility.getBean(FileFieldValueService.class);
        FileItemService fileItemService = ServiceUtility.getBean(FileItemService.class);
        FileItemFieldValueService fileItemFieldValueService = ServiceUtility.getBean(FileItemFieldValueService.class);

        fileFieldValueService.saveOrUpdateList(toUpdateFfv);
        fileItemService.saveOrUpdateList(toUpdateFi);
        fileItemFieldValueService.saveOrUpdateList(toUpdateFifv);
    }

    private static List<FileItemFieldValue> applyModificationsOnFileItemFields(FileItem source, FileItem dest) {

        List<FileItemFieldValue> sourceFileItemFieldValues = source.getFileItemFieldValueList();
        List<FileItemFieldValue> destFileItemFieldValues = dest.getFileItemFieldValueList();
        List<FileItemFieldValue> toUpdateFifv = new ArrayList<>();
        for (FileItemFieldValue dfifv : destFileItemFieldValues) {
            if (!BooleanUtils.toBoolean(dfifv.getFileItemField().getUpdatable())) {
                continue;
            }
            for (FileItemFieldValue sfifv : sourceFileItemFieldValues) {
                if (dfifv.getFileItemField().getCode().equals(sfifv.getFileItemField().getCode()) && !dfifv.getValue().equals(sfifv.getValue())) {
                    dfifv.setValue(sfifv.getValue());
                    toUpdateFifv.add(dfifv);
                    break;
                }
            }
        }
        return toUpdateFifv;
    }

    public static void retrieveNonExisting(File root, File currentFile) {

        List<FileFieldValue> rootFileFieldValues = root.getFileFieldValueList();
        List<FileFieldValue> fileFieldValues = currentFile.getFileFieldValueList();

        for (FileFieldValue rffv : rootFileFieldValues) {

            for (FileFieldValue ffv : fileFieldValues) {

            }
        }
    }

    public static File getRootFile(File current) {

        File root, parent = current;

        do {
            root = parent;
            parent = root.getParent();
        } while (parent != null);

        return root;
    }

    public static String getNumeroDossierGuce(File file) {

        String numeroDossier = file.getNumeroDossier();
        if ((file.getParent() != null || file.getNumeroDossierBase() != null) && (file.getReferenceSiat().startsWith(InformationSystemCode.CC.name()) || (file.getReferenceSiat().startsWith(InformationSystemCode.CT.name()) && Arrays.asList(FileTypeCode.CCT_CSV, FileTypeCode.CCT_CT_E_PVE, FileTypeCode.CCS_MINSANTE, FileTypeCode.VT_MINEPIA, FileTypeCode.VT_MINEPDED, FileTypeCode.PIVPSRP_MINADER).contains(file.getFileType().getCode())))) {
            if (file.getParent() != null) {
                numeroDossier = file.getParent().getNumeroDossier();
            } else {
                numeroDossier = file.getNumeroDossierBase();
            }
        }

        return numeroDossier;
    }

    private FileUtils() {
    }

}
