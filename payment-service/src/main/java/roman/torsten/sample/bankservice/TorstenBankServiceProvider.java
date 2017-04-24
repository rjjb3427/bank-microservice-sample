package roman.torsten.sample.bankservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import roman.torsten.sample.bankservice.api.service.BankService;

import javax.annotation.PostConstruct;
import java.util.Map;

@Service
public class TorstenBankServiceProvider implements BankServiceProvider {
    @Autowired
    Map<String, BankService> map;

    @PostConstruct
    public void init() {
        map.forEach((s, bankService) -> System.out.println("Registered service: " + s) );
    }

    @Override
    public BankService get(String bankId) {
        BankService bankService = map.get(bankId);
        if (bankService == null) {
            throw new RuntimeException("No bank service for bank id: " + bankId);
        }
        return bankService;
    }
}
