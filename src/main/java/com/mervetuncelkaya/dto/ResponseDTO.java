package com.mervetuncelkaya.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {
    private String creditId;
    private String creditStatus;
    private double creditLimit;
    private String creditMessage;
    private int creditScore;
    public ResponseDTO() {
    }

    public ResponseDTO(String creditId, String creditStatus, double creditLimit, String creditMessage,int creditScore) {
        this.creditId = creditId;
        this.creditStatus = creditStatus;
        this.creditLimit = creditLimit;
        this.creditMessage = creditMessage;
        this.creditScore = creditScore;
    }

    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    public String getCreditStatus() {
        return creditStatus;
    }

    public void setCreditStatus(String creditStatus) {
        this.creditStatus = creditStatus;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getCreditMessage() {
        return creditMessage;
    }

    public void setCreditMessage(String creditMessage) {
        this.creditMessage = creditMessage;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }
}
