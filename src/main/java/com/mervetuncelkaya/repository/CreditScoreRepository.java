package com.mervetuncelkaya.repository;

import com.mervetuncelkaya.entitiy.CreditScore;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditScoreRepository extends MongoRepository<CreditScore, String> {
    public CreditScore findByTckn(String tckn);
}
