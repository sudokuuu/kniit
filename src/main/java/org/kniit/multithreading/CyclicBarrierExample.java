package org.kniit.multithreading;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;

public class CyclicBarrierExample {

    public static void main(String[] args) {
        int numThreads = 3;

        CyclicBarrier barrier = new CyclicBarrier(numThreads,
            () -> System.out.println("Все потоки достигли барьера!"));

        for (int i = 1; i <= numThreads; i++) {
            int threadId = i;
            new Thread(() -> {
                try {
                    System.out.println("Поток " + threadId + " подготавливается");
                    Thread.sleep(1000 + threadId * 500);

                    System.out.println("Поток " + threadId + " готов, ждёт на барьере");
                    barrier.await();

                    System.out.println("Поток " + threadId + " продолжает работу");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
