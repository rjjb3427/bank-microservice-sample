package roman.torsten.sample.bankservice;

import lombok.SneakyThrows;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import roman.torsten.sample.bankservice.api.Card;
import roman.torsten.sample.bankservice.api.CardValidationResult;
import roman.torsten.sample.bankservice.api.service.CardValidationService;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

@Service
public class CardValidationServiceClient implements CardValidationService {

    private static final String keyStoreFile = "keystore.p12";
    private static final String keyStorePassword = "mypassword";

    private static final ClientHttpRequestFactory requestFactory;

    static {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(new FileInputStream(new File(keyStoreFile)), keyStorePassword.toCharArray());

            SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
                    new SSLContextBuilder()
                            .loadTrustMaterial(null, new TrustSelfSignedStrategy())
                            .loadKeyMaterial(keyStore, keyStorePassword.toCharArray())
                            .build(),
                    NoopHostnameVerifier.INSTANCE);

            HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).build();
            requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @SneakyThrows
    public CardValidationResult validate(Card card) {
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("roman", "torsten"));
        ResponseEntity<CardValidationResult> response = restTemplate.postForEntity("https://localhost:8082/validate", card, CardValidationResult.class);
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
            e.getMessage();
            e.printStackTrace();
        }
    }
}
