package com.mervetuncelkaya.service;

import com.mervetuncelkaya.dto.ResponseDTO;
import com.mervetuncelkaya.entitiy.CreditApp;

public interface ICreditEvaluationService {
    public ResponseDTO getCreditEvaluationResult(CreditApp param);
}
