package roman.torsten.sample.bankservice.notification;

import roman.torsten.sample.bankservice.api.PaymentRequest;
import roman.torsten.sample.bankservice.api.PaymentResult;

public interface Notifier {
    void send(PaymentRequest request);
    void send(PaymentResult result);
}
