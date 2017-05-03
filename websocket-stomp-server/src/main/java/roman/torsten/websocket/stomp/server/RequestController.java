package roman.torsten.websocket.stomp.server;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import roman.torsten.sample.bankservice.api.PaymentRequest;
import roman.torsten.sample.bankservice.api.PaymentResult;

@Controller
public class RequestController {
    @MessageMapping("/request")
    public void processRequest(PaymentRequest request) {
        System.out.println("Got: " + request.toString());
    }

    @MessageMapping("/result")
    public void processResult(PaymentResult result) {
        System.out.println("Got: " + result);
    }
}
