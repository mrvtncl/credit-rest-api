package com.mervetuncelkaya.entitiy;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CreditNotif extends BaseEntity {

    private String creditId;
    private String message;

    public CreditNotif() {
        super();
    }

    public CreditNotif(String creditId, String message) {
        this.creditId = creditId;
        this.message = message;
    }

    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
