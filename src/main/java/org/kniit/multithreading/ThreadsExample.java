package org.kniit.multithreading;

import java.util.concurrent.Executors;

public class ThreadsExample {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("thread begin");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("thread end");
        });
        thread.start();
        System.out.println("main thread end");

    }

}
