package org.kniit.multithreading;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);

        for (int i = 1; i <= 3; i++) {
            int taskId = i;
            new Thread(() -> {
                try {
                    System.out.println("Задача " + taskId + " начала работу");
                    Thread.sleep(2000); // имитация работы
                    System.out.println("Задача " + taskId + " завершена");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            }).start();
        }

        System.out.println("Главный поток ждёт завершения...");
        latch.await();
        System.out.println("Все задачи завершены, главный поток продолжает!");
    }
}

