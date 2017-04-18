package roman.torsten.sample.bankservice.api;

import lombok.Data;

@Data
public class TransferResult {
    private final TransferStatus status;
    private final String id;

    public TransferResult(TransferStatus status, String id) {
        this.status = status;
        this.id = id;
    }
}
