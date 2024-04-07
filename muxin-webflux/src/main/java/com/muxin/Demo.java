package com.muxin;

import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @projectname: muxin
 * @filename: Demo
 * @author: yangxz
 * @data:2023/12/22 22:21
 * @description:
 */
public class Demo {


    public static void main(String[] args) {



        Flux.just(1,2,3)
                .map(i->{
                    try {
                        System.err.println("map"+i);
                        if (i>2) {
                            Thread.sleep(500);
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return i+10;
                })
                .timeout(Duration.ofMillis(400))
                .onErrorResume(i->{
                    if (i instanceof TimeoutException){
                        System.out.println("这里捕获到超时异常");
                    }
                    return Mono.defer(() -> Mono.just(1));
                })
                .take(3)  //这里如果是2，流程只会执行到第二个，第三个数设置的延迟异常不会被触发
                .doOnNext(i->{
                    System.out.println("on next"+i);
                })
                .subscribe(System.out::println);


        Flux.just(1,2,3).subscribe(System.out::println);

        /**
         * next重Flux中获取一个
         */
        Flux.just(1,2,3)
                .next()
                .subscribe(System.out::println);

        WebClient webClient = WebClient.create();
        Mono<ClientResponse> response = webClient.get()
                .uri("www.baidu.com")
                .exchange();

        ClientResponse clientResponse = response.block();
        Mono<String> respMono = clientResponse.bodyToMono(String.class);
        String rp = respMono.block();
        System.out.println("rp = " + rp);


        webClient.get().uri("www.baidu.com")
                .accept(MediaType.ALL)
                .retrieve()
                .onStatus(HttpStatus::is1xxInformational,clientResponse1 -> {
                    System.out.println("");
                    return Mono.error(new RuntimeException());
                })
                .bodyToMono(String.class)
                .subscribe(System.err::println);



        Flux.range(1,5).log()
                        .subscribe(System.out::println);


        Flux.just(1,2,3,4,5)
                .any(i->i%2==0)
                .subscribe(System.out::println);

        Flux.just(1,2,3,4,5)
                .reduce((x,y)->x+y)
                .subscribe(System.out::println);



        Flux.just("a","b").subscribe(System.out::println);


        Flux.range(1,25)
                .buffer(5)
                .subscribe(System.out::println);


    }


}
