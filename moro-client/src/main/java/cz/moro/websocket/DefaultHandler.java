package cz.moro.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.management.OperatingSystemMXBean;
import cz.moro.domain.SystemResourceInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

import java.lang.management.ManagementFactory;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

@Component
public class DefaultHandler implements WebSocketHandler {

    @Value("${websocket.server.url}")
    private String serverWebSocketUrl;
    @Value("${websocket.client.name}")
    private String clientName;

    private WebSocketSession existingSession;

    private final Logger logger = LogManager.getLogger();

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        if (existingSession == null || !existingSession.isOpen()) {
            existingSession = session;
        }
        Mono<WebSocketMessage> message = createWebSocketMessage(getSystemInfo());
        Consumer<WebSocketMessage> messageConsumer = msg -> logger.info(
                String.format("RECEIVED MSG FROM SERVER:%s", msg.getPayloadAsText()));
        return existingSession.send(message)
                .and(session.receive().doOnNext(messageConsumer));
    }


    private Mono<String> getSystemInfo() {
        SystemResourceInfo systemInfo = new SystemResourceInfo();
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        systemInfo.setOperatingSystem(osBean.getName());
        systemInfo.setCpuUsage(toPercentage(osBean.getCpuLoad()));
        systemInfo.setTotalMemory(osBean.getTotalMemorySize());
        systemInfo.setFreeMemory(osBean.getFreeMemorySize());
        systemInfo.setMemoryUsage(osBean.getTotalMemorySize() - osBean.getFreeMemorySize());
        systemInfo.setClientName(clientName);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return Mono.just(objectMapper.writeValueAsString(systemInfo));
        } catch (JsonProcessingException e) {
            return Mono.error(e);
        }
    }

    private Mono<WebSocketMessage> createWebSocketMessage(Mono<String> payload) {
        return payload.flatMap(msg -> Mono.just(new WebSocketMessage(WebSocketMessage.Type.TEXT, toDataBuffer(msg))));
    }

    public DataBuffer toDataBuffer(String json) {
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        DataBufferFactory factory = new DefaultDataBufferFactory();
        DataBuffer buffer = factory.allocateBuffer(bytes.length);
        buffer.write(bytes);
        return buffer;
    }

    private double toPercentage(double input) {
        return input*100;
    }
}
