package roman.torsten.sample.bankservice.api.service;

import roman.torsten.sample.bankservice.api.PaymentRequest;

/**
 * Author: Roman Torsten
 * Date: 17.04.2017
 * Time: 14:58
 */
public class PaymentFailedException extends BankServiceException {
    public PaymentFailedException(PaymentRequest request) {
        super("Payment request failed. Request: " + request);
    }
}
