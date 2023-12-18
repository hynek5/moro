package cz.moro.websocket;

import cz.moro.api.dto.SystemResourceInfoDto;
import cz.moro.domain.SystemResourceInfo;
import cz.moro.service.SystemResourceInfoService;
import cz.moro.service.mapper.SystemResourceMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

import static org.springframework.web.reactive.socket.WebSocketMessage.Type.TEXT;

@Component
public class DefaultWebSocketHandler implements WebSocketHandler {

    private final SystemResourceMapper systemResourceMapper;
    private final SystemResourceInfoService systemResourceInfoService;

    private final Logger logger = LogManager.getLogger();
    @Autowired
    public DefaultWebSocketHandler(SystemResourceMapper systemResourceMapper, SystemResourceInfoService systemResourceInfoService) {
        this.systemResourceMapper = systemResourceMapper;
        this.systemResourceInfoService = systemResourceInfoService;
    }

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        return session.receive()
                .flatMap(webSocketMessage -> {
                    if (!webSocketMessage.getType().equals(TEXT)) {
                        return Mono.error(new RuntimeException("msg does not contain valid string as payload"));
                    }
                    return Mono.just(webSocketMessage.getPayloadAsText())
                            .flatMap(this::convertToSystemResourceInfo)
                            .doOnNext(this::logData)
                            .flatMap(systemResourceInfoService::save)
                            .flatMap(this::convertToString)
                            .flatMap(jsonString -> toDataBuffer(toResponse(jsonString)))
                            .flatMap(buffer -> session.send(Mono.just(new WebSocketMessage(TEXT, buffer))));
                })
                .onErrorResume(e -> {
                    logger.error("Session closed, error processing message", e);
                    return Mono.empty();
                })
                .then();
    }
    private Mono<SystemResourceInfo> convertToSystemResourceInfo(String input) {
        return convertToMono(() -> systemResourceMapper.fromJson(input));
    }

    private Mono<String> convertToString(SystemResourceInfoDto info) {
        return convertToMono(() -> systemResourceMapper.toJson(info));
    }
    private <R> Mono<R> convertToMono(ThrowingFunction<R> function) {
        try {
            return Mono.just(function.apply());
        } catch (Exception e) {
            return Mono.error(e);
        }
    }

    private void logData(SystemResourceInfo info) {
        logger.info(String.format("Received valid data:%s will be saved to db.", info.toString()));
    }

    private String toResponse(String string) {
        return "Received and processed data:" + string;
    }

    public Mono<DataBuffer> toDataBuffer(String json) {
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        DataBufferFactory factory = new DefaultDataBufferFactory();
        DataBuffer buffer = factory.allocateBuffer(bytes.length);
        buffer.write(bytes);
        return Mono.just(buffer);
    }


}
