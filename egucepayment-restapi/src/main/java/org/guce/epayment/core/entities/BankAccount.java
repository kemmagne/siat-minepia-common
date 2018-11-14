package org.guce.epayment.core.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "BANK_ACCOUNT")
@Data
@EqualsAndHashCode(exclude = {"active", "balance", "owner", "oldId"})
public class BankAccount implements Serializable {

    private static final long serialVersionUID = -8982960497545938212L;

    @Id
    @SequenceGenerator(name = "BANK_ACCOUNT_SEQ", sequenceName = "BANK_ACCOUNT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BANK_ACCOUNT_SEQ")
    @Column(name = "ID", precision = 38)
    private BigDecimal id;

    @Column(name = "ACCOUNT_KEY", length = 2)
    private String key;
    @NotNull
    @JoinColumn(name = "AGENCY_ID")
    @ManyToOne
    private Partner agency;
    @NotNull
    @JoinColumn(name = "BANK_ID")
    @ManyToOne
    private Partner bank;
    @Column(name = "ACCOUNT_NUMBER", length = 11)
    private String number;
    @Column(name = "LABEL")
    private String label;

    @Column(name = "ACTIVE")
    private boolean active;
    @Column(name = "BALANCE", precision = 38, scale = 4)
    private BigDecimal balance;
    @JoinColumn(name = "OWNER_ID")
    @ManyToOne
    private Partner owner;

}

