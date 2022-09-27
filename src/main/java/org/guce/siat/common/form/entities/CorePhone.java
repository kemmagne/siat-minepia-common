/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.siat.common.form.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Koufana Crepin
 */
@Embeddable
public class CorePhone implements Serializable {

    @Column(name = "INDICATIF_PAYS", length = 5)
    private String indicatif;
    @Column(name = "FAXCODE", length = 15)
    private String numero;

    public CorePhone() {
    }

    public CorePhone(String indicatif, String numero) {
        this.indicatif = indicatif;
        this.numero = numero;
    }

    @XmlElement(name = "INDICATIF_PAYS")
    public String getIndicatif() {
        return indicatif;
    }

    public void setIndicatif(String indicatif) {
        this.indicatif = indicatif;
    }

    @XmlElement(name = "NUMERO")
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

}
