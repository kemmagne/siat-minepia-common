package org.guce.siat.common.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.guce.siat.common.utils.Constants;
import org.guce.siat.common.utils.enums.FinalDecisionType;

/**
 * The Class FileItem.
 */
/**
 *
 */
@Entity
@Table(name = "FILE_ITEM")
@XmlRootElement
public class FileItem extends AbstractModel implements Serializable {

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FILE_ITEM_SEQ")
    @SequenceGenerator(name = "FILE_ITEM_SEQ", sequenceName = "FILE_ITEM_SEQ", allocationSize = 1)
    private Long id;

    /**
     * The line number.
     */
    @Column(name = "LINE_NUMBER")
    private Integer lineNumber;

    /**
     * The num ebms message.
     */
    @Column(name = "NUM_EBMS_MSG", length = 61)
    private String numEbmsMessage;

    /**
     * The num ebms message annulation.
     */
    @Column(name = "NUM_EBMS_MSG_ANNULATION", length = 61)
    private String numEbmsMessageAnnulation;

    /**
     * The num ebms message paiement.
     */
    @Column(name = "NUM_EBMS_MSG_PAIEMENT", length = 61)
    private String numEbmsMessagePaiement;

    /**
     * The quantity.
     */
    @Column(name = "QUANTITY", length = 10)
    private String quantity;

    /**
     * The valeur fob.
     */
    @Column(name = "VALEUR_FOB", length = 100)
    private String fobValue;

    /**
     * The file.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "FILE_ID", referencedColumnName = "ID")
    private File file;

    /**
     * The nsh.
     */
    @ManyToOne
    @JoinColumn(name = "NSH_ID", referencedColumnName = "GOODS_ITEM_CODE")
    private Item nsh;

    /**
     * The subfamily.
     */
    @ManyToOne
    @JoinColumn(name = "SUBFAMILY_ID", referencedColumnName = "ID")
    private ServicesItem subfamily;

    /**
     * The step.
     */
    @ManyToOne
    @JoinColumn(name = "STEP_ID", referencedColumnName = "ID", updatable = true)
    private Step step;

    /**
     * The item flows list.
     */
    @OneToMany(mappedBy = "fileItem")
    @OrderBy("id DESC")
    private List<ItemFlow> itemFlowsList;

    /**
     * The file item field value list.
     */
    @OneToMany(mappedBy = "primaryKey.fileItem", cascade = CascadeType.PERSIST)
    private List<FileItemFieldValue> fileItemFieldValueList;

