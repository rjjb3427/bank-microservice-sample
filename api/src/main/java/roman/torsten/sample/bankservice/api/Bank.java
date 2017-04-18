package roman.torsten.sample.bankservice.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Bank {
    private final String id;
    private final String bic;
    private final String name;
}
