package com.mervetuncelkaya.view;

import com.mervetuncelkaya.dto.ResponseDTO;
import com.mervetuncelkaya.entitiy.CreditApp;
import com.mervetuncelkaya.service.ICreditEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.Serializable;
import java.util.logging.Logger;

@Controller(value = "creditView")
public class CreditView implements Serializable {
    private Logger logger = Logger.getLogger(String.valueOf(CreditView.class));

    @Autowired
    private ICreditEvaluationService creditEvaluationService;

    private CreditApp creditApp = new CreditApp();
    private ResponseDTO response = new ResponseDTO();

    private boolean returnResponse;

    public void applyCredit() throws CloneNotSupportedException {
        CreditApp param = new CreditApp();
        param = (CreditApp) creditApp.clone();
        response = creditEvaluationService.getCreditEvaluationResult(param);
        returnResponse = true;
    }

    public CreditApp getCreditApp() {
        return creditApp;
    }

    public void setCreditApp(CreditApp creditApp) {
        this.creditApp = creditApp;
    }

    public ResponseDTO getResponse() {
        return response;
    }

    public void setResponse(ResponseDTO response) {
        this.response = response;
    }

    public boolean isReturnResponse() {
        return returnResponse;
    }

    public void setReturnResponse(boolean returnResponse) {
        this.returnResponse = returnResponse;
    }
}
