package cz.moro.websocket;

@FunctionalInterface
public interface ThrowingFunction<R> {
    R apply() throws Exception;
}