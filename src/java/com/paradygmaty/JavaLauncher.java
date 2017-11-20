package com.paradygmaty;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class JavaLauncher {

    private static Function<Integer, Integer> factorial;

    static {
        factorial = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer n) {
                return n > 0 ? n * factorial.apply(n - 1) : 1;
            }
        };
//        factorial = n ->  n > 0 ? n * factorial.apply(n - 1) : 1;
    }


    public static void main(String[] args) {
        int i = 0;
//
//
//
//        Integer a = 5;
//        Integer b = 5;
//        Integer c = new Integer(5);
//        Integer d = new Integer(6);
//
//        System.out.println(a == b);
//        System.out.println(a == c);
//        System.out.println(c == d);
//        new String(new StringBuilder());
//

        System.out.println(factorial.apply(5));

//        System.out.println(equals2(500, 500));
//        System.out.println(equals2(-5, -5));
    }


    static boolean equals2(Integer a, Integer b) {
        return a == b;
    }


    static boolean equals(int a, int b) {
        return a == b;
    }

}
