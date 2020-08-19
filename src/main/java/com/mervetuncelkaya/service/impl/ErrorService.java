package com.mervetuncelkaya.service.impl;

import com.mervetuncelkaya.dto.ErrorDTO;
import com.mervetuncelkaya.util.Enums;
import org.springframework.stereotype.Service;

@Service
public class ErrorService {

    public ErrorDTO buildErrorDTO(Exception exception, String path) {
        Enums.ExeptionType exceptionType = Enums.ExeptionType.fromException(exception);

        if (exceptionType == null) {
            exceptionType = Enums.ExeptionType.SYSTEM_EXCEPTION;
        }

        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorCode(exceptionType.getHttpStatusCode());
        errorDTO.setErrorName(exceptionType.getHttpStatusValue());
        errorDTO.setErrorMessage(exceptionType.getReason());
        return errorDTO;
    }
}
