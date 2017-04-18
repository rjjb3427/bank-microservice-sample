package roman.torsten.sample.bankservice.api.service;

import roman.torsten.sample.bankservice.api.Card;
import roman.torsten.sample.bankservice.api.CardValidationResult;

public interface CardValidationService {
    CardValidationResult validate(Card card);
}
