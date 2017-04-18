package roman.torsten.sample.bankservice.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class HistoryPaymentRecord {
    private final PaymentRequest request;
    private final PaymentResult result;
    private final Date creationDate;
    private final Date processedDate;
}
