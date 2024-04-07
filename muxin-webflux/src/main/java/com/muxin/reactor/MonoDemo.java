package com.muxin.reactor;

import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * @projectname: muxin
 * @filename: MonoDemo
 * @author: yangxz
 * @data:2023/12/25 10:18
 * @description:
 */
public class MonoDemo {


    public static void main(String[] args) {


        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        Mono.fromCallable(() -> {
            ArrayList<String> upperCaseList = new ArrayList<>();
            for (String str : list) {
                upperCaseList.add(str.toUpperCase());
            }
            return String.join(",", upperCaseList);
        }).subscribe(s->{

        });


        Mono.just(list)
                .flatMap(i -> Mono.just(i.stream()))
                .subscribe(s -> {
                    System.out.println(s.collect(Collectors.toList()));
                });

        Mono.just(list)
                .map(i -> {
                    ArrayList<String> strings = new ArrayList<>();
                    for (String string : i) {
                        strings.add(string.toUpperCase());
                    }
                    return strings;
                })
                .subscribe(s -> {
                    System.out.println(s);
                });
    }


}
