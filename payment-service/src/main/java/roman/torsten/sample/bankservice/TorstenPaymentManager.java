package roman.torsten.sample.bankservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import roman.torsten.sample.bankservice.api.Account;
import roman.torsten.sample.bankservice.api.CardValidationResult;
import roman.torsten.sample.bankservice.api.HistoryPaymentRecord;
import roman.torsten.sample.bankservice.api.TransferRequest;
import roman.torsten.sample.bankservice.api.PaymentRequest;
import roman.torsten.sample.bankservice.api.PaymentResult;
import roman.torsten.sample.bankservice.api.PaymentStatus;
import roman.torsten.sample.bankservice.api.TransferResult;
import roman.torsten.sample.bankservice.api.TransferStatus;
import roman.torsten.sample.bankservice.api.service.CardValidationService;
import roman.torsten.sample.bankservice.api.service.PaymentService;

@RestController
public class TorstenPaymentManager implements PaymentService {

    @Autowired
    private CardValidationService cardValidationService;

    @Autowired
    private TransactionIdGenerator transactionIdGenerator;

    @Autowired
    private AccountService accountService;

    @Autowired
    private BankServiceProvider bankServiceProvider;

    @RequestMapping("/ping")
    public String ping() {
        return "rest contoller is alive";
    }

    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    @Override
    public PaymentResult perform(@RequestBody PaymentRequest request) {
        String paymentId = transactionIdGenerator.generate();
        try {
            System.out.println("Starting transaction: " + paymentId);
            if (cardValidationService.validate(request.getFromCard()).getStatus() != CardValidationResult.Status.Ok) {
                return new PaymentResult(paymentId, PaymentStatus.Fail);
            }
            if (cardValidationService.validate(request.getToCard()).getStatus() != CardValidationResult.Status.Ok) {
                return new PaymentResult(paymentId, PaymentStatus.Fail);
            }
            System.out.println("From " + request.getFromCard() + ", to " + request.getToCard());
            Account fromAccount = accountService.get(request.getFromCard());
            Account toAccount = accountService.get(request.getToCard());
            System.out.println("Credit operation for " + fromAccount);
            TransferResult creditResult = bankServiceProvider.get(fromAccount.getBankId()).transfer(new TransferRequest(fromAccount.getId(), request.getBalance(), TransferRequest.Operation.Credit));
            if (creditResult.getStatus() == TransferStatus.Success) {
                System.out.println("Credit operation was successful, now debit operation for " + toAccount);
                TransferResult debitResult = bankServiceProvider.get(toAccount.getBankId()).transfer(new TransferRequest(toAccount.getId(), request.getBalance(), TransferRequest.Operation.Debit));
                return new PaymentResult(paymentId, debitResult.getStatus() == TransferStatus.Success ? PaymentStatus.Success : PaymentStatus.Submitted);
            }
            return new PaymentResult(paymentId, PaymentStatus.Fail);
        } catch (Exception e) {
            System.out.println("Exception catches: " + e.getMessage());
            return new PaymentResult(paymentId, PaymentStatus.Fail);
        }
    }

    @RequestMapping("/details/{id}")
    @Override
    public HistoryPaymentRecord getPaymentDetails(@PathVariable String id) {
        throw new UnsupportedOperationException();
    }
}
