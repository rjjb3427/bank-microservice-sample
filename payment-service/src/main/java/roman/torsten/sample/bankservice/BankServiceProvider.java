package roman.torsten.sample.bankservice;

import roman.torsten.sample.bankservice.api.service.BankService;

public interface BankServiceProvider {
    BankService get(String bankId);
}
