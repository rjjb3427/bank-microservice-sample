package roman.torsten.sample.bankservice;

import org.springframework.stereotype.Service;
import roman.torsten.sample.bankservice.api.Account;
import roman.torsten.sample.bankservice.api.Card;
import roman.torsten.sample.bankservice.api.Money;

@Service
public class TorstenAccountService implements AccountService {
    @Override
    public Account get(Card card) {
//        return new Account(card.getId(), "2".equals(card.getId()) ? "grpcBankServiceClient" : "offlineBankService", new Money(100000, "rub"));
//        return new Account(card.getId(), "jsonBankServiceClient", new Money(100000, "rub"));
        return new Account(card.getId(), "offlineBankService", new Money(100000, "rub"));
    }
}
