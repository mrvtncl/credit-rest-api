package com.mervetuncelkaya.repository;

import com.mervetuncelkaya.entitiy.CreditNotif;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditNotifRepository extends MongoRepository<CreditNotif, String> {
    public CreditNotif getCreditNotifByCreditId(String creditId);

}
