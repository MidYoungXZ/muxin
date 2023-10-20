package com.muxin.system;

import com.muxin.system.service.IUserService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SystemApplication.class})
public class SpringTest {

    @Autowired
    private IUserService userService;

    public static void main(String[] args) {




        Flux<Integer> ints = Flux.range(1, 4)
                .map(i -> {
                    if (i <= 3) return i;
                    throw new RuntimeException("Got to 4");
                });
        ints.subscribe(i -> System.out.println(i),
                error -> System.err.println("Error: " + error));


    }




}