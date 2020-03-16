package org.guce.siat.common.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.guce.siat.common.utils.enums.FileTypeCode;

/**
 * The Class FileType.
 */
@javax.persistence.Entity
@Table(name = "FILE_TYPE")
@XmlRootElement
public class FileType extends AbstractModel implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The id.
     */
    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "FILE_TYPE_SEQ", sequenceName = "FILE_TYPE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FILE_TYPE_SEQ")
    private Long id;

    /**
     * The code.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "CODE")
    private FileTypeCode code;

    /**
     * The label fr.
     */
    @Column(name = "LABEL_FR")
    private String labelFr;

    /**
     * The label en.
     */
    @Column(name = "LABEL_EN")
    private String labelEn;

    /**
     * The role list.
     */
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "FILE_TYPE_AUTHORITY", joinColumns
            = {
                @JoinColumn(name = "FILE_TYPE_ID", referencedColumnName = "ID", updatable = false)}, inverseJoinColumns
            = {
                @JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID", updatable = false)})
    private List<Authority> roleList;

    /**
     * The user authority file type list.
     */
    @OneToMany(mappedBy = "primaryKey.fileType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserAuthorityFileType> userAuthorityFileTypeList;

    /**
     * The file field list.
     */
    @OneToMany(mappedBy = "fileType")
    private List<FileField> fileFieldList;

    /**
     * The file item field list.
     */
    @OneToMany(mappedBy = "fileType")
    private List<FileItemField> fileItemFieldList;

    /**
     * The user authority file type list.
     */
    @OneToMany(mappedBy = "primaryKey.fileType", fetch = FetchType.LAZY)
    private List<FileTypeStep> fileTypeStepList;

    /**
     * file type flow list.
     */
    @OneToMany(mappedBy = "pk.fileType", fetch = FetchType.LAZY)
    private List<FileTypeFlow> fileTypeFlowList;

    /**
     * The user authority file type list.
     */
    @OneToMany(mappedBy = "fileType", fetch = FetchType.LAZY)
    private List<FileTypeService> fileTypeServiceList;

    @Transient
    private List<Step> stepList;

    @Transient
    private List<Flow> flowList;

    @Transient
    private List<Service> serviceList;

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.model.AbstractModel#getId()
     */
    @Override
    public Long getId() {
        return id;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.model.AbstractModel#setId(java.lang.Long)
     */
    @Override
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Gets the code.
     *
     * @return the code
     */
    public FileTypeCode getCode() {
        return code;
    }

    /**
     * Sets the code.
     *
     * @param code the new code
     */
    public void setCode(final FileTypeCode code) {
        this.code = code;
    }

    /**
     * Gets the label fr.
     *
     * @return the label fr
     */
    public String getLabelFr() {
        return labelFr;
    }

    /**
     * Sets the label fr.
     *
     * @param labelFr the new label fr
     */
    public void setLabelFr(final String labelFr) {
        this.labelFr = labelFr;
    }

    /**
     * Gets the label en.
     *
     * @return the label en
     */
    public String getLabelEn() {
        return labelEn;
    }

    /**
     * Sets the label en.
     *
     * @param labelEn the new label en
     */
    public void setLabelEn(final String labelEn) {
        this.labelEn = labelEn;
    }

    /**
     * Gets the user authority file type list.
     *
     * @return the user authority file type list
     */
    public List<UserAuthorityFileType> getUserAuthorityFileTypeList() {
        return userAuthorityFileTypeList;
    }

    /**
     * Sets the user authority file type list.
     *
     * @param userAuthorityFileTypeList the new user authority file type list
     */
    public void setUserAuthorityFileTypeList(final List<UserAuthorityFileType> userAuthorityFileTypeList) {
        this.userAuthorityFileTypeList = userAuthorityFileTypeList;
    }

    /**
     * Gets the role list.
     *
     * @return the role list
     */
    public List<Authority> getRoleList() {
        return roleList;
    }

    /**
     * Sets the role list.
     *
     * @param roleList the new role list
     */
    public void setRoleList(final List<Authority> roleList) {
        this.roleList = roleList;
    }

    /**
     * Gets the file field list.
     *
     * @return the file field list
     */
    public List<FileField> getFileFieldList() {
        return fileFieldList;
    }

    /**
     * Sets the file field list.
     *
     * @param fileFieldList the new file field list
     */
    public void setFileFieldList(final List<FileField> fileFieldList) {
        this.fileFieldList = fileFieldList;
    }

    /**
     * Gets the file item field list.
     *
     * @return the file item field list
     */
    public List<FileItemField> getFileItemFieldList() {
        return fileItemFieldList;
    }

    /**
     * Sets the file item field list.
     *
     * @param fileItemFieldList the new file item field list
     */
    public void setFileItemFieldList(final List<FileItemField> fileItemFieldList) {
        this.fileItemFieldList = fileItemFieldList;
    }

    /**
     * Gets the file type step list.
     *
     * @return the fileTypeStepList
     */
    public List<FileTypeStep> getFileTypeStepList() {
        return fileTypeStepList;
    }

    /**
     * Sets the file type step list.
     *
     * @param fileTypeStepList the fileTypeStepList to set
     */
    public void setFileTypeStepList(final List<FileTypeStep> fileTypeStepList) {
        this.fileTypeStepList = fileTypeStepList;
    }

    /**
     * @return the stepList
     */
    @SuppressWarnings("unchecked")
    public List<Step> getStepList() {
        if (stepList == null) {
            stepList = (List<Step>) CollectionUtils.collect(fileTypeStepList, new Transformer() {
                @Override
                public Object transform(final Object fileTypeStep) {
                    return ((FileTypeStep) fileTypeStep).getStep();
                }
            });
        }

        return stepList;
    }

    /**
     * @param stepList the stepList to set
     */
    public void setStepList(final List<Step> stepList) {
        this.stepList = stepList;
    }

    /**
     * @return the stepList
     */
    @SuppressWarnings("unchecked")
    public List<Service> getServiceList() {
        if (serviceList == null) {
            serviceList = (List<Service>) CollectionUtils.collect(fileTypeServiceList, new Transformer() {
                @Override
                public Object transform(final Object fileTypeService) {
                    return ((FileTypeService) fileTypeService).getService();
                }
            });
        }
        return serviceList;
    }

    /**
     * @param serviceList the serviceList to set
     */
    public void setServiceList(final List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    public List<FileTypeService> getFileTypeServiceList() {
        return fileTypeServiceList;
    }

    public void setFileTypeServiceList(List<FileTypeService> fileTypeServiceList) {
        this.fileTypeServiceList = fileTypeServiceList;
    }

    public List<FileTypeFlow> getFileTypeFlowList() {
        return fileTypeFlowList;
    }

    public void setFileTypeFlowList(List<FileTypeFlow> fileTypeFlowList) {
        this.fileTypeFlowList = fileTypeFlowList;
    }

    public List<Flow> getFlowList() {
        if (flowList == null) {
            flowList = (List<Flow>) CollectionUtils.collect(fileTypeFlowList, new Transformer() {
                @Override
                public Object transform(final Object fileTypeFlow) {
                    return ((FileTypeFlow) fileTypeFlow).getPk().getFlow();
                }
            });
        }
        return flowList;
    }

    public void setFlowList(List<Flow> flowList) {
        this.flowList = flowList;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object object) {
        if (!(object instanceof FileType)) {
            return false;
        }
        final FileType other = (FileType) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("FileType [id=");
        builder.append(id);
        builder.append(", code=");
        builder.append(code);
        builder.append(", labelFr=");
        builder.append(labelFr);
        builder.append(", labelEn=");
        builder.append(labelEn);
        builder.append("]");
        return builder.toString();
    }

}
