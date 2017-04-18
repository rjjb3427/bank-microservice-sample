package roman.torsten.sample.bankservice.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CardValidationResult {
    private final Status status;

    public enum Status {
        Ok,
        InvalidNumber,
        InvalidCvcCode,
        InvalidCardholderName,
        InvalidExpireDate,
    }
}
