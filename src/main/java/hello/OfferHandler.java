package hello;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.models.Notification;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Created by Krishna on 6/11/2016.
 */
@Component
public class OfferHandler extends TextWebSocketHandler {

    WebSocketSession session;

    // This will send only to one client(most recently connected)
    public void pushOfferCallback(Notification notification) {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("Trying to send:" + notification.toString());
        if (session != null && session.isOpen()) {
            try {
                System.out.println("Now sending:" + objectMapper.writeValueAsString(notification));
                session.sendMessage(new TextMessage("{\"value\": " + objectMapper.writeValueAsString(notification) + "}"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                System.out.println("Don't have open session to send:" + objectMapper.writeValueAsString(notification));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        System.out.println("Connection established");
        this.session = session;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message)
            throws Exception {
        if ("CLOSE".equalsIgnoreCase(message.getPayload())) {
            session.close();
        } else {
            System.out.println("Received:" + message.getPayload());
        }
    }
}
