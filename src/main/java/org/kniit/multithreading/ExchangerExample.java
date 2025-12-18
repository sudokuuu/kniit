package org.kniit.multithreading;

import java.util.concurrent.Exchanger;

public class ExchangerExample {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        Thread producer = new Thread(() -> {
            try {
                String filled = "Заполненный буфер";
                System.out.println("Producer отправляет: " + filled);

                String empty = exchanger.exchange(filled);
                System.out.println("Producer получил: " + empty);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                String empty = "Пустой буфер";
                System.out.println("Consumer отправляет: " + empty);

                String filled = exchanger.exchange(empty);
                System.out.println("Consumer получил: " + filled);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();
    }
}

