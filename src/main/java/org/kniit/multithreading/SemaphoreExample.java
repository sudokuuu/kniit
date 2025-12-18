package org.kniit.multithreading;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    static Semaphore semaphore = new Semaphore(2);
    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            int taskId = i;
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("Задача " + taskId + " начала работу");
                    Thread.sleep(2000);
                    System.out.println("Задача " + taskId + " закончила работу");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }).start();
        }
    }
}
