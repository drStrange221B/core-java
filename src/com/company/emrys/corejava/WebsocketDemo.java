package com.company.emrys.corejava;

import javax.sound.midi.Soundbank;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.CountDownLatch;

public class WebsocketDemo {

    public static void main(String[] args) throws Exception
    {
        int msgCount = 5;
        CountDownLatch receiveLatch = new CountDownLatch(msgCount);

        CompletableFuture<WebSocket> webSocketCompletableFuture = HttpClient.newHttpClient()
                .newWebSocketBuilder()
                .connectTimeout(Duration.ofSeconds(3))
                .buildAsync(
                        URI.create("ws://echo.websocket.org"), new EchoListner(receiveLatch)
                );
        webSocketCompletableFuture.thenAccept(
                webSocket -> {
                    webSocket.request(msgCount);

                    for(int i=0; i<msgCount; i++){
                        webSocket.sendText("Message " + i, true);
                    }
                }
        );

        receiveLatch.await();


    }

    static class EchoListner implements WebSocket.Listener{
       private CountDownLatch countDownLatch;
        public EchoListner(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void onOpen(WebSocket webSocket) {
            System.out.println("WebSocket opened !");
        }

        @Override
        public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
            System.out.println("onText " + data);
            countDownLatch.countDown();
            return null;
        }

        @Override
        public CompletionStage<?> onBinary(WebSocket webSocket, ByteBuffer data, boolean last) {
            return null;
        }

        @Override
        public CompletionStage<?> onPing(WebSocket webSocket, ByteBuffer message) {
            return null;
        }

        @Override
        public CompletionStage<?> onPong(WebSocket webSocket, ByteBuffer message) {
            return null;
        }

        @Override
        public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
            return null;
        }

        @Override
        public void onError(WebSocket webSocket, Throwable error) {

        }
    }
}

