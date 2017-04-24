package roman.torsten.sample.bankservice.grpc;


import org.springframework.stereotype.Service;
import roman.torsten.sample.bankservice.api.Account;
import roman.torsten.sample.bankservice.api.Bank;
import roman.torsten.sample.bankservice.api.HistoryTransferRecord;
import roman.torsten.sample.bankservice.api.TransferRequest;
import roman.torsten.sample.bankservice.api.TransferResult;
import roman.torsten.sample.bankservice.api.TransferStatus;
import roman.torsten.sample.bankservice.api.service.BankService;

@Service
public class GrpcBankService implements BankService {

    @Override
    public Bank getBank() {
        return new Bank("GRPC banking", "1243234", "Mega Bank #5");
    }

    @Override
    public TransferResult transfer(TransferRequest request) {
        System.out.println("Process transfer: " + request);
        return new TransferResult(TransferStatus.Success, "no id");
    }

    @Override
    public TransferResult refund(String transferId) {
        return new TransferResult(TransferStatus.Fail, "no id haha");
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
