package org.guce.epayment.campost.documents;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"errorcode"})
@XmlRootElement(name = "ERROR")
public class ERROR {

    @XmlElement(name = "ERROR_CODE", required = true)
    protected List<Integer> errorcode;

    public List<Integer> getERRORCODE() {
        /* 73 */ if (this.errorcode == null) {
            /* 74 */ this.errorcode = new ArrayList();
        }
        /* 76 */ return this.errorcode;
    }

}
