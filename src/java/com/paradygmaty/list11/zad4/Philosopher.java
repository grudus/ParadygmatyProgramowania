package com.paradygmaty.list11.zad4;


import java.util.Random;
import java.util.concurrent.Semaphore;

public class Philosopher extends Thread {
    private static final Random random = new Random();
    public static Semaphore doorman;

    private int ID;
    private Semaphore chopstickLeft;
    private Semaphore chopstickRight;

    public Philosopher(int ID, Semaphore chopstickLeft, Semaphore chopstickRight) {
        this.ID = ID;
        this.chopstickLeft = chopstickLeft;
        this.chopstickRight = chopstickRight;
    }

    private void updateStatus(String str) {
        System.out.printf("Philosopher of ID:%d %s\n", ID, str);
    }


    private void pause() {
        try {
            sleep(random.nextInt(1000));
        } catch (InterruptedException ignored) {
        }
    }

    private void think() {
        updateStatus("is thinking");
        pause();
    }

    private void tryToEat() {
        if (isAllowedToEat()) {
            updateStatus("is hungry and is trying to pick up his chopsticks");
            try {

                chopstickLeft.acquire();
//                chopstickRight.acquire();

                if (!chopstickRight.tryAcquire()) {
                    updateStatus(" was not able to get neighbor's chopstick");
                } else {
                    updateStatus("picked up his chopsticks and is eating");
                    pause();


                    updateStatus("puts down his chopsticks");
                }
            } catch (InterruptedException e) {
                updateStatus("was interrupted while waiting for his chopstick");
            } finally {
                chopstickLeft.release();
                chopstickRight.release();
                doorman.release();
            }
        }
    }

    private boolean isAllowedToEat() {
        if (doorman.tryAcquire()) {
            updateStatus("has just gone into dinner room");
            return true;
        } else {
            updateStatus("can't go into dinner room");
            return false;
        }
    }

    /**
     * Philosophize between all meals are consumed
     */
    @Override
    public void run() {
        while (true) {
            think();
            tryToEat();
        }
    }
}