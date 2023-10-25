package me.whiteship.java8to11.section6;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class App1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //runnable & callable
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };
         Callable<String> java = () -> {
            Thread.sleep(3000L);
            return "Java";
        };
         Callable<String> hyoeun = () -> {
            Thread.sleep(1000L);
            return "Hyoeun";
        };
        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(hello, java, hyoeun));
        futures.forEach(f-> {
            try {
                System.out.println("f = " + f.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        executorService.shutdown();
    }
}
