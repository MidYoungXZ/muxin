package com.muxin.demo.completablefuture;

import cn.hutool.core.thread.ThreadUtil;
import org.aspectj.weaver.World;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * @projectname: muxin
 * @filename: Demo1
 * @author: yangxz
 * @data:2024/5/7 上午10:37
 * @description:
 */
public class Demo1 {


    private static final ThreadPoolTaskExecutor springExecutor = new ThreadPoolTaskExecutor();

    private static final  ThreadPoolExecutor jdkExecutor = new ThreadPoolExecutor(10, 10, 60L, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10000),new ThreadPoolExecutor.CallerRunsPolicy());

    static {
        springExecutor.setCorePoolSize(5);
        springExecutor.setMaxPoolSize(10);
        springExecutor.setKeepAliveSeconds(60);
        springExecutor.setQueueCapacity(2048);
        springExecutor.setWaitForTasksToCompleteOnShutdown(true);
        springExecutor.setThreadNamePrefix("demo-executor-");
        springExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        springExecutor.initialize();
    }







    /**
     * runAsync()
     * thenRun()  【执行】直接开启一个异步线程执行任务，不接收任何参数，回调方法没有返回
     */
    public static void thenRun() {
        CompletableFuture.runAsync(() -> {//指定线程池springExecutor
            System.out.println("thread name:" + Thread.currentThread().getName() + " first step...");
        }, springExecutor).thenRun(() -> {//默认使用主线程
            System.out.println("thread name:" + Thread.currentThread().getName() + " second step...");
        }).thenRunAsync(() -> {//使用内置线程池ForkJoinPool
            System.out.println("thread name:" + Thread.currentThread().getName() + " third step...");
        });
//         执行结果
//        thread name:demo-executor-1 first step...
//        thread name:main second step...
//        thread name:ForkJoinPool.commonPool-worker-1 third step...
    }

    /**
     * thenApply：【提供】可以提供返回值，接收上一个任务的执行结果，作为下一个Future的入参，回调方法是有返回值的；
     */
    public static void thenApply() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("thread name:" + Thread.currentThread().getName() + " first step...");
            return "hello";
        }, springExecutor).thenApply((result1) -> {
            String targetResult = result1 + " austin";
            System.out.println("first step result: " + result1);
            System.out.println("thread name:" + Thread.currentThread().getName() + " second step..., targetResult: " + targetResult);
            return targetResult;
        });
//        thread name:demo-executor-1 first step...
//        first step result: hello
//        thread name:demo-executor-1 second step..., targetResult: hello austin //thenApply虽然没有指定线程池，但是默认是复用它上一个任务的线程池的
    }




    /**
     * thenAccept：【接收】可以接收上一个任务的执行结果，作为下一个Future的入参，回调方法是没有返回值的；
     */
    public static void thenAccept() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("thread name:" + Thread.currentThread().getName() + " first step...");
            return "hello";
        }, springExecutor).thenAccept((result1) -> {
            String targetResult = result1 + " austin";
            System.out.println("first step result: " + result1);
            System.out.println("thread name:" + Thread.currentThread().getName() + " second step..., targetResult: " + targetResult);
        });
//        thread name:demo-executor-1 first step...
//        first step result: hello
//        thread name:demo-executor-1 second step..., targetResult: hello austin //同样复用前一个任务的线程池
//        thenAccept()和thenApply()的用法实际上基本上一致，区别在于thenAccept()回调方法是没有返回值的，而thenApply()回调的带返回值的。
    }

    /**
     *它能够将两个 CompletableFuture 的结果合并，然后执行特定的操作（使用传入的 BiConsumer 对象对合并结果进行处理），
     * 并返回一个新的 CompletableFuture<Void> 对象（因为该方法没有返回值）
     */
    public static void thenAcceptBoth() throws Exception{
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 123);
        CompletableFuture<Void> future3 = future1.thenAcceptBoth(future2, (s, i) -> {
            System.out.println(s + " " + i); // 输出：Hello 123
        });
        future3.get(); // 阻塞等待任务完成
    }

    /**
     * thenCombine：【结合】可以结合不同的Future的返回值，做为下一个Future的入参，回调方法是有返回值的；
     */
    public static void thenCombine() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行future1开始...");
            return "Hello";
        }, springExecutor);
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行future2开始...");
            return "World";
        }, springExecutor);
        future1.thenCombine(future2, (result1, result2) -> {
            String result = result1 + " " + result2;
            System.out.println("获取到future1、future2聚合结果：" + result);
            return result;
        }).thenAccept(result -> {
            System.out.println("thenAccept 执行～");
            System.out.println(result);
        });
