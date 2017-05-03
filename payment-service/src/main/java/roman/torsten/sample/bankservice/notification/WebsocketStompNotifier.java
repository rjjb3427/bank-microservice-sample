package roman.torsten.sample.bankservice.notification;


import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import roman.torsten.sample.bankservice.api.PaymentRequest;
import roman.torsten.sample.bankservice.api.PaymentResult;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Collections;

/**
 * Author: Roman Torsten
 * Date: 28.04.2017
 * Time: 15:11
 */
@Service
public class WebsocketStompNotifier implements Notifier {

    private StompSession session;

    public static class Handler extends StompSessionHandlerAdapter {
    }

    @PostConstruct
    public void init() throws IOException {
        SockJsClient sockJsClient = new SockJsClient(Collections.singletonList(new WebSocketTransport(new StandardWebSocketClient())));
        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        try {
            session = stompClient.connect("ws://localhost:9091/notification", new Handler()).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void send(PaymentRequest request) {
        session.send("/app/request", request);
    }

    @Override
    public void send(PaymentResult result) {
        session.send("/app/result", result);
    }
}
