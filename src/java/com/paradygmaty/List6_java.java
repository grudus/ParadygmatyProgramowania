package com.paradygmaty;

public class List6_java {

/*1) Napisz funkcje wkładającą element do posortowanej kolekcji, w taki sposób, aby wyjściowa kolekcja
pozostawała posortowana.
    Funkcję napisz w języku Ocaml w sposób funkcyjny przy użyciu list oraz w języku Java w sposób imperatywny
    przy użyciu tablic.*/


    static int[] insert(int[] array, int value) {
        int[] newArray = new int[array.length + 1];
        boolean inserted = false;

        for (int i = 0; i < array.length; i++) {
            if (value < array[i]) {
                if (!inserted) {
                    newArray[i] = value;
                    inserted = true;
                }
                newArray[i+1] = array[i];
            }
            else
                newArray[i] = array[i];
        }
        if (!inserted)
            newArray[array.length] = value;

        return newArray;
    }

}
