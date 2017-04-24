package roman.torsten.sample.bankservice.gsonrpc;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.ProxyUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URL;

@Configuration
public class JsonRpcBankServiceConfig {
    private static final String endpoint = "http://localhost:8099/jsonBankService";

    @Bean
    public JsonRpcHttpClient jsonRpcHttpClient() {
        try {
            return new JsonRpcHttpClient(new URL(endpoint));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public JsonBankService jsonBankServiceProxyClient(JsonRpcHttpClient jsonRpcHttpClient) {
        return ProxyUtil.createClientProxy(getClass().getClassLoader(), JsonBankService.class, jsonRpcHttpClient);
    }
}
