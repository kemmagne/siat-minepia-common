package org.guce.epayment.campost.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.guce.epayment.core.entities.Partner;

@Entity
@Table(name = "CAMPOST_ACCOUNT")
@Data
@EqualsAndHashCode(of = {"id"})
public class CampostAccount implements Serializable {

    private static final long serialVersionUID = 5742864034821557031L;

    @Id
    @SequenceGenerator(name = "CAMPOST_ACCOUNT_SEQ", sequenceName = "CAMPOST_ACCOUNT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAMPOST_ACCOUNT_SEQ")
    @Column(name = "ID")
    private Integer id;

    @JoinColumn(name = "BENEFICIARY", nullable = false, unique = true)
    @OneToOne
    private Partner beneficiary;
    @Column(name = "MERCHANT_ID", nullable = false, unique = true)
    private String merchantid;
    @Column(name = "IZEN_ID", nullable = false, unique = true)
    private String izenid;
    @Column(name = "SECRET_KEY", nullable = false)
    private String secretKey;
    @Column(name = "INIT_VECTOR", nullable = false)
    private String initVector;

}
