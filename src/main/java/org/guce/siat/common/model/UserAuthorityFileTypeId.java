package org.guce.siat.common.model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * The Class UserAuthorityFileTypeId.
 */
@Embeddable
public class UserAuthorityFileTypeId implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 662058985247014320L;

    /**
     * The user authority.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_AUTHORITY_ID", referencedColumnName = "ID", updatable = false)
    private UserAuthority userAuthority;

    /**
     * The file type.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FILE_TYPE_ID", referencedColumnName = "ID", updatable = false)
    private FileType fileType;

    /**
     * Gets the user authority.
     *
     * @return the user authority
     */
    public UserAuthority getUserAuthority() {
        return userAuthority;
    }

    /**
     * Sets the user authority.
     *
     * @param userAuthority the new user authority
     */
    public void setUserAuthority(final UserAuthority userAuthority) {
        this.userAuthority = userAuthority;
    }

    /**
     * Gets the file type.
     *
     * @return the file type
     */
    public FileType getFileType() {
        return fileType;
    }

    /**
     * Sets the file type.
     *
     * @param fileType the new file type
     */
    public void setFileType(final FileType fileType) {
        this.fileType = fileType;
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
        result = prime * result + ((fileType == null) ? 0 : fileType.hashCode());
        result = prime * result + ((userAuthority == null) ? 0 : userAuthority.hashCode());
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
        if (!(obj instanceof UserAuthorityFileTypeId)) {
            return false;
        }
        final UserAuthorityFileTypeId other = (UserAuthorityFileTypeId) obj;
        if (fileType == null) {
            if (other.fileType != null) {
                return false;
            }
        } else if (!fileType.equals(other.fileType)) {
            return false;
        }
        if (userAuthority == null) {
            if (other.userAuthority != null) {
                return false;
            }
        } else if (!userAuthority.equals(other.userAuthority)) {
            return false;
        }
        return true;
    }

}
