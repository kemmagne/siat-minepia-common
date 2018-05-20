package org.guce.epayment.campost.documents;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

    /*  32 */ private static final QName _CARDNO_QNAME = new QName("", "CARD_NO");
    /*  33 */    private static final QName _RESPONSECODE_QNAME = new QName("", "RESPONSE_CODE");
    /*  34 */    private static final QName _CONVERRATE_QNAME = new QName("", "CONVER_RATE");
    /*  35 */    private static final QName _SUCCESS_QNAME = new QName("", "SUCCESS");
    /*  36 */    private static final QName _TRANSAMOUNT_QNAME = new QName("", "TRANS_AMOUNT");
    /*  37 */    private static final QName _PROCESSAMOUNT_QNAME = new QName("", "PROCESS_AMOUNT");
    /*  38 */    private static final QName _PPACCODETWO_QNAME = new QName("", "P_PAC_CODE_TWO");
    /*  39 */    private static final QName _PROCESSCURR_QNAME = new QName("", "PROCESS_CURR");
    /*  40 */    private static final QName _REQUESTID_QNAME = new QName("", "REQUEST_ID");
    /*  41 */    private static final QName _TRANSSESSIONID_QNAME = new QName("", "TRANS_SESSION_ID");
    /*  42 */    private static final QName _PPACCODEONE_QNAME = new QName("", "P_PAC_CODE_ONE");
    /*  43 */    private static final QName _ERRORCODE_QNAME = new QName("", "ERROR_CODE");
    /*  44 */    private static final QName _PMODE_QNAME = new QName("", "P_MODE");
    /*  45 */    private static final QName _REQUESTDATE_QNAME = new QName("", "REQUEST_DATE");
    /*  46 */    private static final QName _TRANSCURR_QNAME = new QName("", "TRANS_CURR");
    /*  47 */    private static final QName _SENDERSERNO_QNAME = new QName("", "SENDER_SERNO");

    public TRANSNET createTRANSNET() {
        /*  62 */ return new TRANSNET();
    }

    public RESPONSEREASON createRESPONSEREASON() {
        /*  70 */ return new RESPONSEREASON();
    }

    public ERROR createERROR() {
        /*  78 */ return new ERROR();
    }

    public CATALOG createCATALOG() {
        /*  86 */ return new CATALOG();
    }

    public CARDDATA createCARDDATA() {
        /*  94 */ return new CARDDATA();
    }

    @XmlElementDecl(namespace = "", name = "CARD_NO")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createCARDNO(String value) {
        /* 105 */ return new JAXBElement(_CARDNO_QNAME, String.class, null, value);
    }

    @XmlElementDecl(namespace = "", name = "RESPONSE_CODE")
    public JAXBElement<BigInteger> createRESPONSECODE(BigInteger value) {
        /* 115 */ return new JAXBElement(_RESPONSECODE_QNAME, BigInteger.class, null, value);
    }

    @XmlElementDecl(namespace = "", name = "CONVER_RATE")
    public JAXBElement<BigDecimal> createCONVERRATE(BigDecimal value) {
        /* 125 */ return new JAXBElement(_CONVERRATE_QNAME, BigDecimal.class, null, value);
    }

    @XmlElementDecl(namespace = "", name = "SUCCESS")
    public JAXBElement<BigInteger> createSUCCESS(BigInteger value) {
        /* 135 */ return new JAXBElement(_SUCCESS_QNAME, BigInteger.class, null, value);
    }

    @XmlElementDecl(namespace = "", name = "TRANS_AMOUNT")
    public JAXBElement<BigDecimal> createTRANSAMOUNT(BigDecimal value) {
        /* 145 */ return new JAXBElement(_TRANSAMOUNT_QNAME, BigDecimal.class, null, value);
    }

    @XmlElementDecl(namespace = "", name = "PROCESS_AMOUNT")
    public JAXBElement<BigDecimal> createPROCESSAMOUNT(BigDecimal value) {
        /* 155 */ return new JAXBElement(_PROCESSAMOUNT_QNAME, BigDecimal.class, null, value);
    }

    @XmlElementDecl(namespace = "", name = "P_PAC_CODE_TWO")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createPPACCODETWO(String value) {
        /* 166 */ return new JAXBElement(_PPACCODETWO_QNAME, String.class, null, value);
    }

    @XmlElementDecl(namespace = "", name = "PROCESS_CURR")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createPROCESSCURR(String value) {
        /* 177 */ return new JAXBElement(_PROCESSCURR_QNAME, String.class, null, value);
    }

    @XmlElementDecl(namespace = "", name = "REQUEST_ID")
    public JAXBElement<BigInteger> createREQUESTID(BigInteger value) {
        /* 187 */ return new JAXBElement(_REQUESTID_QNAME, BigInteger.class, null, value);
    }

    @XmlElementDecl(namespace = "", name = "TRANS_SESSION_ID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createTRANSSESSIONID(String value) {
        /* 198 */ return new JAXBElement(_TRANSSESSIONID_QNAME, String.class, null, value);
    }

    @XmlElementDecl(namespace = "", name = "P_PAC_CODE_ONE")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createPPACCODEONE(String value) {
        /* 209 */ return new JAXBElement(_PPACCODEONE_QNAME, String.class, null, value);
    }

    @XmlElementDecl(namespace = "", name = "ERROR_CODE")
    public JAXBElement<BigInteger> createERRORCODE(BigInteger value) {
        /* 219 */ return new JAXBElement(_ERRORCODE_QNAME, BigInteger.class, null, value);
    }

    @XmlElementDecl(namespace = "", name = "P_MODE")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createPMODE(String value) {
        /* 230 */ return new JAXBElement(_PMODE_QNAME, String.class, null, value);
    }

    @XmlElementDecl(namespace = "", name = "REQUEST_DATE")
    public JAXBElement<String> createREQUESTDATE(String value) {
        /* 240 */ return new JAXBElement(_REQUESTDATE_QNAME, String.class, null, value);
    }

    @XmlElementDecl(namespace = "", name = "TRANS_CURR")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createTRANSCURR(String value) {
        /* 251 */ return new JAXBElement(_TRANSCURR_QNAME, String.class, null, value);
    }

    @XmlElementDecl(namespace = "", name = "SENDER_SERNO")
    public JAXBElement<BigInteger> createSENDERSERNO(BigInteger value) {
        /* 261 */ return new JAXBElement(_SENDERSERNO_QNAME, BigInteger.class, null, value);
    }

}
