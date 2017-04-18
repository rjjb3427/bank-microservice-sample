package roman.torsten.sample.bankservice.api.service;

/**
 * Author: Roman Torsten
 * Date: 14.04.2017
 * Time: 12:01
 */
public class BankServiceException extends RuntimeException {
    public BankServiceException() {
    }

    public BankServiceException(String message) {
        super(message);
    }

    public BankServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public BankServiceException(Throwable cause) {
        super(cause);
    }

    public BankServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
