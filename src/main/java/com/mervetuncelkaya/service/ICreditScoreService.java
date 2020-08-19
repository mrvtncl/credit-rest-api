package com.mervetuncelkaya.service;

import com.mervetuncelkaya.exception.SystemException;

public interface ICreditScoreService {
    public int getCreditScore(String tckn) throws SystemException;
}
