package com.mervetuncelkaya.service;

import com.mervetuncelkaya.entitiy.CreditApp;
import com.mervetuncelkaya.entitiy.CreditNotif;
import com.mervetuncelkaya.exception.GeneralDatabaseException;
import com.mervetuncelkaya.exception.SystemException;
import com.mervetuncelkaya.repository.CreditNotifRepository;

public interface ICreditNotifService extends IBaseService<CreditNotif, CreditNotifRepository> {

    public CreditNotif buildCreditNotif(String creditId, String message) throws SystemException;

    public CreditNotif getCreditNotifByCreditId(String creditId) throws GeneralDatabaseException;

    public String createNotifMessage(CreditApp param) throws IllegalStateException;

}
