package roman.torsten.sample.bankservice;

import roman.torsten.sample.bankservice.api.Account;
import roman.torsten.sample.bankservice.api.Card;

public interface AccountService {
    Account get(Card card);
}
