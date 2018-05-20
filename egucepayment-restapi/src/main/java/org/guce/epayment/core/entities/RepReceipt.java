package org.guce.epayment.core.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author tadzotsa
 */
@Entity
@Table(name = "REP_RECEIPT")
@Data
@EqualsAndHashCode(of = {"number"})
public class RepReceipt implements Serializable {

    private static final long serialVersionUID = -5911581576367380906L;

    @Id
    @Column(name = "RECEIPT_NUMBER", nullable = false)
    private String number;
    @Column(name = "RECEIPT_DATE", nullable = false)
    private LocalDateTime receiptDate;
    @JoinColumn(name = "BENEFICIARY", nullable = false)
    @ManyToOne
    private Partner beneficiary;
    @JoinColumn(name = "INVOICE_ID", nullable = false)
    @ManyToOne
    private Invoice invoice;
    @JoinColumn(name = "PAYMENT_ID", nullable = false)
    @OneToOne
    private Payment payment;
    @Lob
    @Column(name = "METADATA")
    private String metadata;

}
