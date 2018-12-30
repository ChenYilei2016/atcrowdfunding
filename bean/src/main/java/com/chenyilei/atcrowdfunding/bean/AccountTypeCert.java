package com.chenyilei.atcrowdfunding.bean;

import javax.persistence.Id;

public class AccountTypeCert {
    @Id
    private Integer id;

    private String accttype;

    private Integer certid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccttype() {
        return accttype;
    }

    public void setAccttype(String accttype) {
        this.accttype = accttype == null ? null : accttype.trim();
    }

    public Integer getCertid() {
        return certid;
    }

    public void setCertid(Integer certid) {
        this.certid = certid;
    }
}