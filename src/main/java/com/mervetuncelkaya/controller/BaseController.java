package com.mervetuncelkaya.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public abstract class BaseController extends ResponseEntityExceptionHandler {
    public ResponseEntity<Object> responseEntity(Object object) {
        return new ResponseEntity<Object>(object, HttpStatus.OK);
    }
}
