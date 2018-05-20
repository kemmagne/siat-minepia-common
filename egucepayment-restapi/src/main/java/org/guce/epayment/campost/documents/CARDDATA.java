package org.guce.epayment.campost.documents;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"pmode", "senderserno", "ppaccodeone", "ppaccodetwo", "transsessionid", "cardno", "transamount", "processamount", "transcurr", "processcurr", "converrate"})
@XmlRootElement(name = "CARDDATA")
public class CARDDATA {

    @XmlElement(name = "P_MODE", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String pmode;
    @XmlElement(name = "SENDER_SERNO", required = true)
    protected BigInteger senderserno;
    @XmlElement(name = "P_PAC_CODE_ONE", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String ppaccodeone;
    @XmlElement(name = "P_PAC_CODE_TWO", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String ppaccodetwo;
    @XmlElement(name = "TRANS_SESSION_ID", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String transsessionid;
    @XmlElement(name = "CARD_NO", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String cardno;
    @XmlElement(name = "TRANS_AMOUNT", required = true)
    protected BigDecimal transamount;
    @XmlElement(name = "PROCESS_AMOUNT", required = true)
    protected BigDecimal processamount;
    @XmlElement(name = "TRANS_CURR", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String transcurr;
    @XmlElement(name = "PROCESS_CURR", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String processcurr;
    @XmlElement(name = "CONVER_RATE", required = true)
    protected BigDecimal converrate;

    public String getPMODE() {
        return this.pmode;
    }

    public void setPMODE(String value) {
        this.pmode = value;
    }

    public BigInteger getSENDERSERNO() {
        return this.senderserno;
    }

    public void setSENDERSERNO(BigInteger value) {
        this.senderserno = value;
    }

    public String getPPACCODEONE() {
        return this.ppaccodeone;
    }

    public void setPPACCODEONE(String value) {
        this.ppaccodeone = value;
    }

    public String getPPACCODETWO() {
        return this.ppaccodetwo;
    }

    public void setPPACCODETWO(String value) {
        this.ppaccodetwo = value;
    }

    public String getTRANSSESSIONID() {
        return this.transsessionid;
    }

    public void setTRANSSESSIONID(String value) {
        this.transsessionid = value;
    }

    public String getCARDNO() {
        return this.cardno;
    }

    public void setCARDNO(String value) {
        this.cardno = value;
    }

    public BigDecimal getTRANSAMOUNT() {
        return this.transamount;
    }

    public void setTRANSAMOUNT(BigDecimal value) {
        this.transamount = value;
    }

    public BigDecimal getPROCESSAMOUNT() {
        return this.processamount;
    }

    public void setPROCESSAMOUNT(BigDecimal value) {
        this.processamount = value;
    }

    public String getTRANSCURR() {
        return this.transcurr;
    }

    public void setTRANSCURR(String value) {
        this.transcurr = value;
    }

    public String getPROCESSCURR() {
        return this.processcurr;
    }

    public void setPROCESSCURR(String value) {
        this.processcurr = value;
    }

    public BigDecimal getCONVERRATE() {
        return this.converrate;
    }

    public void setCONVERRATE(BigDecimal value) {
        this.converrate = value;
    }

}
