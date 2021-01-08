/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.siat.common.model;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author mbezele
 */
/**
 * The Class StampSignature.
 */
@javax.persistence.Entity
@Table(name = "STAMP_SIGNATURE")
public class StampSignature extends AbstractModel implements Serializable{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id of image. */
	@Id
	@Column(name = "ID")
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STAMP_SIGNATURE_SEQ")
	@SequenceGenerator(name = "STAMP_SIGNATURE_SEQ", sequenceName = "STAMP_SIGNATURE_SEQ", allocationSize = 1)
	private Long id;

	/** The value of image. */	
	@Lob
	@Column(name = "IMAGE_VALUE")
	private byte[] image;
	
	/** The name of image. */	
	@Column(name = "IMAGE_NAME")
	private String name;
	
	 /**
     * The creation date.
     */
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public byte[]  getImage() {
		return image;
	}

	public void setImage(byte[]  image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	
}
