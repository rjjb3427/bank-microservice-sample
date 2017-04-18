package roman.torsten.sample.bankservice.api.service;

import roman.torsten.sample.bankservice.api.HistoryPaymentRecord;
import roman.torsten.sample.bankservice.api.PaymentRequest;
import roman.torsten.sample.bankservice.api.PaymentResult;

public interface PaymentService {
    PaymentResult perform(PaymentRequest request);
    HistoryPaymentRecord getPaymentDetails(String paymentId);
}
