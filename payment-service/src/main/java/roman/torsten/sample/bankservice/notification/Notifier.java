package roman.torsten.sample.bankservice.notification;

import roman.torsten.sample.bankservice.api.PaymentRequest;

public interface Notifier {
    void send(PaymentRequest request);
}
