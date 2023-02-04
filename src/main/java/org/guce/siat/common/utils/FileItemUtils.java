package org.guce.siat.common.utils;

import java.util.List;

import org.guce.siat.common.model.FileItem;

/**
 * The Class FileItemUtils.
 */
public final class FileItemUtils {

    /**
     * Find file item by line number.
     *
     * @param lineNumber the line number
     * @param list the list
     * @return the file item
     */
    public static FileItem findFileItemByLineNumber(final Integer lineNumber, final List<FileItem> list) {
        for (final FileItem fileItem : list) {
            if (lineNumber.equals(fileItem.getLineNumber())) {
                return fileItem;
            }
        }
        return null;
    }

    private FileItemUtils() {
    }

}
