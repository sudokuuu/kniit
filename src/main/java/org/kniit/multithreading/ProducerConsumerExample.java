package org.kniit.multithreading;

class Store {

    private int i = 0;
    private boolean isRead = true;

    public synchronized void produce(int value) throws InterruptedException {
        while (!isRead) {
            wait();
        }
        i = value;
        isRead = false;
        System.out.println("Произведено: " + value);
        notify();
    }

    public synchronized int consume() throws InterruptedException {
        while (isRead) {
            wait();
        }
        System.out.println("Потреблено: " + i);
        isRead = true;
        notify();
        return i;
    }

}

public class ProducerConsumerExample {

    public static void main(String[] args) {
        LockStore store = new LockStore();

        Thread producer = new Thread(() -> {
            int i = 1;
            try {
                while (true) {
                    store.produce(i++);
                    Thread.sleep(300); // задержка для наглядности
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                while (true) {
                    store.consume();
                    Thread.sleep(500); // задержка для наглядности
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();
    }

}

