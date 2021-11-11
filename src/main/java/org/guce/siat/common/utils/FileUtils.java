package org.guce.siat.common.utils;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.BooleanUtils;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileFieldValue;
import org.guce.siat.common.model.FileItem;
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
        for (FileFieldValue dffv : destFileFieldValues) {
            if (!BooleanUtils.toBoolean(dffv.getFileField().getUpdatable())) {
                continue;
            }
            for (FileFieldValue sffv : sourceFileFieldValues) {
                if (dffv.getFileField().getCode().equals(sffv.getFileField().getCode())) {
                    dffv.setValue(sffv.getValue());
                    break;
                }
            }
        }

        for (FileItem sfi : source.getFileItemsList()) {
            for (FileItem dfi : dest.getFileItemsList()) {
                if (sfi.getLineNumber().equals(dfi.getLineNumber())) {
                    sfi.setQuantity(dfi.getQuantity());
                    sfi.setFobValue(dfi.getFobValue());
                    break;
                }
            }
        }

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
        if (file.getParent() != null && (file.getReferenceSiat().startsWith(InformationSystemCode.CC.name()) || (file.getReferenceSiat().startsWith(InformationSystemCode.CT.name()) && Arrays.asList(FileTypeCode.CCT_CSV, FileTypeCode.CCT_CT_E_PVE, FileTypeCode.VT_MINEPIA).contains(file.getFileType().getCode())))) {
            numeroDossier = file.getParent().getNumeroDossier();
        }

        return numeroDossier;
    }

    private FileUtils() {
    }

}
