package org.guce.siat.common.model;

import java.io.Serializable;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class UserAuthorityFileType.
 */
@Entity
@Table(name = "USER_AUTHORIRTY_FILE_TYPE")
@XmlRootElement
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.userAuthority", joinColumns = @JoinColumn(name = "USER_AUTHORITY_ID")),
    @AssociationOverride(name = "primaryKey.fileType", joinColumns = @JoinColumn(name = "FILE_TYPE_ID"))}
)
public class UserAuthorityFileType extends AbstractModel implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -9098449738561410816L;

    /**
     * The primary key.
     */
    @EmbeddedId
    private UserAuthorityFileTypeId primaryKey = new UserAuthorityFileTypeId();

    /**
     * Gets the primary key.
     *
     * @return the primary key
     */
    public UserAuthorityFileTypeId getPrimaryKey() {
        return primaryKey;
    }

    /**
     * Sets the primary key.
     *
     * @param primaryKey the new primary key
     */
    public void setPrimaryKey(final UserAuthorityFileTypeId primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     * Gets the user authority.
     *
     * @return the user authority
     */
    @Transient
    public UserAuthority getUserAuthority() {
        return primaryKey.getUserAuthority();
    }

    /**
     * Gets the file type.
     *
     * @return the file type
     */
    @Transient
    public FileType getFileType() {
        return primaryKey.getFileType();
    }

    /**
     * Sets the user authority.
     *
     * @param userAuthority the new user authority
     */
    public void setUserAuthority(final UserAuthority userAuthority) {
        primaryKey.setUserAuthority(userAuthority);
    }

    /**
     * Sets the file type.
     *
     * @param fileType the new file type
     */
    public void setFileType(final FileType fileType) {
        primaryKey.setFileType(fileType);
    }

}
