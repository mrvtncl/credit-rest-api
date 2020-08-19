package com.mervetuncelkaya.util;

import com.mervetuncelkaya.exception.GeneralDatabaseException;
import com.mervetuncelkaya.exception.SystemException;
import org.springframework.http.HttpStatus;

public class Enums {
    public enum CreditStatusType {
        APPROVED(1),
        REJECTED(0);

        private int value;

        private CreditStatusType(int value) {
            this.value = value;
        }

        public int getName() {
            return value;
        }

        public void setName(int value) {
            this.value = value;
        }
    }

    public enum ExeptionType {
        DATABASE_SERVICE_UNAVAILABLE(new GeneralDatabaseException(), HttpStatus.SERVICE_UNAVAILABLE, "Veritabanı yanıt vermiyor."),
        NO_RESULT_FOUND(new GeneralDatabaseException(), HttpStatus.NOT_FOUND, "Kayıt bulunamadı."),
        SYSTEM_EXCEPTION(new SystemException(), HttpStatus.INTERNAL_SERVER_ERROR, "Sistem hatası."),
        SERVICE_UNAVAILABLE(new SystemException(), HttpStatus.SERVICE_UNAVAILABLE, "Servis yanıt vermiyor.");

        private Exception exception;
        private HttpStatus httpStatus;
        private String reason;

        private ExeptionType(Exception exception, HttpStatus httpStatus, String reason) {
            this.exception = exception;
            this.httpStatus = httpStatus;
            this.reason = reason;
        }

        public static ExeptionType fromException(Exception ex) {
            for (ExeptionType exeptionType : ExeptionType.values()) {
                if (ex.getClass().isInstance(exeptionType.getException())) {
                    return exeptionType;
                }
            }
            return null;
        }

        public Exception getException() {
            return exception;
        }

        public void setException(Exception exception) {
            this.exception = exception;
        }

        public HttpStatus getHttpStatus() {
            return httpStatus;
        }

        public void setHttpStatus(HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
        }

        public int getHttpStatusCode(){
            return httpStatus.value();
        }

        public String getHttpStatusValue(){
            return httpStatus.getReasonPhrase();
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }

}
