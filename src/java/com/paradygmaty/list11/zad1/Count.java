package com.paradygmaty.list11.zad1;

public class Count extends Thread {
    static IntCell n = new IntCell();

    @Override
    public void run() {
        int temp;
        for (int i = 0; i < 200_000; i++) {
            temp = n.getN();
            n.setN(temp + 1);
        }
    }
}
