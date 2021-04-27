package org.guce.siat.common.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.guce.siat.common.utils.SecurityUtils;

/**
 * The Class ItemFlow.
 */
@Entity
@Table(name = "ITEM_FLOW")
@XmlRootElement
public class ItemFlow extends AbstractModel implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The id.
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEM_FLOW_SEQ")
    @SequenceGenerator(name = "ITEM_FLOW_SEQ", sequenceName = "ITEM_FLOW_SEQ", allocationSize = 1)
    private Long id;

    /**
     * The created.
     */
    @Column(name = "CREATED", nullable = false, columnDefinition = "TIMESTAMP DEFAULT SYSTIMESTAMP", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    /**
     * The unread.
     */
    @Column(name = "UNREAD")
    private Boolean unread;

    /**
     * The sent.
     */
    @Column(name = "SENT")
    private Boolean sent;

    /**
     * The received.
     */
    @Column(name = "RECEIVED", length = 1)
    private String received;

    /**
     * The sender.
     */
    @JoinColumn(name = "SENDER_ID", referencedColumnName = "ID")
    @ManyToOne
    private User sender;

    /**
     * The flow.
     */
    @JoinColumn(name = "FLOW_ID", referencedColumnName = "ID")
    @ManyToOne
    private Flow flow;

    /**
     * The file item.
     */
    @JoinColumn(name = "FILE_ITEM_ID", referencedColumnName = "ID")
    @ManyToOne
    private FileItem fileItem;

    /**
     * The item flows data list.
     */
    @OneToMany(mappedBy = "itemFlow")
    private List<ItemFlowData> itemFlowsDataList;

    /**
     * The received.
     */
    @Column(name = "MESSAGE_ID")
    private String messageId;

    @ManyToOne
    @JoinColumn(name = "ASSIGNED_USER_ID", referencedColumnName = "ID")
    private User assignedUser;

    @Column(name = "IP_ADDRESS", length = 20)
    private String ipAddress;

    /**
     * Instantiates a new item flow.
     */
    public ItemFlow() {
    }

    public ItemFlow(final Long id) {
        this.id = id;
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
     * Sets the id.
     *
     * @param id the id to set
     */
    @Override
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Gets the created.
     *
     * @return the created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * Sets the created.
     *
     * @param created the created to set
     */
    public void setCreated(final Date created) {
        this.created = created;
    }

    /**
     * Gets the unread.
     *
     * @return the unread
     */
    public Boolean getUnread() {
        return unread;
    }

    /**
     * Sets the unread.
     *
     * @param unread the unread to set
     */
    public void setUnread(final Boolean unread) {
        this.unread = unread;
    }

    /**
     * Gets the sent.
     *
     * @return the sent
     */
    public Boolean getSent() {
        return sent;
    }

    /**
     * Sets the sent.
     *
     * @param sent the sent to set
     */
    public void setSent(final Boolean sent) {
        this.sent = sent;
    }

    /**
     * Gets the sender.
     *
     * @return the sender
     */
    public User getSender() {
        return sender;
    }

    /**
     * Sets the sender.
     *
     * @param sender the sender to set
     */
    public void setSender(final User sender) {
        this.sender = sender;
    }

    /**
     * Gets the flow.
     *
     * @return the flow
     */
    public Flow getFlow() {
        return flow;
    }

    /**
     * Sets the flow.
     *
     * @param flow the flow to set
     */
    public void setFlow(final Flow flow) {
        this.flow = flow;
    }

    /**
     * Gets the file item.
     *
     * @return the fileItem
     */
    public FileItem getFileItem() {
        return fileItem;
    }

    /**
     * Sets the file item.
     *
     * @param fileItem the fileItem to set
     */
    public void setFileItem(final FileItem fileItem) {
        this.fileItem = fileItem;
    }

    /**
     * Gets the item flows data list.
     *
     * @return the itemFlowsDataList
     */
    public List<ItemFlowData> getItemFlowsDataList() {
        return itemFlowsDataList;
    }

    /**
     * Sets the item flows data list.
     *
     * @param itemFlowsDataList the itemFlowsDataList to set
     */
    public void setItemFlowsDataList(final List<ItemFlowData> itemFlowsDataList) {

        this.itemFlowsDataList = itemFlowsDataList;
    }

    /**
     * Gets the received.
     *
     * @return the received
     */
    public String getReceived() {
        return received;
    }

    /**
     * Sets the received.
     *
     * @param received the new received
     */
    public void setReceived(final String received) {
        this.received = received;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    /**
     * Gets the assigned user.
     *
     * @return the assignedUser
     */
    public User getAssignedUser() {
        return assignedUser;
    }

    /**
     * Sets the assigned user.
     *
     * @param assignedUser the assignedUser to set
     */
    public void setAssignedUser(final User assignedUser) {
        this.assignedUser = assignedUser;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object object) {
        if (!(object instanceof ItemFlow)) {
            return false;
        }
        final ItemFlow other = (ItemFlow) object;
        return !((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId())));
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ItemFlow [ id=");
        builder.append(id);
        builder.append(", created=");
        builder.append(created);
        builder.append(", unread=");
        builder.append(unread);
        builder.append(", handled=");
        builder.append(", sent=");
        builder.append(sent);
        builder.append(" ]");
        return builder.toString();
    }

    @PrePersist
    public void prePersist() {
        setIpAddress(SecurityUtils.getCurrentAddressIp());
    }

}
