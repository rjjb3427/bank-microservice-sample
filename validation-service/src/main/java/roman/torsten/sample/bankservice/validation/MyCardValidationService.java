package roman.torsten.sample.bankservice.validation;

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

@RestController
public class MyCardValidationService implements CardValidationService {
    @RequestMapping("/ping")
    public ResponseEntity ping() {
        return new ResponseEntity<>("rest contoller is alive", HttpStatus.OK);
    }

    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public @ResponseBody CardValidationResult validate(@RequestBody Card card) {
        System.out.println("Card to validate: " + card);
        if (StringUtils.isEmpty(card.getId()) || StringUtils.isEmpty(card.getCardholderName())) {
            return new CardValidationResult(CardValidationResult.Status.InvalidNumber);
        }
        return new CardValidationResult(CardValidationResult.Status.Ok);
    }
}
