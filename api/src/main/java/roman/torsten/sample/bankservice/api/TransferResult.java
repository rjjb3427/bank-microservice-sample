package roman.torsten.sample.bankservice.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransferResult {
    private final TransferStatus status;
    private final String id;
}
