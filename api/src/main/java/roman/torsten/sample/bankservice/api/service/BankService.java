package roman.torsten.sample.bankservice.api.service;

import roman.torsten.sample.bankservice.api.Account;
import roman.torsten.sample.bankservice.api.Bank;
import roman.torsten.sample.bankservice.api.HistoryTransferRecord;
import roman.torsten.sample.bankservice.api.TransferRequest;
import roman.torsten.sample.bankservice.api.TransferResult;

public interface BankService {
    Bank getBank();
    TransferResult transfer(TransferRequest request);
    TransferResult refund(String transferId);
    HistoryTransferRecord getTransferDetails(String transferId);
    Account getAccount();
}
