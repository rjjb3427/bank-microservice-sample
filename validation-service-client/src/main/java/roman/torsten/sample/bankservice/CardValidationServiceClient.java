package roman.torsten.sample.bankservice;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import roman.torsten.sample.bankservice.api.Card;
import roman.torsten.sample.bankservice.api.CardValidationResult;
import roman.torsten.sample.bankservice.api.service.CardValidationService;

@Service
public class CardValidationServiceClient implements CardValidationService {

    @Override
    public CardValidationResult validate(Card card) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        ResponseEntity<CardValidationResult> response = restTemplate.postForEntity("http://localhost:8082/validate", card, CardValidationResult.class);
        return response.getBody();
    }

    public static void main(String[] args) {
        try {
            CardValidationServiceClient client = new CardValidationServiceClient();
            CardValidationResult result = client.validate(new Card("4234", "romeo"));
            System.out.println(result);
            result = client.validate(new Card("4234", null));
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
