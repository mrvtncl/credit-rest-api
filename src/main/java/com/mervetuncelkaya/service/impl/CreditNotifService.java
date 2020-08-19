package com.mervetuncelkaya.service.impl;

import com.mervetuncelkaya.entitiy.CreditApp;
import com.mervetuncelkaya.entitiy.CreditNotif;
import com.mervetuncelkaya.exception.GeneralDatabaseException;
import com.mervetuncelkaya.exception.SystemException;
import com.mervetuncelkaya.repository.CreditNotifRepository;
import com.mervetuncelkaya.service.ICreditNotifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditNotifService extends BaseService<CreditNotif, CreditNotifRepository> implements ICreditNotifService {

    @Autowired
    public CreditNotifService(CreditNotifRepository repository) {
        super(repository);
    }

    public CreditNotif getCreditNotifByCreditId(String creditId) throws GeneralDatabaseException {
        try {
            return getRepository().getCreditNotifByCreditId(creditId);
        } catch (Exception ex) {
            throw new GeneralDatabaseException(ex);
        }
    }

    @Override
    public CreditNotif buildCreditNotif(String creditId, String message) throws SystemException {
        try {
            CreditNotif creditNotif = new CreditNotif();
            creditNotif.setCreditId(creditId);
            creditNotif.setMessage(message);
            return creditNotif;
        } catch (Exception ex) {
            throw new SystemException(ex);
        }
    }

    @Override
    public String createNotifMessage(CreditApp param) throws IllegalStateException {
        switch (param.getCreditStatus()) {
            case APPROVED:
                return "Sayın " + param.getName() + " " + param.getLastName() + ", kredi başvurunuz " + param.getCreditLimit() + "TL limit ile onaylanmıştır.";
            case REJECTED:
                return "Sayın " + param.getName() + " " + param.getLastName() + ", kredi başvurunuz uygun bulunmadığı için olumlu değerlendirilememiştir.";

            default:
                throw new IllegalStateException("Beklenmeyen hata: " + param.getCreditStatus());
        }
    }

}
