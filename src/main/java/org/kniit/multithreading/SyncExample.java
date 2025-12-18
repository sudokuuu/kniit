package org.kniit.multithreading;

public class SyncExample {

    private static volatile int counter = 0;

    public static void incrementUnsafe() {
        counter++;
    }

    public static synchronized void incrementSafe() {
        counter++;
    }

    public static void main(String[] args) throws InterruptedException {
        counter = 0;
        Thread[] threads = new Thread[100];
        long start = System.currentTimeMillis();
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
        System.out.println(System.currentTimeMillis() - start);
        System.out.println("Результат без synchronized: " + counter);

        counter = 0;
        start = System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    incrementSafe();
                }
            });
            threads[i].start();
        }
        for (Thread t : threads) t.join();
        System.out.println(System.currentTimeMillis() - start);
        System.out.println("Результат с synchronized: " + counter);
    }

}

