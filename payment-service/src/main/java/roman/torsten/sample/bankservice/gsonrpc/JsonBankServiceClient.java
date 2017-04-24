package roman.torsten.sample.bankservice.gsonrpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import roman.torsten.sample.bankservice.api.Account;
import roman.torsten.sample.bankservice.api.Bank;
import roman.torsten.sample.bankservice.api.HistoryTransferRecord;
import roman.torsten.sample.bankservice.api.TransferRequest;
import roman.torsten.sample.bankservice.api.TransferResult;
import roman.torsten.sample.bankservice.api.service.BankService;

@Service
public class JsonBankServiceClient implements BankService {

    @Autowired
    private JsonBankService jsonService;

    @Override
    public Bank getBank() {
        throw new UnsupportedOperationException();
    }

    @Override
    public TransferResult transfer(TransferRequest request) {
        return jsonService.transfer(request);
    }

    @Override
    public TransferResult refund(String transferId) {
        throw new UnsupportedOperationException();
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