//        执行future1开始...
//        执行future2开始...
//        获取到future1、future2聚合结果：Hello World
//        thenAccept 执行～
//        Hello World
        //future1和future2并行，后执行thenCombine
    }

    /**
     * thenCompose：【组成】将上一个Future实例结果传递给下一个实例中。
     */
    public static void thenCompose() {
        CompletableFuture.supplyAsync(() -> {
            // 第一个Future实例结果
            System.out.println("thread name:" + Thread.currentThread().getName() + " 执行future1开始...");
            return "Hello";
        }, springExecutor).thenCompose(result1 -> CompletableFuture.supplyAsync(() -> {
            // 将上一个Future实例结果传到这里
            System.out.println("thread name:" + Thread.currentThread().getName() + " 执行future2开始..., 第一个实例结果：" + result1);
            return result1 + " World";
        })).thenCompose(result12 -> CompletableFuture.supplyAsync(() -> {
            // 将第一个和第二个实例结果传到这里
            System.out.println("thread name:" + Thread.currentThread().getName() + " 执行future3开始..., 第一第二个实现聚合结果：" + result12);
            String targetResult = result12 + ", I am austin!";
            System.out.println("最终输出结果：" + targetResult);
            return targetResult;
        }));
//        thread name:demo-executor-1 执行future1开始...
//        thread name:ForkJoinPool.commonPool-worker-1 执行future2开始..., 第一个实例结果：Hello
//        thread name:ForkJoinPool.commonPool-worker-1 执行future3开始..., 第一第二个实现聚合结果：Hello World
//        最终输出结果：Hello World, I am austin!
        //thenCompose被线性执行的

    }

    /**
     * 根据多个 CompletableFuture 对象生成一个新的 CompletableFuture 对象，等待所有异步任务执行完毕后，把所有的异步任务结果封装到一个数组对象中并返回。
     */
    public static void allOf() throws Exception{
        long l = System.currentTimeMillis();
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            ThreadUtil.sleep(2000);
            return "Hello";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            ThreadUtil.sleep(1000);
            return "World";
        });
        CompletableFuture<Void> allFuture = CompletableFuture.allOf(future1, future2);
        allFuture.join();
        long l2 = System.currentTimeMillis();
        System.out.println("Future1 result: " + future1.get());
        System.out.println("Future2 result: " + future2.get());
        System.out.println("cost time: " + (l2 - l));
//        Future1 result: Hello
//        Future2 result:World
//        cost time: 2008
    }


    public static void anyOf() throws Exception{
        long l = System.currentTimeMillis();
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            ThreadUtil.sleep(2000);
            return "Hello";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            ThreadUtil.sleep(1000);
            return "World";
        });
        CompletableFuture<Object> anyFuture = CompletableFuture.anyOf(future1, future2);
        anyFuture.thenAccept(result -> System.out.println("Result: " + result));
        String joinResult = (String) anyFuture.join();
        System.out.println(joinResult);
//        Result: World
//                World
    }







    public static void main(String[] args) throws Exception{
//        thenRun();
//        thenApply();
//        thenAccept();
//        thenCombine();
//        thenCompose();
//        allOf();
        anyOf();



    }

    /**
     * CompletableFuture实例化创建
     *
     *     // 返回一个新的CompletableFuture，由线程池ForkJoinPool.commonPool()中运行的任务异步完成，不会返回结果。
     *     public static CompletableFuture<Void> runAsync(Runnable runnable);
     *     // 返回一个新的CompletableFuture，运行任务时可以指定自定义线程池来实现异步，不会返回结果。
     *     public static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor);
     *
     *     // 返回由线程池ForkJoinPool.commonPool()中运行的任务异步完成的新CompletableFuture，可以返回异步线程执行之后的结果。
     *     public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier);
     *     public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor);
     */


    /**
     * 获取CompletableFuture结果
     *
     *     public  T   get()   阻塞式获取执行结果，如果任务还没有完成则会阻塞等待知直到任务执行完成
     *     public  T   get(long timeout, TimeUnit unit)   带超时的阻塞式获取执行结果
     *     public  T   getNow(T valueIfAbsent)  如果已完成，立刻返回执行结果，否则返回给定的valueIfAbsent
     *     public  T   join()  该方法和get()方法作用一样， 不抛异常的阻塞式获取异步执行结果
     *     public CompletableFuture<Object> allOf()  当给定的所有CompletableFuture都完成时，返回一个新的CompletableFuture
     *     public CompletableFuture<Object> anyOf()   当给定的其中一个CompletableFuture完成时，返回一个新的CompletableFuture
     */

    /**
     * 结果处理
     *
     * public CompletableFuture<T> whenComplete(BiConsumer<? super T,? super Throwable> action)
     * public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T,? super Throwable> action)
     * public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T,? super Throwable> action, Executor executor)
     */

    /**
     * 异常处理
     *
     * handle：返回一个新的CompletionStage，当该阶段正常或异常完成时，将使用此阶段的结果和异常作为所提供函数的参数执行，不会将内部异常抛出。
     * whenComplete：返回与此阶段具有相同结果或异常的新CompletionStage，该阶段在此阶段完成时执行给定操作。与方法handle不同，会将内部异常往外抛出。
     * exceptionally：返回一个新的CompletableFuture，CompletableFuture提供了异常捕获回调exceptionally，相当于同步调用中的try/catch。
     *
     */




}
