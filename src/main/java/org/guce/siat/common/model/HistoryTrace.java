/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.siat.common.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 *
 * @author mbezele
 */
@Entity
@Table(name = "HISTORY_TRACE")
public class HistoryTrace extends AbstractModel implements Serializable {

    private static final long serialVersionUID = 1L;
   
    @Id
    @Basic(optional = false)
    @Column(name = "HT_ID", length = 35)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HISTORY_TRACE_SEQ")
    @SequenceGenerator(name = "HISTORY_TRACE_SEQ", sequenceName = "HISTORY_TRACE_SEQ", allocationSize = 1)
    private Long htId;
 
    @Column(name = "HT_MODEL", length = 255)
    private String htModel;
 
    @Column(name = "HT_MODEL_ID", length = 25)
    private String htModelId;

    @Column(name = "HT_ACTION", length = 25)
    private String htAction;
   
    @Column(name = "HT_VALUE")
    private byte[] htValue;

    @Column(name = "HT_CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date htCreatedDate;

    @ManyToOne
    @JoinColumn(name = "ACTION_CREATED_BY", referencedColumnName = "LOGIN")
    private User userAction;

    public Long getHtId() {
        return htId;
    }

    public void setHtId(Long htId) {
        this.htId = htId;
    }

    public String getHtModel() {
        return htModel;
    }

    public void setHtModel(String htModel) {
        this.htModel = htModel;
    }

    public String getHtModelId() {
        return htModelId;
    }

    public void setHtModelId(String htModelId) {
        this.htModelId = htModelId;
    }

    public String getHtAction() {
        return htAction;
    }

    public void setHtAction(String htAction) {
        this.htAction = htAction;
    }

    public byte [] getHtValue() {
        return htValue;
    }

    public void setHtValue(byte [] htValue) {
        this.htValue = htValue;
    }

    public Date getHtCreatedDate() {
        return htCreatedDate;
    }

    public void setHtCreatedDate(Date htCreatedDate) {
        this.htCreatedDate = htCreatedDate;
    }

    public User getUserAction() {
        return userAction;
    }

    public void setUserAction(User userAction) {
        this.userAction = userAction;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.htId);
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
        final HistoryTrace other = (HistoryTrace) obj;
        if (!Objects.equals(this.htId, other.htId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HistoryTrace{" + "htId=" + htId + ", htModel=" + htModel + ", htModelId=" + htModelId + ", htAction=" + htAction + ", htValue=" + htValue + ", htCreatedDate=" + htCreatedDate + ", userAction=" + userAction + '}';
    }

}
