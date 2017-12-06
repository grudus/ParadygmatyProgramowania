package com.paradygmaty;

public class List8_java {

    int zawartość = 0;
    static void argNiemodyfikowalny(final List8_java zmienna) {
        zmienna.zawartość = 1;
//        zmienna = null;
    }
    static void argModyfikowalny(List8_java zmienna) {
        zmienna.zawartość = 1;
        zmienna = null;
    }
    public static void main(String[] args) {
        List8_java modyfikowalna = new List8_java();
        final List8_java niemodyfikowalna = new List8_java();

        argModyfikowalny(niemodyfikowalna);
        System.out.println(niemodyfikowalna.zawartość);
// tutaj wstaw instrukcje
    }

}
