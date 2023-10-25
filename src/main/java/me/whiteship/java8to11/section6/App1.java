package me.whiteship.java8to11.section6;

import java.util.concurrent.*;

public class App1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //runnable & callable
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };
        Future<String> submit = executorService.submit(hello); //future를 리턴
        System.out.println("start!!");
        String s = submit.get(); // 기다렸다가 꺼내쓴다 , 블로킹콜
        System.out.println("end!!");
        executorService.shutdown();
    }
}
