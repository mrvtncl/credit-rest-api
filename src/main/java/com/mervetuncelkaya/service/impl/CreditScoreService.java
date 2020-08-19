package com.mervetuncelkaya.service.impl;

import com.mervetuncelkaya.exception.SystemException;
import com.mervetuncelkaya.service.ICreditScoreService;
import com.mervetuncelkaya.util.Constants;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CreditScoreService implements ICreditScoreService {

    @Override
    public int getCreditScore(String tckn) throws SystemException {
        try {
            Random random = new Random();
            return random.nextInt(Constants.MAX_CREDIT_SCORE);
        } catch (Exception ex) {
            throw new SystemException(ex);
        }
    }
}