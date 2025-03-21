package org.guce.siat.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author yenke
 */
@Entity
@Table(name = "CONTAINER"
//        , uniqueConstraints = @UniqueConstraint(columnNames = {"CONT_NUMBER", "FILE_ID"})
)
public class Container implements Serializable {

    private static final long serialVersionUID = -212745789515592123L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTAINER_SEQ")
    @SequenceGenerator(name = "CONTAINER_SEQ", sequenceName = "CONTAINER_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "CONT_NUMBER", length = 11)
    private String contNumber;
    @Column(name = "CONT_SEAL1", length = 20)
    private String contSeal1;
    @Column(name = "CONT_SEAL2", length = 20)
    private String contSeal2;
    @Column(name = "CONT_SEAL3", length = 20)
    private String contSeal3;
    @Column(name = "CONT_GROSS_MASS", precision = 24, scale = 4)
    private BigDecimal contGrossMass;
    @Column(name = "CONT_VOLUME", precision = 24, scale = 4)
    private BigDecimal contVolume;
    @Column(name = "CONT_PACKAGES_COUNT")
    private Integer contNumberOfPackages;
    @Column(name = "CONT_MARK", length = 35)
    private String contMark;
    @Column(name = "CONT_REFRIGERATED")
    private Boolean contRefrigerated;
    @Column(name = "CONT_DANGEROUS_INDICATOR")
    private Short contDangerousIndicator;
    @Column(name = "CONT_TYPE")
    private String contType;
    @Column(name = "CONT_FULL")
    private Boolean contFull;
    @Column(name = "CONT_VGM")
    private BigDecimal contVgm;
    @Column(name = "CONT_DENOMINATION")
    private String contDenomination;

    /**
     * The file
     */
    @JoinColumn(name = "FILE_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne
    private File file;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContNumber() {
        return contNumber;
    }

    public void setContNumber(String contNumber) {
        this.contNumber = contNumber;
    }

    public String getContSeal1() {
        return contSeal1;
    }

    public void setContSeal1(String contSeal1) {
        this.contSeal1 = contSeal1;
    }

    public String getContSeal2() {
        return contSeal2;
    }

    public void setContSeal2(String contSeal2) {
        this.contSeal2 = contSeal2;
    }

    public String getContSeal3() {
        return contSeal3;
    }

    public void setContSeal3(String contSeal3) {
        this.contSeal3 = contSeal3;
    }

    public BigDecimal getContGrossMass() {
        return contGrossMass;
    }

    public void setContGrossMass(BigDecimal contGrossMass) {
        this.contGrossMass = contGrossMass;
    }

    public BigDecimal getContVolume() {
        return contVolume;
    }

    public void setContVolume(BigDecimal contVolume) {
        this.contVolume = contVolume;
    }

    public Integer getContNumberOfPackages() {
        return contNumberOfPackages;
    }

    public void setContNumberOfPackages(Integer contNumberOfPackages) {
        this.contNumberOfPackages = contNumberOfPackages;
    }

    public String getContMark() {
        return contMark;
    }

    public void setContMark(String contMark) {
        this.contMark = contMark;
    }

    public Boolean getContRefrigerated() {
        return contRefrigerated;
    }

    public void setContRefrigerated(Boolean contRefrigerated) {
        this.contRefrigerated = contRefrigerated;
    }

    public Short getContDangerousIndicator() {
        return contDangerousIndicator;
    }

    public void setContDangerousIndicator(Short contDangerousIndicator) {
        this.contDangerousIndicator = contDangerousIndicator;
    }

    public String getContType() {
        return contType;
    }

    public void setContType(String contType) {
        this.contType = contType;
    }

    public Boolean getContFull() {
        return contFull;
    }

    public void setContFull(Boolean contFull) {
        this.contFull = contFull;
    }

    public BigDecimal getContVgm() {
        return contVgm;
    }

    public void setContVgm(BigDecimal contVgm) {
        this.contVgm = contVgm;
    }

    public String getContDenomination() {
        return contDenomination;
    }

    public void setContDenomination(String contDenomination) {
        this.contDenomination = contDenomination;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.contNumber);
        hash = 79 * hash + Objects.hashCode(this.contSeal1);
        hash = 79 * hash + Objects.hashCode(this.contSeal2);
        hash = 79 * hash + Objects.hashCode(this.contSeal3);
        hash = 79 * hash + Objects.hashCode(this.contGrossMass);
        hash = 79 * hash + Objects.hashCode(this.contVolume);
        hash = 79 * hash + Objects.hashCode(this.contNumberOfPackages);
        hash = 79 * hash + Objects.hashCode(this.contMark);
        hash = 79 * hash + Objects.hashCode(this.contRefrigerated);
        hash = 79 * hash + Objects.hashCode(this.contDangerousIndicator);
        hash = 79 * hash + Objects.hashCode(this.contType);
        hash = 79 * hash + Objects.hashCode(this.contFull);
        hash = 79 * hash + Objects.hashCode(this.contVgm);
        hash = 79 * hash + Objects.hashCode(this.contDenomination);
        hash = 79 * hash + Objects.hashCode(this.file);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Container other = (Container) obj;
        if (!Objects.equals(this.contNumber, other.contNumber)) {
            return false;
        }
        if (!Objects.equals(this.contSeal1, other.contSeal1)) {
            return false;
        }
        if (!Objects.equals(this.contSeal2, other.contSeal2)) {
            return false;
        }
        if (!Objects.equals(this.contSeal3, other.contSeal3)) {
            return false;
        }
        if (!Objects.equals(this.contMark, other.contMark)) {
            return false;
        }
        if (!Objects.equals(this.contType, other.contType)) {
            return false;
        }
        if (!Objects.equals(this.contDenomination, other.contDenomination)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.contGrossMass, other.contGrossMass)) {
            return false;
        }
        if (!Objects.equals(this.contVolume, other.contVolume)) {
            return false;
        }
        if (!Objects.equals(this.contNumberOfPackages, other.contNumberOfPackages)) {
            return false;
        }
        if (!Objects.equals(this.contRefrigerated, other.contRefrigerated)) {
            return false;
        }
        if (!Objects.equals(this.contDangerousIndicator, other.contDangerousIndicator)) {
            return false;
        }
        if (!Objects.equals(this.contFull, other.contFull)) {
            return false;
        }
        if (!Objects.equals(this.contVgm, other.contVgm)) {
            return false;
        }
        return Objects.equals(this.file, other.file);
    }

}
