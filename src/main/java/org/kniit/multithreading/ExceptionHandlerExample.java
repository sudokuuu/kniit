package org.kniit.multithreading;

public class ExceptionHandlerExample {

    public static void main(String[] args) {
        Runnable r = () -> {
            System.out.println(Thread.currentThread().getName() + " OK");
            throw new ArrayIndexOutOfBoundsException("ERROR");
        };
        Thread t = new Thread(r);
        t.start();
        System.out.println(Thread.currentThread().getName() + " OK");

    }
}
