package com.example.entity;

import jakarta.xml.bind.annotation.*;


@XmlRootElement(name = "company")
@XmlAccessorType(XmlAccessType.FIELD)
public class Company {

    @XmlTransient
    private Integer prodId;

    @XmlElement(name = "pname")
    private String prodName;

    @XmlElement(name = "pcost")
    private Double prodCost;

    @XmlElement(name = "pdisc")
    private Double prodDisc;

    @XmlElement(name = "pgst")
    private Double prodGst;


    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public Double getProdCost() {
        return prodCost;
    }

    public void setProdCost(Double prodCost) {
        this.prodCost = prodCost;
    }

    public Double getProdDisc() {
        return prodDisc;
    }

    public void setProdDisc(Double prodDisc) {
        this.prodDisc = prodDisc;
    }

    public Double getProdGst() {
        return prodGst;
    }

    public void setProdGst(Double prodGst) {
        this.prodGst = prodGst;
    }
}
