package org.guce.siat.common.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author tadzotsa
 */
@Entity
@Table(name = "QR_CODE_HISTORY")
public class QRCodeHistory implements Serializable {

    private static final long serialVersionUID = -1800546748161060707L;

    @Id
    @Column(name = "QR_CODE_ID")
    @SequenceGenerator(name = "QR_CODE_SEQ", sequenceName = "QR_CODE_SEQ", allocationSize = 1, initialValue = 500)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QR_CODE_SEQ")
    protected Long qrCodeId;
    @Lob
    @Column(name = "QR_CODE_CONTENT", nullable = false)
    private String qrCodeContent;
    @Column(name = "QR_CODE_GENERATION_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date qrCodeGenerationDate;
    @Column(name = "QR_CODE_MODIFICATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date qrCodeModificationDate;
    @JoinColumn(name = "QR_CODE_FILE", nullable = false, unique = true)
    @OneToOne
    private File qrCodeFile;

    public Long getQrCodeId() {
        return qrCodeId;
    }

    public void setQrCodeId(Long qrCodeId) {
        this.qrCodeId = qrCodeId;
    }

    public String getQrCodeContent() {
        return qrCodeContent;
    }

    public void setQrCodeContent(String qrCodeContent) {
        this.qrCodeContent = qrCodeContent;
    }

    public Date getQrCodeGenerationDate() {
        return qrCodeGenerationDate;
    }

    public void setQrCodeGenerationDate(Date qrCodeGenerationDate) {
        this.qrCodeGenerationDate = qrCodeGenerationDate;
    }

    public Date getQrCodeModificationDate() {
        return qrCodeModificationDate;
    }

    public void setQrCodeModificationDate(Date qrCodeModificationDate) {
        this.qrCodeModificationDate = qrCodeModificationDate;
    }

    public File getQrCodeFile() {
        return qrCodeFile;
    }

    public void setQrCodeFile(File qrCodeFile) {
        this.qrCodeFile = qrCodeFile;
    }

    @PrePersist
    public void prePersist() {
        qrCodeGenerationDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        qrCodeModificationDate = new Date();
    }

}
