package com.paradygmaty.list11.zad4;

import java.util.concurrent.Semaphore;

public class DinigRoom {
    private Semaphore doorman;
    private Semaphore[] chopsticks;
    private Philosopher[] philosopher;
    private final int N;

    public DinigRoom(int N) {
        this.N = N;
        doorman = new Semaphore(N - 1);
    }

    private void createForks() {
        // Create the chopsticks, 1 per philosopher
        chopsticks = new Semaphore[N];
        for (int f = 0; f < N; f++) {
            // each chopsticks is a single resource
            chopsticks[f] = new Semaphore(1, true);
        }
    }

    private void invitePhilosophers() {
        // Create the philosophers, pass in their chopstickss
        philosopher = new Philosopher[N];
        for (int ID = 0; ID < N; ID++)
            philosopher[ID] = new Philosopher(ID, chopsticks[ID], chopsticks[(ID + 1) % N]);
        Philosopher.doorman = doorman;
    }

    private void startFeast() {
        createForks();
        invitePhilosophers();

        System.out.println("Start dining!");

        // Start the philosophers
        for (int i = 0; i < N; i++) {
            philosopher[i].start();
        }

        // Wait for them to finish
        for (int i = 0; i < N; i++) {
            try {
                philosopher[i].join();
            } catch (InterruptedException ignored) {
            }
        }
        System.out.println("Done eating.");
    }

    /**
     * Dining simulations - puts philosophers by the table with their chopstickss
     */
    public static void main(String[] args) {
        new DinigRoom(5).startFeast();            // five philosophers, five chopstickss
    }
}
