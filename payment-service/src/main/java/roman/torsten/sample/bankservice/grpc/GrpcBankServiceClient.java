package roman.torsten.sample.bankservice.grpc;

import org.springframework.stereotype.Service;
import roman.torsten.sample.bankservice.api.Account;
import roman.torsten.sample.bankservice.api.Bank;
import roman.torsten.sample.bankservice.api.HistoryTransferRecord;
import roman.torsten.sample.bankservice.api.TransferRequest;
import roman.torsten.sample.bankservice.api.TransferResult;
import roman.torsten.sample.bankservice.api.service.BankService;

import javax.annotation.PostConstruct;

@Service
public class GrpcBankServiceClient implements BankService {

    private GrpcBankServiceClientWrapper client;

    @PostConstruct
    public void init() {
        client = new GrpcBankServiceClientWrapper("localhost", 50051);
    }

    @Override
    public Bank getBank() {
        return null;
    }

    @Override
    public TransferResult transfer(TransferRequest request) {
        return client.transfer(request);
    }

    @Override
    public TransferResult refund(String transferId) {
        return null;
    }

    @Override
    public HistoryTransferRecord getTransferDetails(String transferId) {
        return null;
    }

    @Override
    public Account getAccount() {
        return null;
    }
}
