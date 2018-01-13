package com.paradygmaty;

import java.util.Random;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class Lab10 {

    public static void main(String[] args) {
        Account account = new Account();
        account.value = 1000;

        MoneyOperation m1 = new GetMoney(account);
        MoneyOperation m2 = new AddMoney(account);

        new Thread(m1).start();
        new Thread(m2).start();

    }


    public static final Dekker DEKKER = new Dekker();

}

abstract class MoneyOperation implements Runnable {
    Account account;
    int processingTime;
    int waitingTime;
    int time = 20;
    int id;
    public MoneyOperation(Account account, int id) {
        this.account = account;
        processingTime = (int) (Math.random() * time);
        waitingTime = (int) (Math.random() * time + time);
        this.id = id;
    }

    public void run() {
        while (true) {
            try {
//                Lab10.DEKKER.Pmutex(id);
                Thread.sleep(processingTime);
                action();
                System.out.printf("[%s]: Current balance: [%d]\n",
                        Thread.currentThread().getName(),
                        account.value);

                Thread.sleep(waitingTime);

//                Lab10.DEKKER.Vmutex(id);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    abstract void action();;
}

class GetMoney  extends MoneyOperation {
    public GetMoney(Account account) {
        super(account, 0);
    }

    @Override
    void action() {
        account.value-= 100;
    }

}

class AddMoney extends MoneyOperation {
    public AddMoney(Account account) {
        super(account, 1);
    }


    @Override
    void action() {
       account.value += 100;
    }
}



class Dekker {
    public Dekker () {
        flag.set(0,0); flag.set(1,0); turn = 0;
    }

    public void Pmutex(int t) {
        int other;

        other = 1-t;
        flag.set(t,1);
        while (flag.get(other) == 1) {
            if (turn == other) {
                flag.set(t,0);
                while (turn == other)
                    ;
                flag.set(t,1);
            }
        }
    }

    public void Vmutex(int t) {
        turn = 1-t;
        flag.set(t,0);
    }

    private volatile int turn;
    private AtomicIntegerArray flag = new AtomicIntegerArray(2);
}



class Account {
    int value;
}

