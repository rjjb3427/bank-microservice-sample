package roman.torsten.sample.bankservice.api;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class HistoryTransferRecord {
    private TransferRequest request;
    private TransferResult result;
    private Date transferDate;
}
