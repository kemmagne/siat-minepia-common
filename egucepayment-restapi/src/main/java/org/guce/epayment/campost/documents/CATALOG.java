package org.guce.epayment.campost.documents;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"carddata"})
@XmlRootElement(name = "CATALOG")
public class CATALOG {

    @XmlElement(name = "CARDDATA", required = true)
    protected CARDDATA carddata;

    public CARDDATA getCARDDATA() {
        /* 54 */ return this.carddata;
    }

    public void setCARDDATA(CARDDATA value) {
        /* 64 */ this.carddata = value;
    }

}
