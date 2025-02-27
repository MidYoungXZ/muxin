package com.muxin;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufFlux;
import reactor.netty.http.client.HttpClient;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class HttpClientPostExample {


    public static void main(String[] args) throws Exception{

        ExecutorService threadPool = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            Thread.sleep(50);
            System.err.println("第"+i+"次");
            // 定义请求体为JSON数组
            String jsonArray = "[\n" +
                    "    {\n" +
                    "        \"chan\": \"BTBIZ\",\n" +
                    "        \"name\": \"BT_EVENT\",\n" +
                    "        \"time\": 1697707619000,\n" +
                    "        \"traceid\": \"traceid0000009" +i+"\",\n"+
                    "        \"phone_no\": \"phone_no00000001\",\n" +
                    "        \"dtfield\": [\n" +
                    "            \"dt001\",\n" +
                    "            \"dt002\",\n" +
                    "            \"dt003\"\n" +
                    "        ]\n" +
                    "    }\n" +
                    "]";
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        request(jsonArray);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            };
            threadPool.execute(runnable);
        }
    }




    public static void request(String jsonArray) throws InterruptedException {
        // 创建HttpClient实例
        HttpClient client = HttpClient.create()
                // 设置连接超时为5秒
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                // 配置读取和写入超时
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(10))
                                .addHandlerLast(new WriteTimeoutHandler(10))
                );

        // 记录开始时间
        Instant startTime = Instant.now();

        // 发送POST请求
        Mono<String> responseMono = client
                .post()
                .uri("http://10.100.2.70:9100/BANK/BTBIZ/BT_EVENT/rule")
                .send(ByteBufFlux.fromString(Mono.just(jsonArray)))
                // 设置总超时时间为10秒
                .responseSingle((response, byteBufMono) ->
                        byteBufMono.asString(StandardCharsets.UTF_8)
                                .map(body -> "Status: " + response.status().code() + "\nBody: " + body)
                )
                .timeout(Duration.ofMillis(2500)) // 设置请求超时
                .doOnNext(response -> {
                    // 请求成功时，记录结束时间
                    Instant endTime = Instant.now();
                    Duration duration = Duration.between(startTime, endTime);
                    // 打印返回信息和耗时
                    System.out.println(response);
                    System.out.println("Time taken: " + duration.toMillis() + " ms");
                })
                .doOnError(throwable -> {
                    throwable.printStackTrace();
                    // 发生超时或其他异常时的处理
                    System.err.println("Request failed: " + throwable.getMessage());
                });

        // 发起请求并阻塞等待结果，超时将触发异常
        try {
            responseMono.block(); // 阻塞等待响应
        } catch (Exception e) {
            System.err.println("Request aborted due to timeout or error.");
        }
        System.out.println("Request completed.");
        Thread.sleep(100000);
    }
}
