package org.kniit.multithreading;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicExample {

    private static AtomicInteger counter = new AtomicInteger(0);

    public static void incrementUnsafe() {
        counter.incrementAndGet();
    }


    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    incrementUnsafe();
                }
            });
            threads[i].start();
        }
        for (Thread t : threads) {
            t.join();
        }
        System.out.println("Результат c использование AtomicInteger: " + counter.get());
    }

}
