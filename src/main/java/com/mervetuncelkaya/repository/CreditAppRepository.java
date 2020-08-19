package com.mervetuncelkaya.repository;

import com.mervetuncelkaya.entitiy.CreditApp;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditAppRepository extends MongoRepository<CreditApp, String> {
}
