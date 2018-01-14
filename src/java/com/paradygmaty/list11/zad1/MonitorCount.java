package com.paradygmaty.list11.zad1;

public class MonitorCount extends Thread {
    static IntCell n = new IntCell();
    static int counter = 0;
    private int id;
    MonitorCount() {
        id = ++counter;
    }

    @Override
    public void run() {
        int temp;
        synchronized (n) { //najpierw jedno da do konca, potem drugie
        for (int i = 0; i < 200; i++) {
//            synchronized (n) { moglo byc tutaj, wtedy by sie przeplataly
                System.out.printf("ID: %d, i: %d\n", id, i);
                temp = n.getN();
                n.setN(temp + 1);
            }
        }
    }
}
