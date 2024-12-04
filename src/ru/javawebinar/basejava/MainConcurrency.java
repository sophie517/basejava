package ru.javawebinar.basejava;

public class MainConcurrency {
    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();
        deadlock(lock1, lock2);
    }

    private static void deadlock(Object lock1, Object lock2) {
        Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 1: holding lock1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 1: waiting for lock2...");
                synchronized (lock2) {
                    System.out.println("Thread 1: congratulations! no deadlock");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Thread 2: holding lock2");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 2: waiting for lock1...");
                synchronized (lock1) {
                    System.out.println("Thread 2: congratulations! no deadlock");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
