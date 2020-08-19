package com.mervetuncelkaya.service.impl;

import com.mervetuncelkaya.entitiy.CreditApp;
import com.mervetuncelkaya.repository.CreditAppRepository;
import com.mervetuncelkaya.service.ICreditAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditAppService extends BaseService<CreditApp, CreditAppRepository> implements ICreditAppService {

    @Autowired
    public CreditAppService(CreditAppRepository repository) {
        super(repository);
    }

    @Override
    public CreditApp buildCreditApp(String tckn, String name, String lastName, String phone, double income) {
        CreditApp creditApp = new CreditApp();
        creditApp.setTckn(tckn);
        creditApp.setName(name);
        creditApp.setLastName(lastName);
        creditApp.setPhone(phone);
        creditApp.setIncome(income);
        return creditApp;
    }


}
