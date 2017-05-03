package roman.torsten.sample.bankservice.notification;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import roman.torsten.sample.bankservice.api.PaymentRequest;
import roman.torsten.sample.bankservice.api.PaymentResult;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Collections;

@Service
public class WebsocketsNotifier implements Notifier {

    public static class Handler implements WebSocketHandler {
        WebSocketSession session;

        @Override
        public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
            session = webSocketSession;
        }

        @Override
        public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
            System.out.println("Receive message: " + webSocketMessage.getPayload().toString());
        }

        @Override
        public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        }

        @Override
        public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        }

        @Override
        public boolean supportsPartialMessages() {
            return false;
        }

        public void send(PaymentRequest request) {
            try {
                session.sendMessage(new TextMessage(request.toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    Handler handler;

    @PostConstruct
    public void init() throws IOException {
        SockJsClient sockJsClient = new SockJsClient(Collections.singletonList(new WebSocketTransport(new StandardWebSocketClient())));
        handler = new Handler();
        sockJsClient.doHandshake(handler, "ws://localhost:9090/request");
    }

    @Override
    public void send(PaymentRequest request) {
        handler.send(request);
    }

    @Override
    public void send(PaymentResult result) {

    }
}
