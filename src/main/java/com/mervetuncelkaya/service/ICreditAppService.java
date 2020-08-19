package com.mervetuncelkaya.service;

import com.mervetuncelkaya.entitiy.CreditApp;
import com.mervetuncelkaya.repository.CreditAppRepository;

public interface ICreditAppService extends IBaseService<CreditApp, CreditAppRepository> {
    public CreditApp buildCreditApp(String tckn, String name, String lastName, String phone, double income);
}
