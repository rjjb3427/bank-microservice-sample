package roman.torsten.sample.bankservice;

import org.springframework.stereotype.Service;
import roman.torsten.sample.bankservice.api.Account;
import roman.torsten.sample.bankservice.api.Bank;
import roman.torsten.sample.bankservice.api.HistoryTransferRecord;
import roman.torsten.sample.bankservice.api.Money;
import roman.torsten.sample.bankservice.api.TransferRequest;
import roman.torsten.sample.bankservice.api.TransferResult;
import roman.torsten.sample.bankservice.api.TransferStatus;
import roman.torsten.sample.bankservice.api.service.BankService;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Service
public class TorstenBankServiceProvider implements BankServiceProvider {

    private BankService bankServiceStub;

    @PostConstruct
    public void init() {
        bankServiceStub = new BankService() {
            @Override
            public Bank getBank() {
                return new Bank("db", "24345324523", "Deutsche Bank");
            }

            @Override
            public TransferResult transfer(TransferRequest request) {
                System.out.println("Processing transfer request: " + request);
                return new TransferResult(TransferStatus.Success, String.valueOf(LocalDateTime.now().getNano()));
            }

            @Override
            public TransferResult refund(String transferId) {
                return new TransferResult(TransferStatus.Fail, String.valueOf(LocalDateTime.now().getNano()));
            }

            @Override
            public HistoryTransferRecord getTransferDetails(String transferId) {
                throw new UnsupportedOperationException();
            }

            @Override
            public Account getAccount() {
                return new Account("1234", "db", new Money(100000, "usd"));
            }
        };
    }

    @Override
    public BankService get(String bankId) {
        return bankServiceStub;
    }
}
