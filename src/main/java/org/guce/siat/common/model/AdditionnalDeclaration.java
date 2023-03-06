/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.siat.common.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author boris.tomfeu
 */
@Entity
@Table(name = "DECLARATION_ADDITIONNEL")
public class AdditionnalDeclaration extends AbstractModel implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DECLARATION_ADD_SEQ")
    @SequenceGenerator(name = "DECLARATION_ADD_SEQ", sequenceName = "DECLARATION_ADD_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "REFERENCE", length = 255)
    private String reference;

    @Column(name = "DECLARATION", length = 1000)
    private String declaration;
    

    @ManyToMany
    @JoinTable(name = "DECLARATION_ITEM",
            joinColumns = @JoinColumn(name = "DECLARATION_ID"),
            inverseJoinColumns = @JoinColumn(name = "FILEITEM_ID"))
    private List<FileItem> fileItems;

    @Transient
    private Declaration selectedDeclaration;
    
    @Transient
    private List<String> fileItemIds;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getDeclaration() {
        return declaration;
    }

    public void setDeclaration(String declaration) {
        this.declaration = declaration;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public List<String> getFileItemIds() {
        return fileItemIds;
    }

    public void setFileItemIds(List<String> fileItemIds) {
        this.fileItemIds = fileItemIds;
    }

    public List<FileItem> getFileItems() {
        return fileItems;
    }

    public void setFileItems(List<FileItem> fileItems) {
        this.fileItems = fileItems;
    }

    public Declaration getSelectedDeclaration() {
        return selectedDeclaration;
    }

    public void setSelectedDeclaration(Declaration selectedDeclaration) {
        this.selectedDeclaration = selectedDeclaration;
    }

    public String getItem() {
        List<String> hsCode = new ArrayList<>();
        if (fileItemIds != null && !fileItemIds.isEmpty()) {
            for (String fileItemId : fileItemIds) {
                hsCode.add(fileItemId.split("-")[1]);
            }
        }
        return String.join(" ", hsCode);
    }

    @PrePersist
    public void beforePersist() {
        if (this.fileItems == null) {
            this.fileItems = new ArrayList<>();
        }
        if (fileItemIds != null) {
            for (String fileItemId : fileItemIds) {
                FileItem item = new FileItem();
                item.setId(Long.parseLong(fileItemId.split("-")[0]));
                this.fileItems.add(item);
            }
        }
    }
}
