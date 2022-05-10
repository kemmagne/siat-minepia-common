package org.guce.siat.common.model.dto;

import java.io.Serializable;
import org.guce.siat.common.utils.Constants;

/**
 * The Class FileItemDTO.
 */
/**
 *
 */
public class FileItemDTO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The id.
     */
    private Long id;

    /**
     * The line number.
     */
    private Integer lineNumber;

    /**
     * The num ebms message.
     */
    private String numEbmsMessage;

    /**
     * The num ebms message annulation.
     */
    private String numEbmsMessageAnnulation;

    /**
     * The num ebms message paiement.
     */
    private String numEbmsMessagePaiement;

    /**
     * The quantity.
     */
    private String quantity;

    /**
     * The valeur fob.
     */
    private String fobValue;

    /**
     * The file.
     */
    private FileDTO file;

    /**
     * The nsh.
     */
    private ItemDTO nsh;

    /**
     * The subfamily.
     */
    private ServicesItemDTO subfamily;

    /**
     * The step.
     */
    private StepDTO step;

 
    /**
     * The draft.
     */
    private Boolean draft;

    /**
     * The is fictive.
     */
    private Boolean isFictive = false;

    /**
     * Instantiates a new file item.
     */
    public FileItemDTO() {
    }


    /**
     * Instantiates a new file item.
     *
     * @param id the id
     */
    public FileItemDTO(final Long id) {
        this.id = id;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the id to set
     */
    
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
    public FileDTO getFile() {
        return file;
    }

    /**
     * Sets the file.
     *
     * @param file the file to set
     */
    public void setFile(final FileDTO file) {
        this.file = file;
    }

    /**
     * Gets the nsh.
     *
     * @return the nsh
     */
    public ItemDTO getNsh() {
        return nsh;
    }

    /**
     * Sets the nsh.
     *
     * @param nsh the nsh to set
     */
    public void setNsh(final ItemDTO nsh) {
        this.nsh = nsh;
    }

    /**
     * Gets the subfamily.
     *
     * @return the subfamily
     */
    public ServicesItemDTO getSubfamily() {
        return subfamily;
    }

    /**
     * Sets the subfamily.
     *
     * @param subfamily the new subfamily
     */
    public void setSubfamily(final ServicesItemDTO subfamily) {
        this.subfamily = subfamily;
    }

    /**
     * Gets the step.
     *
     * @return the step
     */
    public StepDTO getStep() {
        return step;
    }

    /**
     * Sets the step.
     *
     * @param step the step to set
     */
    public void setStep(final StepDTO step) {
        this.step = step;
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
        if (!(object instanceof FileItemDTO)) {
            return false;
        }
        final FileItemDTO other = (FileItemDTO) object;
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
     * Instantiates a new file item.
     *
     * @param quantity the quantity
     * @param fobValue the fob value
     */
    public FileItemDTO(final String quantity, final String fobValue) {
        super();
        this.quantity = quantity;
        this.fobValue = fobValue;
    }

    /**
     * Instantiates a new file item.
     *
     * @param fobValue the fob value
     */
    public FileItemDTO(final String fobValue) {
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
     * Gets the num ebms message paiement.
     *
     * @return the num ebms message paiement
     */
    public String getNumEbmsMessagePaiement() {
        return numEbmsMessagePaiement;
    }

    /**
     * Sets the num ebms message paiement.
     *
     * @param numEbmsMessagePaiement the new num ebms message paiement
     */
    public void setNumEbmsMessagePaiement(final String numEbmsMessagePaiement) {
        this.numEbmsMessagePaiement = numEbmsMessagePaiement;
    }

    public static class StepDTO{
           private Long id;

    private String labelFr;

    private String labelEn;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getLabelFr() {
            return labelFr;
        }

        public void setLabelFr(String labelFr) {
            this.labelFr = labelFr;
        }

        public String getLabelEn() {
            return labelEn;
        }

        public void setLabelEn(String labelEn) {
            this.labelEn = labelEn;
        }
    
    }
    
    public static class ItemDTO{
    
    private String goodsItemCode;

    private String goodsItemChapterCode;

    private String goodsItemDesc;

    private String goodsItemDescEn;

        public String getGoodsItemCode() {
            return goodsItemCode;
        }

        public void setGoodsItemCode(String goodsItemCode) {
            this.goodsItemCode = goodsItemCode;
        }

        public String getGoodsItemChapterCode() {
            return goodsItemChapterCode;
        }

        public void setGoodsItemChapterCode(String goodsItemChapterCode) {
            this.goodsItemChapterCode = goodsItemChapterCode;
        }

        public String getGoodsItemDesc() {
            return goodsItemDesc;
        }

        public void setGoodsItemDesc(String goodsItemDesc) {
            this.goodsItemDesc = goodsItemDesc;
        }

        public String getGoodsItemDescEn() {
            return goodsItemDescEn;
        }

        public void setGoodsItemDescEn(String goodsItemDescEn) {
            this.goodsItemDescEn = goodsItemDescEn;
        }
    
}
    public static class ServicesItemDTO{
        private Long id;

	private String label;

	private Character type;

	private String code;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public Character getType() {
            return type;
        }

        public void setType(Character type) {
            this.type = type;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
        
        
    }
}
