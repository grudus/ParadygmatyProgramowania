package com.paradygmaty;


public class DiningPhilosophers {

    public static void main(String[] args) throws Exception {

        Philosopher[] philosophers = new Philosopher[5];
        Object[] forks = new Object[philosophers.length];

        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Object();
        }

        for (int i = 0; i < philosophers.length; i++) {
            Object leftFork = forks[i];
            Object rightFork = forks[(i + 1) % forks.length];
            philosophers[i] = new Philosopher(rightFork, leftFork);

            Thread t
                    = new Thread(philosophers[i], "Philosopher " + (i + 1));
            t.start();
        }
    }
}


@SuppressWarnings("ALL")
class Philosopher implements Runnable {

    static Object guard = new Object();
    static int numOfEating = 0;
    // The forks on either side of this Philosopher
    private Object leftFork;
    private Object rightFork;

    public Philosopher(Object leftFork, Object rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        try {
            while (true) {

                synchronized (guard) {
                    while (numOfEating == 4)
                        guard.wait();
                }

                // thinking
                doAction("Thinking");
                synchronized (leftFork) {
                    ++numOfEating;
                    doAction("Picked up left fork");
                    synchronized (rightFork) {
                        doAction(" Picked up right fork - eating");
                        doAction(" Put down right fork");
                    }
                    doAction(" Put down left fork. Back to thinking");
                }
                synchronized (guard) {
                    --numOfEating;
                    guard.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }

    private void doAction(String action) throws InterruptedException {
        System.out.println(
                Thread.currentThread().getName() + " " + action);
        Thread.sleep(((int) (Math.random() * 100)));
    }

}