package roman.torsten.sample.bankservice.api;

/**
 * Author: Roman Torsten
 * Date: 10.04.2017
 * Time: 16:24
 */
public enum PaymentStatus {
    Success(0),
    Fail(1),
    Submitted(2),
    ;

    private final int id;

    PaymentStatus(int id) {
        this.id = id;
    }
}
