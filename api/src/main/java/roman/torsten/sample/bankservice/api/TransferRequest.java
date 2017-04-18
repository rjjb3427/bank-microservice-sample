package roman.torsten.sample.bankservice.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransferRequest {
    public enum Operation {
        Credit,
        Debit
    }

    private final String accountId;
    private final Money money;
    private final Operation operation;
}
