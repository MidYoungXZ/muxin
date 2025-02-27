package org.example;

import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

/**
 * @projectname: muxin
 * @filename: FluxTest
 * @author: yangxz
 * @data:2024/10/29 15:38
 * @description:
 */
public class FluxTest {


    @Test
    public void fluxArrayTest() {
        Flux.just(1, 2, 3, 4, 5).subscribe(new Subscriber<Integer>() { // 1

            @Override
            public void onSubscribe(Subscription s) {
                System.out.println("onSubscribe");
                s.request(6);   // 2
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext:" + integer);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }



}
