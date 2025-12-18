package org.kniit.multithreading;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class LockStore {

    private int i = 0;
    private boolean isRead = true;

    private final Lock lock = new ReentrantLock();
    private final Condition canProduce = lock.newCondition();
    private final Condition canConsume = lock.newCondition();

    public void produce(int value) throws InterruptedException {
        lock.lock();
        try {
            while (!isRead) {
                canProduce.await();
            }
            i = value;
            isRead = false;
            System.out.println("Произведено: " + value);
            canConsume.signal();
        } finally {
            lock.unlock();
        }
    }

    public int consume() throws InterruptedException {
        lock.lock();
        try {
            while (isRead) {
                canConsume.await();
            }
            System.out.println("Потреблено: " + i);
            isRead = true;
            canProduce.signal();
            return i;
        } finally {
            lock.unlock();
        }
    }

}

public class LockExample {

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
