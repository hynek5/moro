package cz.moro.config;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import reactor.netty.tcp.SslProvider;

import javax.net.ssl.TrustManagerFactory;
import java.net.URI;
import java.security.KeyStore;

@Configuration
public class WebSocketClientConfig {

    @Value("${websocket.server.url}")
    private URI serverWebSocketUrl;
    @Value("${websocket.client.trust-store}")
    private String trustStorePath;
    @Value("${websocket.client.trust-store-password}")
    private String trustStorePassword;


    @Bean
    ReactorNettyWebSocketClient webSocketClient(WebSocketHandler webSocketHandler) throws Exception {
        ReactorNettyWebSocketClient client = new ReactorNettyWebSocketClient();
        SslContext sslContext = createSslContext();
        SslProvider sslProvider = SslProvider.builder()
                .sslContext(sslContext)
                .build();
        return new ReactorNettyWebSocketClient(client.getHttpClient().secure(sslProvider));
    }

    public SslContext createSslContext() throws Exception {
        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());

        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource(trustStorePath);
        trustStore.load(resource.getInputStream(), trustStorePassword.toCharArray());

        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(trustStore);

        return SslContextBuilder
                .forClient()
                .trustManager(tmf)
                .build();
    }
}
