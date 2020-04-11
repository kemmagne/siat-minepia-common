package org.guce.siat.common.utils;

import java.util.List;
import org.apache.commons.lang3.BooleanUtils;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileFieldValue;

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
    }

    public static File getRootFile(File current) {

        File root, parent = current;

        do {
            root = parent;
            parent = root.getParent();
        } while (parent != null);

        return root;
    }

    private FileUtils() {
    }

}
