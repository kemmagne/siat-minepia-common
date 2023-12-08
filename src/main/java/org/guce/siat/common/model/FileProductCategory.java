/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.siat.common.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author lissouck
 */
/**
 * The Class File.
 */
@javax.persistence.Entity
@Table(name = "PRODUCT_CATEGORY_FILE")
public class FileProductCategory implements Serializable{
    /**
     * The id.
     */
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FILE_PRODUCT_CATEGORY_SEQ")
    @SequenceGenerator(name = "FILE_PRODUCT_CATEGORY_SEQ", sequenceName = "FILE_PRODUCT_CATEGORY_SEQ", allocationSize = 1)
    private Long id;
    
    /**
     * The file
     */
    @JoinColumn(name = "FILE_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne
    private File file;
    
    /**
     * The product category
     */
    @JoinColumn(name = "PRODUCT_CATEGORY", referencedColumnName = "CODE", nullable = false)
    @ManyToOne
    private ProductCategory productCategory;

    public FileProductCategory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }
    
    
}
