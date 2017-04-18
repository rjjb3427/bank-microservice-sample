package roman.torsten.sample.bankservice.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentRequest {
    private final Card fromCard;
    private final Card toCard;
    private final Money balance;
}
