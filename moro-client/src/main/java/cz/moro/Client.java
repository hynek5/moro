package cz.moro;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;

import java.net.URI;

@Component
public class Client {

    @Value("${websocket.server.url}")
    private URI serverWebSocketUrl;
    private final ReactorNettyWebSocketClient webSocketClient;
    private final Logger logger = LogManager.getLogger();

    @Autowired
    public Client(ReactorNettyWebSocketClient client) {
        webSocketClient = client;
    }

    public void run(WebSocketHandler webSocketHandler) {
        while (true) {
            try {
                webSocketClient.execute(serverWebSocketUrl, webSocketHandler).subscribe();
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                logger.error(e);
            }
        }
    }


}
