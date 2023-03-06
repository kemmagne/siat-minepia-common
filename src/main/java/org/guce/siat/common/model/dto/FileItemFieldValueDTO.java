package org.guce.siat.common.model.dto;

import java.io.Serializable;

/**
 * The Class FileItemFieldValue.
 */
public class FileItemFieldValueDTO implements Serializable {

    private final FileItemFieldValueIdDTO primaryKey = new FileItemFieldValueIdDTO();

    private String value;

    private Integer level;

    private String history;
    
//    private FileItemDTO fileItemDTO;
//    
//    private FileItemFieldDTO fileItemFieldDTO;

    /**
     * Gets the history.
     *
     * @return the history
     */
    public String getHistory() {
        return history;
    }

    /**
     * Sets the history.
     *
     * @param history the new history
     */
    public void setHistory(final String history) {
        this.history = history;
    }

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

    /**
     * Gets the level.
     *
     * @return the level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * Sets the level.
     *
     * @param level the new level
     */
    public void setLevel(final Integer level) {
        this.level = level;
    }


    public FileItemLiteDTO getFileItem() {
        return primaryKey.getFileItem();
       // return fileItemDTO;
    }

    public void setFileItem(FileItemLiteDTO fileItemDTO) {
        primaryKey.setFileItem(fileItemDTO);
       //this.fileItemDTO = fileItemDTO;
    }

    public FileItemFieldLiteDTO getFileItemField() {
       return primaryKey.getFileItemField();
      //  return fileItemFieldDTO;
    }

    public void setFileItemField(FileItemFieldLiteDTO fileItemFieldDTO) {
         primaryKey.setFileItemField(fileItemFieldDTO);
         //this.fileItemFieldDTO = fileItemFieldDTO;
    }


    @Override
    public String toString() {
        return "FileItemFieldValueDTO{" + "primaryKey=" + primaryKey + ", value=" + value + ", level=" + level + ", history=" + history + '}';
    }

}
