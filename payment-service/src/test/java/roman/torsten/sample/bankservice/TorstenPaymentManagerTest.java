package roman.torsten.sample.bankservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import roman.torsten.sample.bankservice.api.PaymentRequest;
import roman.torsten.sample.bankservice.api.PaymentResult;

@RunWith(SpringRunner.class)
public class TorstenPaymentManagerTest {

    private TorstenPaymentManager manager = new TorstenPaymentManager();

    @Test
    public void checkPing() throws Exception {
        PaymentRequest request = new PaymentRequest(null, null, null);
        PaymentResult result = manager.perform(request);

    }

}

