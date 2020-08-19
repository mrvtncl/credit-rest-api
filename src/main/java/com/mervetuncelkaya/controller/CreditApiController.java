package com.mervetuncelkaya.controller;

import com.mervetuncelkaya.dto.ErrorDTO;
import com.mervetuncelkaya.dto.ResponseDTO;
import com.mervetuncelkaya.entitiy.CreditApp;
import com.mervetuncelkaya.entitiy.CreditNotif;
import com.mervetuncelkaya.exception.GeneralDatabaseException;
import com.mervetuncelkaya.exception.SystemException;
import com.mervetuncelkaya.service.ICreditAppService;
import com.mervetuncelkaya.service.ICreditEvaluationService;
import com.mervetuncelkaya.service.ICreditNotifService;
import com.mervetuncelkaya.service.impl.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/creditapi")
public class CreditApiController extends BaseController {

    private Logger logger = Logger.getLogger(String.valueOf(CreditApiController.class));

    @Autowired
    private ICreditAppService creditAppService;

    @Autowired
    private ICreditNotifService creditNotifService;

    @Autowired
    private ICreditEvaluationService creditEvaluationService;

    @Autowired
    private ErrorService errorService;

    @ExceptionHandler(value = {GeneralDatabaseException.class, SystemException.class})
    public ResponseEntity<Object> handleException(Exception exception, HttpServletRequest request) {
        ErrorDTO errorDTO = errorService.buildErrorDTO(exception, request.getRequestURI());
        return responseEntity(errorDTO);
    }

    @PostMapping(value = "applyCredit", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> applyCredit(@RequestBody CreditApp param) {
        logger.info("starting applyCredit...");
        ResponseDTO responseDTO = creditEvaluationService.getCreditEvaluationResult(param);
        return responseEntity(responseDTO);
    }

    @GetMapping(value = "getCreditAppList", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCreditAppList() {
        logger.info("starting getCreditAppList...");
        return responseEntity(creditAppService.findAll());
    }

    @GetMapping(value = "getCreditNotifList", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCreditNotifList() {
        logger.info("starting getCreditNotifList...");
        return responseEntity(creditNotifService.findAll());
    }

    @GetMapping(value = "getCreditAppById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCreditAppById(@RequestParam(value = "id") String id) {
        logger.info("starting getCreditAppById...");
        return responseEntity(creditAppService.findById(id));
    }

    @GetMapping(value = "getCreditNotifByCreditId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreditNotif> getCreditNotifByCreditId(@RequestParam(value = "creditId") String creditId) {
        logger.info("starting getCreditNotifByCreditId...");
        return ResponseEntity.ok(creditNotifService.getCreditNotifByCreditId(creditId));
    }

    @DeleteMapping(value = "deleteCreditNotifs", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteCreditNotifs() {
        logger.info("starting deleteCreditNotifs...");
        creditNotifService.deleteAll();
    }

    @DeleteMapping(value = "deleteCreditApps", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteCreditApps() {
        logger.info("starting deleteCreditApps...");
        creditAppService.deleteAll();
    }
}
