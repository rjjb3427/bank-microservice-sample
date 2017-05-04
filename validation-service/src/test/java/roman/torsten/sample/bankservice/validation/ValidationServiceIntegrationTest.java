package roman.torsten.sample.bankservice.validation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import roman.torsten.sample.bankservice.api.Card;
import roman.torsten.sample.bankservice.api.CardValidationResult;
import roman.torsten.sample.bankservice.api.service.CardValidationService;

/**
 * Author: Roman Torsten
 * Date: 04.05.2017
 * Time: 18:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidationServiceIntegrationTest {

    @Autowired
    CardValidationService service;

    @Test
    public void testA() {
        CardValidationResult result = service.validate(new Card("2343214", "romeo"));
        System.out.println(result);
    }

}
