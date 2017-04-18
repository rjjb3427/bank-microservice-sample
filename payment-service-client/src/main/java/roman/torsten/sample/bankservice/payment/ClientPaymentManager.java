package roman.torsten.sample.bankservice.payment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import roman.torsten.sample.bankservice.api.Card;
import roman.torsten.sample.bankservice.api.HistoryPaymentRecord;
import roman.torsten.sample.bankservice.api.Money;
import roman.torsten.sample.bankservice.api.PaymentRequest;
import roman.torsten.sample.bankservice.api.PaymentResult;
import roman.torsten.sample.bankservice.api.service.PaymentFailedException;
import roman.torsten.sample.bankservice.api.service.PaymentService;

import java.io.IOException;

public class ClientPaymentManager implements PaymentService {

    @Override
    public PaymentResult perform(PaymentRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                System.out.println("Got error from server: " + response.getRawStatusCode());
            }
        });
        ResponseEntity<PaymentResult> response = restTemplate.postForEntity("http://localhost:8083/payment", request, PaymentResult.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new PaymentFailedException(request);
        }
        return response.getBody();
    }

    @Override
    public HistoryPaymentRecord getPaymentDetails(@PathVariable String id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public static void main(String[] args) {
        try {
            ClientPaymentManager clientPaymentManager = new ClientPaymentManager();
            PaymentRequest request = new PaymentRequest(new Card("1", "2"), new Card("2", "1"), new Money(1300, "RUB"));
            PaymentResult payment = clientPaymentManager.perform(request);
            System.out.println("Payment result: " + payment);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
