package roman.torsten.sample.bankservice.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Money {
    private final long amount;
    private final String currency;
}
