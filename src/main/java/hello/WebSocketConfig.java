
/**
 * Created by Shubham on 6/11/2016.
 */
    package hello;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.scheduling.annotation.EnableScheduling;
    import org.springframework.web.socket.config.annotation.EnableWebSocket;
    import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
    import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

    @Configuration
    @EnableWebSocket
    @EnableScheduling
    public class WebSocketConfig implements WebSocketConfigurer {

        @Autowired
        CounterHandler counterHandler;
        @Autowired
        OfferHandler offerHandler;

        @Override
        public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
            registry.addHandler(counterHandler, "/counter").setAllowedOrigins("*");
            registry.addHandler(offerHandler, "/notifications").setAllowedOrigins("*");
        }

    }
