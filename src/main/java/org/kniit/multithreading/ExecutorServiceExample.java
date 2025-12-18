package org.kniit.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorServiceExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 5; i++) {
            int taskId = i;
            executor.execute(() -> {
                System.out.println("Runnable-задача " + taskId +
                    " выполняется в потоке " + Thread.currentThread().getName());
            });
        }

        List<Callable<Integer>> callables = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            int number = i;
            callables.add(() -> {
                System.out.println("Callable-задача " + number +
                    " выполняется в потоке " + Thread.currentThread().getName());
                return number * number;
            });
        }

        List<Future<Integer>> futures = executor.invokeAll(callables);

        for (Future<Integer> future : futures) {
            System.out.println("Результат задачи: " + future.get());
        }

        List<Callable<String>> anyTasks = new ArrayList<>();
        anyTasks.add(() -> {
            TimeUnit.SECONDS.sleep(5);
            return "Результат из медленной задачи";
        });
        anyTasks.add(() -> {
            TimeUnit.SECONDS.sleep(6);
            return "Результат из быстрой задачи";
        });
        anyTasks.add(() -> "Результат из моментальной задачи");

        String anyResult = executor.invokeAny(anyTasks);
        System.out.println("Результат invokeAny: " + anyResult);

        executor.shutdown();
        if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
            executor.shutdownNow();
        }
    }
}
