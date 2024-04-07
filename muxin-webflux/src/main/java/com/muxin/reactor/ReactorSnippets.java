package com.muxin.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

/**
 * @projectname: muxin
 * @filename: ReactorSnippets
 * @author: yangxz
 * @data:2023/12/24 20:15
 * @description:
 */
public class ReactorSnippets {
    private static List<String> words = Arrays.asList(
            "the",
            "quick",
            "brown",
            "fox",
            "jumped",
            "over",
            "the",
            "lazy",
            "dog"
    );

    @Test
    public void simpleCreation() {
        Flux<String> fewWords = Flux.just("Hello", "World");
        Flux<String> manyWords = Flux.fromIterable(words);

        fewWords.subscribe(System.out::println);
        System.out.println();
        manyWords.subscribe(System.out::println);
    }

    /**
     * 为了输出 Fox 句子中的各个字母，我们还需要 flatMap （就像我们在 RxJava 中所做的那样），
     * 但在 Reactor 中我们使用 fromArray 而不是 from 。然后我们要过滤掉重复的字母并使用
     * distinct 和 sort 对它们进行排序。最后，我们希望为每个不同的字母输出一个索引，
     * 这可以使用 zipWith 和 range 来完成：
     */
    @Test
    public void findingMissingLetter() {
        Flux<String> manyLetters = Flux
                .fromIterable(words)
                .flatMap(word -> Flux.fromArray(word.split("")))
                .distinct()
                .sort()
                .zipWith(Flux.range(1, Integer.MAX_VALUE),
                        (string, count) -> String.format("%2d. %s", count, string));

        manyLetters.subscribe(System.out::println);
    }


    /**
     * 上述结果 s丢失
     * 解决此问题的一种方法是更正原始单词数组，但我们也可以使用 concat/concatWith 和 Mono
     */
    @Test
    public void restoringMissingLetter() {
        Mono<String> missing = Mono.just("s");
        Flux<String> allLetters = Flux
                .fromIterable(words)
                .flatMap(word -> Flux.fromArray(word.split("")))
                .concatWith(missing)
                .distinct()
                .sort()
                .zipWith(Flux.range(1, Integer.MAX_VALUE),
                        (string, count) -> String.format("%2d. %s", count, string));

        allLetters.subscribe(System.out::println);
    }


    /**
     *上一篇文章指出了 Rx 词汇和 Streams API 之间的相似之处，事实上，
     * 当数据可以从内存中轻松获得时，Reactor 与 Java Streams
     * 一样，以简单推送模式运行（请参阅下面的反压部分以了解原因）。
     * 更复杂和真正的异步代码片段不适用于这种仅在主线程中订阅的模式，
     * 主要是因为控制权将返回到主线程，然后在订阅完成后立即退出应用程序。例如：
     */
    @Test
    public void shortCircuit() {
        Flux<String> helloPauseWorld =
                Mono.just("Hello")
                        .concatWith(Mono.just("world")
                                .delaySubscription(Duration.ofMillis(500)));
        helloPauseWorld.subscribe(System.out::println);
    }


    /**
     *此代码片段打印“Hello”，但无法打印延迟的“world”，因为测试终止得太早。
     * 在只编写这样的主类的代码片段和测试中，您通常会希望恢复到阻塞行为。
     * 为此，您可以创建一个 CountDownLatch 并在订阅者中调用 countDown
     * （在 onError 和 onComplete 中）。但这并不是很有反应，不是吗？
     * （如果您忘记倒数，例如出现错误怎么办？）
     *
     * 解决该问题的第二种方法是使用恢复到非反应性世界的运算符之一。
     * 具体来说， toIterable 和 toStream 都将产生阻塞实例。因此，让我们使用 toStream 作为示例
     *
     *
     */
    @Test
    public void blocks() {
        Flux<String> helloPauseWorld =
                Mono.just("Hello")
                        .concatWith(Mono.just("world")
                                .delaySubscription(Duration.ofMillis(500)));

        helloPauseWorld.toStream()
                .forEach(System.out::println);
    }


    /**
     *正如我们上面提到的，RxJava amb() 运算符已重命名为 firstEmitting
     * （这更清楚地暗示了该运算符的用途：选择要发出的第一个 Flux ）。
     * 在下面的示例中，我们创建一个启动延迟 450 毫秒的 Mono 和一个发出值的 Flux ，
     * 每个值之前有 400 毫秒的暂停。当 firstEmitting() 它们在一起时，
     * 由于 Flux 中的第一个值出现在 Mono 的值之前，因此 Flux 最终被播放：
     * 这会打印句子的每个部分，每个部分之间有 400 毫秒的短暂暂停。
     *
     */
    @Test
    public void firstEmitting() {
        Mono<String> a = Mono.just("oops I'm late")
                .delaySubscription(Duration.ofMillis(500));
        Flux<String> b = Flux.just("let's get", "the party", "started")
                .delaySubscription(Duration.ofMillis(500));

        Flux.first(a, b)
                .toIterable()
                .forEach(System.out::println);
    }






}