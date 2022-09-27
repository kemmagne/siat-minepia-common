package org.guce.siat.common.model.dto;

import java.io.Serializable;

/**
 * The Class FileFieldValue.
 */
public class FileFieldValueDTO implements Serializable {


    private final FileFieldValueIdDTO primaryKey = new FileFieldValueIdDTO();
    /**
     * The value.
     */
    private String value;

    /**
     * Gets the value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value.
     *
     * @param value the new value
     */
    public void setValue(final String value) {
        this.value = value;
    }

    public FileLiteDTO getFile() {
        return primaryKey.getFile();
    }

    public void setFile(FileLiteDTO file) {
        primaryKey.setFile(file);
    }

    public FileFieldLiteDTO getFileField() {
        return primaryKey.getFileField();
    }

    public void setFileField(FileFieldLiteDTO fileField) {
        primaryKey.setFileField(fileField);
    }

}
