package roman.torsten.sample.bankservice.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Client {
    private String id;
    private String bankId;
}
