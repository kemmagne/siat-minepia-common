package org.guce.siat.common.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class Item.
 */
@Entity
@Table(name = "REP_NSH")
@XmlRootElement
public class Item implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Nomenclature Douanière Marchandise (NSH)
     */
    @Id
    @Column(name = "GOODS_ITEM_CODE")
    private String goodsItemCode;

    /**
     * Code Chapitre
     */
    @Column(name = "GOODS_ITEM_CHAPTER_CODE")
    private String goodsItemChapterCode;

    /**
     * Description Marchandise (FR).
     */
    @Column(name = "GOODS_ITEM_DESC")
    private String goodsItemDesc;

    /**
     * Description Marchandise (EN)
     */
    @Column(name = "GOODS_ITEM_DESC_EN")
    private String goodsItemDescEn;

    /**
     * Législation spéciales: Certains produits sont frappés à l’entrée ou à la
     * sortie de prohibition absolue ou sont soumis à des restrictions, à des
     * mesures particulières de contrôle, à des formalités spéciales. Ces
     * mesures particulières sont applicables à la marchandise quelque soit le
     * régime douanier qui lui est assigné.
     */
    @Column(name = "CUSTOMS_STATUS_CODE")
    private String customsStatusCode;

    public Item() {
    }

    public Item(String goodsItemCode) {
        this.goodsItemCode = goodsItemCode;
    }

    /**
     * @return the goodsItemCode
     */
    public String getGoodsItemCode() {
        return goodsItemCode;
    }

    /**
     * @param goodsItemCode the goodsItemCode to set
     */
    public void setGoodsItemCode(final String goodsItemCode) {
        this.goodsItemCode = goodsItemCode;
    }

    /**
     * @return the goodsItemChapterCode
     */
    public String getGoodsItemChapterCode() {
        return goodsItemChapterCode;
    }

    /**
     * @param goodsItemChapterCode the goodsItemChapterCode to set
     */
    public void setGoodsItemChapterCode(final String goodsItemChapterCode) {
        this.goodsItemChapterCode = goodsItemChapterCode;
    }

    /**
     * @return the goodsItemDesc
     */
    public String getGoodsItemDesc() {
        return goodsItemDesc;
    }

    /**
     * @param goodsItemDesc the goodsItemDesc to set
     */
    public void setGoodsItemDesc(final String goodsItemDesc) {
        this.goodsItemDesc = goodsItemDesc;
    }

    /**
     * @return the goodsItemDescEn
     */
    public String getGoodsItemDescEn() {
        return goodsItemDescEn;
    }

    /**
     * @param goodsItemDescEn the goodsItemDescEn to set
     */
    public void setGoodsItemDescEn(final String goodsItemDescEn) {
        this.goodsItemDescEn = goodsItemDescEn;
    }

    /**
     * @return the customsStatusCode
     */
    public String getCustomsStatusCode() {
        return customsStatusCode;
    }

    /**
     * @param customsStatusCode the customsStatusCode to set
     */
    public void setCustomsStatusCode(final String customsStatusCode) {
        this.customsStatusCode = customsStatusCode;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (goodsItemCode != null ? goodsItemCode.hashCode() : 0);
        return hash;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object object) {
        if (!(object instanceof Item)) {
            return false;
        }
        final Item other = (Item) object;
        return !((this.getGoodsItemCode() == null && other.getGoodsItemCode() != null)
                || (this.getGoodsItemCode() != null && !this.getGoodsItemCode().equals(other.getGoodsItemCode())));
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Item [goodsItemCode=");
        builder.append(goodsItemCode);
        builder.append(", goodsItemChapterCode=");
        builder.append(goodsItemChapterCode);
        builder.append(", goodsItemDesc=");
        builder.append(goodsItemDesc);
        builder.append(", goodsItemDescEn=");
        builder.append(goodsItemDescEn);
        builder.append(", customsStatusCode=");
        builder.append(customsStatusCode);
        builder.append("]");
        return builder.toString();
    }

}
