package com.paradygmaty.list11.zad1;

public class SemaphorCount extends Thread {
    static IntCell n = new IntCell();
    static boolean isLocked = false;
    static Object lock = new Object();
    static int counter = 0;
    int id;

    SemaphorCount() {
        id = ++counter;
    }

    @Override
    public void run() {
        int temp;
        for (int i = 0; i < 200_000; i++) {
//            System.out.printf("Id: %d, i: %d\n", id, i);

            synchronized (lock) {
                while (isLocked) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                isLocked = (true);
            }

            temp = n.getN();
            n.setN(temp + 1);

            synchronized (lock) {
                isLocked = (false);
                lock.notify();
            }
        }
    }


}


/*
class SemaphoreCount extends Thread {
    static IntCell n = new IntCell();
    static Semaphore semaphore = new Semaphore(1);

    @Override
    public void run() {
        int temp;
        for (int i = 0; i < 200000; i++) {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            temp = n.getN();
            n.setN(temp + 1);
            semaphore.release();


        }
    }

    public static void main(String[] args) {
        Count2 p = new Count2();
        Count2 q = new Count2();
        p.start();
        q.start();
        try {
            p.join();
            q.join();
        } catch (InterruptedException e) {
        }
        System.out.println("The value of n is " + n.getN());
    }
}
*/