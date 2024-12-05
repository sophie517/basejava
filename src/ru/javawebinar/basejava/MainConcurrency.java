package ru.javawebinar.basejava;

public class MainConcurrency {
    public static void main(String[] args) {
        deadlock("lock1", "lock2");
        deadlock("lock2", "lock1");
    }

    private static void deadlock(Object lock1, Object lock2) {
        new Thread(() -> {
            synchronized (lock1) {
                System.out.println("holding " + lock1);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("waiting for " + lock2);
                synchronized (lock2) {
                    System.out.println("congratulations! no deadlock");
                }
            }
        }).start();
    }
}
