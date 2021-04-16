package org.guce.siat.common.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * The Class MessageToSend.
 */
@Entity
@Table(name = "MESSAGE_TO_SEND")
public class MessageToSend implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The messageId.
     */
    @Id
    @Column(name="MESSAGEID")
    private String messageId;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] ebxml;
    
    @Column(name = "RESEND_RETRY_NUMBER")
    private int resendRetryNumber;

    /**
     * Instantiates a new MessageToSend.
     */
    public MessageToSend() {
    }

    /**
     * Gets the messageId.
     *
     * @return the messageId
     */
    public String getId() {
        return messageId;
    }

    /**
     * Sets the messageId.
     *
     * @param id the new messageId
     */
    public void setId(final String id) {
        this.messageId = id;
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
