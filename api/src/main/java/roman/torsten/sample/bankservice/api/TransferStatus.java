package roman.torsten.sample.bankservice.api;

/**
 * Author: Roman Torsten
 * Date: 10.04.2017
 * Time: 16:24
 */
public enum TransferStatus {
    Success(0),
    Fail(1);

    private final int id;

    TransferStatus(int id) {
        this.id = id;
    }
}
