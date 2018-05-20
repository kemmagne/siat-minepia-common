package org.guce.epayment.campost.documents;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"success", "error"})
@XmlRootElement(name = "RESPONSE_REASON")
public class RESPONSEREASON {

    @XmlElement(name = "SUCCESS", required = true)
    protected BigInteger success;
    @XmlElement(name = "ERROR", required = true)
    protected ERROR error;

    public BigInteger getSUCCESS() {
        /* 59 */ return this.success;
    }

    public void setSUCCESS(BigInteger value) {
        /* 69 */ this.success = value;
    }

    public ERROR getERROR() {
        /* 79 */ return this.error;
    }

    public void setERROR(ERROR value) {
        /* 89 */ this.error = value;
    }

}
