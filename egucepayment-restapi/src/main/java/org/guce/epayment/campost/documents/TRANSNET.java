package org.guce.epayment.campost.documents;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"requestid", "requestdate", "responsecode", "responsereason", "catalog"})
@XmlRootElement(name = "TRANS_NET")
public class TRANSNET {

    @XmlElement(name = "REQUEST_ID", required = true)
    protected BigInteger requestid;
    @XmlElement(name = "REQUEST_DATE", required = true)
    protected String requestdate;
    @XmlElement(name = "RESPONSE_CODE", required = true)
    protected BigInteger responsecode;
    @XmlElement(name = "RESPONSE_REASON", required = true)
    protected RESPONSEREASON responsereason;
    @XmlElement(name = "CATALOG", required = true)
    protected CATALOG catalog;

    public BigInteger getREQUESTID() {
        /*  71 */ return this.requestid;
    }

    public void setREQUESTID(BigInteger value) {
        /*  81 */ this.requestid = value;
    }

    public String getREQUESTDATE() {
        /*  91 */ return this.requestdate;
    }

    public void setREQUESTDATE(String value) {
        /* 101 */ this.requestdate = value;
    }

    public BigInteger getRESPONSECODE() {
        /* 111 */ return this.responsecode;
    }

    public void setRESPONSECODE(BigInteger value) {
        /* 121 */ this.responsecode = value;
    }

    public RESPONSEREASON getRESPONSEREASON() {
        /* 131 */ return this.responsereason;
    }

    public void setRESPONSEREASON(RESPONSEREASON value) {
        /* 141 */ this.responsereason = value;
    }

    public CATALOG getCATALOG() {
        /* 151 */ return this.catalog;
    }

    public void setCATALOG(CATALOG value) {
        /* 161 */ this.catalog = value;
    }

}
