package org.guce.epayment.campost.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.guce.epayment.core.entities.Partner;
import org.guce.epayment.core.entities.Payment;

@Entity
@Table(name = "CAMPOST_PAYMENT")
@DiscriminatorValue("CAMPOST_PAYMENT")
@Data
@EqualsAndHashCode(callSuper = true, of = {})
public class CampostPayment extends Payment {

    private static final long serialVersionUID = -3505189134968124021L;

    @JoinColumn(name = "BENEFICIARY", nullable = false)
    @ManyToOne
    private Partner beneficiary;
    @Column(name = "SESSION_ID", nullable = false, length = 50)
    private String sessionId;
    @Column(name = "LANG", nullable = false, length = 3)
    private String lang;
    @Column(name = "CURRENCY", nullable = false, length = 3)
    private String currency;

}
