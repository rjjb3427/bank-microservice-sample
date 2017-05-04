package roman.torsten.sample.bankservice.validation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import roman.torsten.sample.bankservice.validation.layers.data.CardEntity;
import roman.torsten.sample.bankservice.validation.layers.data.CardRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Author: Roman Torsten
 * Date: 04.05.2017
 * Time: 14:44
 */
@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = ApplicationConfig.class)
public class CardRepositoryIntegrationTest {

    @Autowired
    CardRepository cardRepository;

    @Before
    public void before() {
//        cardRepository.deleteAll();
    }

    @Test
    public void sample() {
        assertEquals(0, cardRepository.count());
        CardEntity cardEntity = cardRepository.save(new CardEntity("213", "Romeo"));
        assertEquals("213", cardEntity.getId());
        assertEquals("Romeo", cardEntity.getHolderName());
        assertEquals(1, cardRepository.count());
        cardEntity = cardRepository.findOne("000");
        assertNull(cardEntity);
    }
}
