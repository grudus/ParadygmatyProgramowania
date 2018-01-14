package com.paradygmaty.list11.zad1;

class Launcher {

    public static void main(String[] args) {
        SemaphorCount p = new SemaphorCount();
        SemaphorCount q = new SemaphorCount();
        p.start();
        q.start();
        try { p.join(); q.join(); }
        catch (InterruptedException e) { }
        System.out.println("The value of n is " + p.n.getN());
    }
}