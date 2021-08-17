package org.guce.siat.common.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * The Class MessageToSend.
 */
@Entity
@Table(name = "MESSAGE_TO_SEND")
public class MessageToSend extends AbstractModel implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * The id.
     */
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MESSAGE_TO_SEND_SEQ")
    @SequenceGenerator(name = "MESSAGE_TO_SEND_SEQ", sequenceName = "MESSAGE_TO_SEND_SEQ", allocationSize = 1)
    private Long id;

    /**
     * The messageId.
     */
    @Column(name="MESSAGE_ID")
    private String messageId;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "EBXML")
    private byte[] ebxml;
    
    @Column(name = "RESEND_RETRY_NUMBER")
    private int resendRetryNumber;
    
    @Column(name="LAST_RETRY_SEND_TIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date lastRetrySendTime;

    /**
     * Instantiates a new MessageToSend.
     */
    public MessageToSend() {
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * Sets the messageId.
     *
     * @param id the new messageId
     */
    @Override
    public void setId(final Long id) {
        this.id = id;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public byte[] getEbxml() {
        return ebxml;
    }

    public void setEbxml(byte[] ebxml) {
        this.ebxml = ebxml;
    }

    public int getResendRetryNumber() {
        return resendRetryNumber;
    }

    public void setResendRetryNumber(int resendRetryNumber) {
        this.resendRetryNumber = resendRetryNumber;
    }

    public Date getLastRetrySendTime() {
        return lastRetrySendTime;
    }

    public void setLastRetrySendTime(Date lastRetrySendTime) {
        this.lastRetrySendTime = lastRetrySendTime;
    }
    
    /*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (messageId != null ? messageId.hashCode() : 0);
        return hash;
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object object) {
        if (!(object instanceof MessageToSend)) {
            return false;
        }
        final MessageToSend other = (MessageToSend) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("MessageToSend [id=");
        builder.append(messageId);
        builder.append("]");
        return builder.toString();
    }

}
