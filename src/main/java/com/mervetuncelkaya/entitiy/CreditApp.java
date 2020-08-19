package com.mervetuncelkaya.entitiy;

import com.mervetuncelkaya.util.Enums;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Locale;

@Document
public class CreditApp extends BaseEntity implements Cloneable {

    private String tckn;
    private String name;
    private String lastName;
    private String phone;
    private double income;
    private double creditLimit;
    private int creditScore;
    private Enums.CreditStatusType creditStatus;

    public CreditApp() {
        super();
    }

    public CreditApp clone() throws CloneNotSupportedException {
       return (CreditApp) super.clone();
    }

    public  CreditApp(String tckn){
        super();
        this.tckn=tckn;
    }

    public String getTckn() {
        return tckn;
    }

    public void setTckn(String tckn) {
        this.tckn = tckn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toUpperCase(new Locale("tr","TR"));
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.toUpperCase(new Locale("tr","TR"));;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    public Enums.CreditStatusType getCreditStatus() {
        return creditStatus;
    }

    public void setCreditStatus(Enums.CreditStatusType creditStatus) {
        this.creditStatus = creditStatus;
    }
}
