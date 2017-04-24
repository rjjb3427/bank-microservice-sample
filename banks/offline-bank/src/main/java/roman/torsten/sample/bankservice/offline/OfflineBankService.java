package roman.torsten.sample.bankservice.offline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import roman.torsten.sample.bankservice.api.Account;
import roman.torsten.sample.bankservice.api.Bank;
import roman.torsten.sample.bankservice.api.HistoryTransferRecord;
import roman.torsten.sample.bankservice.api.TransferRequest;
import roman.torsten.sample.bankservice.api.TransferResult;
import roman.torsten.sample.bankservice.api.TransferStatus;
import roman.torsten.sample.bankservice.api.service.BankService;

@Service
public class OfflineBankService implements BankService {

    @Autowired
    private TransferIdGenerator idGenerator;

    @Override
    public Bank getBank() {
        return new Bank("Offline banking", "1243234", "Mega Bank #3");
    }

    @Override
    public TransferResult transfer(TransferRequest request) {
        System.out.println("Process transfer: " + request);
        return new TransferResult(TransferStatus.Success, idGenerator.generate());
    }

    @Override
    public TransferResult refund(String transferId) {
        return new TransferResult(TransferStatus.Fail, idGenerator.generate());
    }

    @Override
    public HistoryTransferRecord getTransferDetails(String transferId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Account getAccount() {
        throw new UnsupportedOperationException();
    }
}
