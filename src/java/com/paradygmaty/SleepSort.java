package com.paradygmaty;

import java.util.stream.Stream;

import static java.util.concurrent.TimeUnit.SECONDS;

public class SleepSort {


    public static void main(String[] args) {

        Stream.of(5,3,2,6,1).forEach(i -> {
            new Thread(() -> {
                try { Thread.sleep(i*100);} catch (Exception e) {}
                System.out.println(i);
            }).start();
        });
    }
}