    /**
     * The recommandations list.
     */
    @OneToMany(mappedBy = "fileItem")
    private List<Recommandation> recommandationsList;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "FILE_ITEM_DECLARATION",
            joinColumns = @JoinColumn(name = "FILEITEM_ID"),
            inverseJoinColumns = @JoinColumn(name = "DECLARATION_ID"))
    private List<Declaration> additionnalDeclarations;

    /**
     * The draft.
     */
    @Column(name = "DRAFT")
    private Boolean draft;

    /**
     * The is fictive.
     */
    @Column(name = "FICTIVE", nullable = false)
    private Boolean isFictive = false;

    /**
     * The repeatablefile item field value list.
     */
    @Transient
    private List<FileItemFieldValue> repeatablefileItemFieldValueList = new ArrayList<FileItemFieldValue>();

    /**
     * The non repeatablefile field value list.
     */
    @Transient
    private List<FileItemFieldValue> nonRepeatablefileFieldValueList = new ArrayList<FileItemFieldValue>();

    /**
     * The decision type.
     */
    @Transient
    private FinalDecisionType decisionType;

    /**
     * The redefined label fr.
     */
    @Transient
    private String redefinedLabelFr;

    /**
     * The redefined label en.
     */
    @Transient
    private String redefinedLabelEn;

    /**
     * The botanic Name.
     */
    @Transient
    private String botanicName;

    /**
     * The commercial Name.
     */
    @Transient
    private String commercialName;

    /**
     * The redefined label en.
     */
    @Transient
    private double weight;

    @Transient
    private String unit;

    @Transient
    private String typeProduct;

    @Transient
    private String codeBureau;

    /**
     * Instantiates a new file item.
     */
    public FileItem() {
    }

    /**
     * Gets the decision type.
     *
     * @return the decision type
     */
    public FinalDecisionType getDecisionType() {
        return decisionType;
    }

    /**
     * Sets the decision type.
     *
     * @param decisionType the new decision type
     */
    public void setDecisionType(final FinalDecisionType decisionType) {
        this.decisionType = decisionType;
    }

    /**
     * Instantiates a new file item.
     *
     * @param id the id
     */
    public FileItem(final Long id) {
        super();
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
     * Gets the line number.
     *
     * @return the lineNumber
     */
    public Integer getLineNumber() {
        return lineNumber;
    }

    /**
     * Sets the line number.
     *
     * @param lineNumber the lineNumber to set
     */
    public void setLineNumber(final Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    /**
     * Gets the file.
     *
     * @return the file
     */
    public File getFile() {
        return file;
    }

    /**
     * Sets the file.
     *
     * @param file the file to set
     */
    public void setFile(final File file) {
        this.file = file;
    }

    /**
     * Gets the nsh.
     *
     * @return the nsh
     */
    public Item getNsh() {
        return nsh;
    }

    /**
     * Sets the nsh.
     *
     * @param nsh the nsh to set
     */
    public void setNsh(final Item nsh) {
        this.nsh = nsh;
    }

    /**
     * Gets the subfamily.
     *
     * @return the subfamily
     */
    public ServicesItem getSubfamily() {
        return subfamily;
    }

    /**
     * Sets the subfamily.
     *
     * @param subfamily the new subfamily
     */
    public void setSubfamily(final ServicesItem subfamily) {
        this.subfamily = subfamily;
    }

    /**
     * Gets the step.
     *
     * @return the step
     */
    public Step getStep() {
        return step;
    }

    /**
     * Sets the step.
     *
     * @param step the step to set
     */
    public void setStep(final Step step) {
        this.step = step;
    }

    /**
     * Gets the item flows list.
     *
     * @return the itemFlowsList
     */
    public List<ItemFlow> getItemFlowsList() {
        return itemFlowsList;
    }

    /**
     * Sets the item flows list.
     *
     * @param itemFlowsList the itemFlowsList to set
     */
    public void setItemFlowsList(final List<ItemFlow> itemFlowsList) {
        this.itemFlowsList = itemFlowsList;
    }

    /**
     * Gets the file item field value list.
     *
     * @return the fileItemFieldValueList
     */
    public List<FileItemFieldValue> getFileItemFieldValueList() {
        return fileItemFieldValueList;
    }

    /**
     * Sets the file item field value list.
     *
     * @param fileItemFieldValueList the fileItemFieldValueList to set
     */
    public void setFileItemFieldValueList(final List<FileItemFieldValue> fileItemFieldValueList) {
        this.fileItemFieldValueList = fileItemFieldValueList;
    }

    /**
     * Gets the draft.
     *
     * @return the draft
     */
    public Boolean getDraft() {
        return draft;
    }

    /**
     * Sets the draft.
     *
     * @param draft the draft to set
     */
    public void setDraft(final Boolean draft) {
        this.draft = draft;
    }

    /**
     * Gets the quantity.
     *
     * @return the quantity
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity.
     *
     * @param quantity the quantity to set
     */
    public void setQuantity(final String quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the fob value.
     *
     * @return the fobValue
     */
    public String getFobValue() {
        return fobValue;
    }

    /**
     * Sets the fob value.
     *
     * @param fobValue the fobValue to set
     */
    public void setFobValue(final String fobValue) {
        this.fobValue = fobValue;
    }

    /**
     * Gets the num ebms message.
     *
     * @return the numEbmsMessage
     */
    public String getNumEbmsMessage() {
        return numEbmsMessage;
    }

    /**
     * Sets the num ebms message.
     *
     * @param numEbmsMessage the numEbmsMessage to set
     */
    public void setNumEbmsMessage(final String numEbmsMessage) {
        this.numEbmsMessage = numEbmsMessage;
    }

    /**
     * Gets the recommandations list.
     *
     * @return the recommandationsList
     */
    public List<Recommandation> getRecommandationsList() {
        return recommandationsList;
    }

    /**
     * Sets the recommandations list.
     *
     * @param recommandationsList the recommandationsList to set
     */
    public void setRecommandationsList(final List<Recommandation> recommandationsList) {
        this.recommandationsList = recommandationsList;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result = 1;
        result = Constants.THIRTYONE * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object object) {
        if (!(object instanceof FileItem)) {
            return false;
        }
        final FileItem other = (FileItem) object;
        return !((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId())));
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
     */
//    @Override
//    public String toString() {
//        final StringBuilder builder = new StringBuilder();
//        builder.append("FileItem [id=");
//        builder.append(id);
//        builder.append(", lineNumber=");
//        builder.append(lineNumber);
//        builder.append(", numEbmsMessage=");
//        builder.append(numEbmsMessage);
//        builder.append(", file=");
//        builder.append(file);
//        builder.append(", nsh=");
//        builder.append(nsh);
//        builder.append(", subfamily=");
//        builder.append(subfamily);
//        builder.append(", step=");
//        builder.append(step);
//        builder.append(", draft=");
//        builder.append(draft);
//        builder.append("]");
//        return builder.toString();
//    }
    /**
     * Gets the repeatablefile item field value list.
     *
     * @return the repeatablefile item field value list
     */
    public List<FileItemFieldValue> getRepeatablefileItemFieldValueList() {

        if (CollectionUtils.isEmpty(repeatablefileItemFieldValueList)) {
            repeatablefileItemFieldValueList = new ArrayList<FileItemFieldValue>();
            for (final FileItemFieldValue fileItemFieldValue : getFileItemFieldValueList()) {
                if (fileItemFieldValue.getFileItemField().getRepeatable() && fileItemFieldValue.getLevel() == 0) {
                    repeatablefileItemFieldValueList.add(fileItemFieldValue);
                }
            }
        }
        return repeatablefileItemFieldValueList;

    }

    /**
     * Gets the non repeatablefile item field value list.
     *
     * @return the non repeatablefile item field value list
     */
    public List<FileItemFieldValue> getNonRepeatablefileItemFieldValueList() {

        if (CollectionUtils.isEmpty(nonRepeatablefileFieldValueList)) {
            nonRepeatablefileFieldValueList = new ArrayList<FileItemFieldValue>();
            for (final FileItemFieldValue fileFieldValue : getFileItemFieldValueList()) {
                if (!fileFieldValue.getFileItemField().getRepeatable()) {
                    nonRepeatablefileFieldValueList.add(fileFieldValue);
                }
            }
        }
        return nonRepeatablefileFieldValueList;

    }

    /**
     * Instantiates a new file item.
     *
     * @param quantity the quantity
     * @param fobValue the fob value
     */
    public FileItem(final String quantity, final String fobValue) {
        super();
        this.quantity = quantity;
        this.fobValue = fobValue;
    }

    /**
     * Instantiates a new file item.
     *
     * @param fobValue the fob value
     */
    public FileItem(final String fobValue) {
        super();

        this.fobValue = fobValue;
    }

    /**
     * Gets the num ebms message annulation.
     *
     * @return the numEbmsMessageAnnulation
     */
    public String getNumEbmsMessageAnnulation() {
        return numEbmsMessageAnnulation;
    }

    /**
     * Sets the num ebms message annulation.
     *
     * @param numEbmsMessageAnnulation the numEbmsMessageAnnulation to set
     */
    public void setNumEbmsMessageAnnulation(final String numEbmsMessageAnnulation) {
        this.numEbmsMessageAnnulation = numEbmsMessageAnnulation;
    }

    /**
     * Gets the checks if is fictive.
     *
     * @return the isFictive
     */
    public Boolean getIsFictive() {
        return isFictive;
    }

    /**
     * Sets the checks if is fictive.
     *
     * @param isFictive the isFictive to set
     */
    public void setIsFictive(final Boolean isFictive) {
        this.isFictive = isFictive;
    }

    /**
     * Gets the redefined label fr.
     *
     * @return the redefined label fr
     */
    public String getRedefinedLabelFr() {
        if (StringUtils.isEmpty(redefinedLabelFr) && step != null) {
            redefinedLabelFr = step.getLabelFr();
        }
        return redefinedLabelFr;
    }

    /**
     * Sets the redefined label fr.
     *
     * @param redefinedLabelFr the new redefined label fr
     */
    public void setRedefinedLabelFr(final String redefinedLabelFr) {
        this.redefinedLabelFr = redefinedLabelFr;
    }

    /**
     * Gets the redefined label en.
     *
     * @return the redefined label en
     */
    public String getRedefinedLabelEn() {
        if (StringUtils.isEmpty(redefinedLabelEn) && step != null) {
            redefinedLabelEn = step.getLabelEn();
        }
        return redefinedLabelEn;
    }

    /**
     * Sets the redefined label en.
     *
     * @param redefinedLabelEn the new redefined label en
     */
    public void setRedefinedLabelEn(final String redefinedLabelEn) {
        this.redefinedLabelEn = redefinedLabelEn;
    }

    /**
     * Gets the num ebms message paiement.
     *
     * @return the num ebms message paiement
     */
    public String getNumEbmsMessagePaiement() {
        return numEbmsMessagePaiement;
    }

    public List<Declaration> getAdditionnalDeclarations() {
        return additionnalDeclarations;
    }

    public void setAdditionnalDeclarations(List<Declaration> additionnalDeclarations) {
        this.additionnalDeclarations = additionnalDeclarations;
    }

    /**
     * Sets the num ebms message paiement.
     *
     * @param numEbmsMessagePaiement the new num ebms message paiement
     */
    public void setNumEbmsMessagePaiement(final String numEbmsMessagePaiement) {
        this.numEbmsMessagePaiement = numEbmsMessagePaiement;
    }

    public String getBotanicName() {
        return botanicName;
    }

    public void setBotanicName(String botanicName) {
        this.botanicName = botanicName;
    }

    public String getCommercialName() {
        return commercialName;
    }

    public void setCommercialName(String commercialName) {
        this.commercialName = commercialName;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "FileItem{" + "id=" + id + ", lineNumber=" + lineNumber + ", numEbmsMessage=" + numEbmsMessage + ", numEbmsMessageAnnulation=" + numEbmsMessageAnnulation + ", numEbmsMessagePaiement=" + numEbmsMessagePaiement + ", quantity=" + quantity + ", fobValue=" + fobValue + ", file=" + file + ", nsh=" + nsh + ", subfamily=" + subfamily + ", step=" + step + ", itemFlowsList=" + itemFlowsList + ", fileItemFieldValueList=" + fileItemFieldValueList + ", recommandationsList=" + recommandationsList + ", draft=" + draft + ", isFictive=" + isFictive + ", repeatablefileItemFieldValueList=" + repeatablefileItemFieldValueList + ", nonRepeatablefileFieldValueList=" + nonRepeatablefileFieldValueList + ", decisionType=" + decisionType + ", redefinedLabelFr=" + redefinedLabelFr + ", redefinedLabelEn=" + redefinedLabelEn + '}';
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(String typeProduct) {
        this.typeProduct = typeProduct;
    }

    public String getCodeBureau() {
        return codeBureau;
    }

    public void setCodeBureau(String codeBureau) {
        this.codeBureau = codeBureau;
    }

}
