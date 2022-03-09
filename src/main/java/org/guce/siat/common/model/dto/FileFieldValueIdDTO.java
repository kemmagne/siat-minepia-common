package org.guce.siat.common.model.dto;

import java.io.Serializable;


/**
 * The Class FileFieldValueId.
 */
public class FileFieldValueIdDTO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 662058985247014320L;

    /**
     * The file.
     */

    private FileLiteDTO file;

    /**
     * The file field.
     */
    private FileFieldLiteDTO fileField;

    /**
     * Gets the file.
     *
     * @return the file
     */
    public FileLiteDTO getFile() {
        return file;
    }

    /**
     * Sets the file.
     *
     * @param file the new file
     */
    public void setFile(FileLiteDTO file) {
        this.file = file;
    }

    /**
     * Gets the file field.
     *
     * @return the file field
     */
    public FileFieldLiteDTO getFileField() {
        return fileField;
    }

    /**
     * Sets the file field.
     *
     * @param fileField the new file field
     */
    public void setFileField(FileFieldLiteDTO fileField) {
        this.fileField = fileField;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((file == null) ? 0 : file.hashCode());
        result = prime * result + ((fileField == null) ? 0 : fileField.hashCode());
        return result;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof FileFieldValueIdDTO)) {
            return false;
        }
        final FileFieldValueIdDTO other = (FileFieldValueIdDTO) obj;
        if (file == null) {
            if (other.file != null) {
                return false;
            }
        } else if (!file.equals(other.file)) {
            return false;
        }
        if (fileField == null) {
            if (other.fileField != null) {
                return false;
            }
        } else if (!fileField.equals(other.fileField)) {
            return false;
        }
        return true;
    }

//    @Override
//    public String toString() {
//        return "FileFieldValueIdDTO{" + "file=" + file + ", fileField=" + fileField + '}';
//    }

}
