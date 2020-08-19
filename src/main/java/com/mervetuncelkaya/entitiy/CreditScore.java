package com.mervetuncelkaya.entitiy;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CreditScore extends BaseEntity {
    private String tckn;
    private int creditScore;

    public CreditScore() {
        super();
    }

    public String getTckn() {
        return tckn;
    }

    public void setTckn(String tckn) {
        this.tckn = tckn;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }
}
