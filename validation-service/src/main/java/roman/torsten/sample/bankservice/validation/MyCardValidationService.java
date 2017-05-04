package roman.torsten.sample.bankservice.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import roman.torsten.sample.bankservice.api.Card;
import roman.torsten.sample.bankservice.api.CardValidationResult;
import roman.torsten.sample.bankservice.api.service.CardValidationService;
import roman.torsten.sample.bankservice.validation.layers.data.CardEntity;
import roman.torsten.sample.bankservice.validation.layers.data.CardRepository;

@RestController
public class MyCardValidationService implements CardValidationService {

    @Autowired
    private CardRepository cardRepository;

    @RequestMapping("/ping")
    public ResponseEntity ping() {
        return new ResponseEntity<>("rest contoller is alive", HttpStatus.OK);
    }

    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public @ResponseBody CardValidationResult validate(@RequestBody Card card) {
        System.out.println("Card to validate: " + card);
        System.out.println("Registered cards: " + cardRepository.count());
        if (StringUtils.isEmpty(card.getId()) || StringUtils.isEmpty(card.getCardholderName())) {
            return new CardValidationResult(CardValidationResult.Status.InvalidNumber);
        }
        CardEntity cardEntity = cardRepository.findOne(card.getId());
        if (cardEntity != null) {
            if (card.getCardholderName().equals(cardEntity.getHolderName())) {
                return new CardValidationResult(CardValidationResult.Status.Ok);
            }
            return new CardValidationResult(CardValidationResult.Status.InvalidCardholderName);
        }
        return new CardValidationResult(CardValidationResult.Status.InvalidNumber);
    }
}
