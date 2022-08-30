/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.siat.common.form.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.guce.siat.common.model.Country;
import org.guce.siat.common.model.Port;
import org.guce.siat.common.model.TransportMode;

/**
 *
 * @author boris.tomfeu
 */
public class FormData implements Serializable {

    private Country origin;
    private CoreStakeHolder consignee;
    private CoreStakeHolder exporter;
    private Country destCountry;
    private TransportMode transportMode;
    private Port clearingPlace;
    private Port loadingPlace;
    private List<CoreGood> goodList;
    private String conditionnement;
    private String typeProduit;

    public FormData() {
        this.origin = new Country();
        this.origin.setCountryIdAlpha2("CM");
        this.consignee = new CoreStakeHolder();
        this.exporter = new CoreStakeHolder();
        this.goodList = new ArrayList<>();
    }

    public Country getOrigin() {
        return origin;
    }

    public void setOrigin(Country origin) {
        this.origin = origin;
    }

    public CoreStakeHolder getConsignee() {
        return consignee;
    }

    public void setConsignee(CoreStakeHolder consignee) {
        this.consignee = consignee;
    }

    public CoreStakeHolder getExporter() {
        return exporter;
    }

    public void setExporter(CoreStakeHolder exporter) {
        this.exporter = exporter;
    }

    public Country getDestCountry() {
        return destCountry;
    }

    public void setDestCountry(Country destCountry) {
        this.destCountry = destCountry;
    }

    public TransportMode getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(TransportMode transportMode) {
        this.transportMode = transportMode;
    }

    public Port getClearingPlace() {
        return clearingPlace;
    }

    public void setClearingPlace(Port clearingPlace) {
        this.clearingPlace = clearingPlace;
    }

    public List<CoreGood> getGoodList() {
        return goodList;
    }

    public void setGoodList(List<CoreGood> goodList) {
        this.goodList = goodList;
    }

    public String getConditionnement() {
        return conditionnement;
    }

    public void setConditionnement(String conditionnement) {
        this.conditionnement = conditionnement;
    }

    public String getTypeProduit() {
        return typeProduit;
    }

    public void setTypeProduit(String typeProduit) {
        this.typeProduit = typeProduit;
    }

    @Override
    public String toString() {
        return "FormData{" + "origin=" + origin + ", consignee=" + consignee + ", exporter=" + exporter + ", destCountry=" + destCountry + ", transportMode=" + transportMode + ", clearingPlace=" + clearingPlace + ", goodList=" + goodList + ", conditionnement=" + conditionnement + '}';
    }

    public Port getLoadingPlace() {
        return loadingPlace;
    }

    public void setLoadingPlace(Port loadingPlace) {
        this.loadingPlace = loadingPlace;
    }
    
    
}
