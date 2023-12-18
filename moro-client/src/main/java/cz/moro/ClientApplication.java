package cz.moro;

import cz.moro.websocket.DefaultHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.reactive.socket.WebSocketHandler;

import java.net.URISyntaxException;

@SpringBootApplication
public class ClientApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ClientApplication.class, args);
		WebSocketHandler handler = context.getBean(DefaultHandler.class);
		Client client = context.getBean(Client.class);
		client.run(handler);
	}

}
