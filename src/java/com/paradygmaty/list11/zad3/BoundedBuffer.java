package com.paradygmaty.list11.zad3;

import static java.lang.Thread.currentThread;

public class BoundedBuffer implements Produce, Consume {
    final private int N;
    private int in = 0, out = 0, n = 0;
    private int[] elems;

    public BoundedBuffer(int N) {
        this.N = N;
        elems = new int[N];
    }

    public synchronized void put(int x) {
        while (n >= N) {
            System.out.println(currentThread().getName() + " waiting with " + x);
            waiting();
        }
        elems[in] = x;
        in = (in + 1) % N;
        n += 1;
        System.out.println(currentThread().getName() + " produced: " + x);
        if (n == 1) notifyAll();
    }

    public synchronized int take() {
        while (n == 0) {
            System.out.println(currentThread().getName() + " waiting");
            waiting();
        }
        int x = elems[out];
        out = (out + 1) % N;
        n -= 1;
        System.out.println(currentThread().getName() + " consuming: " + x);
        if (n == N - 1) notifyAll();
        return x;
    }

    private void waiting() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

