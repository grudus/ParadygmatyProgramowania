package com.paradygmaty

object List1 {

  /* Zdefiniuj funkcję flatten : 'a list list -> 'a list, która dla argumentu będącego listą list
 tworzy listę, złożoną z elementów wszystkich podlist z zachowaniem ich kolejności,
 np. flatten [[5;6];[1;2;3]] zwraca [5; 6; 1; 2; 3], czyli spłaszcza listę o jeden poziom.*/

  def flatten[T](list: List[List[T]]): List[T] = {
    if (list.isEmpty) Nil
    else list.head ++ flatten(list.tail)
  }


  /* Zdefiniuj funkcję count : 'a * 'a list -> int obliczającą ile razy dany obiekt występuje
 w danej liście, np. count ('a', ['a'; 'l'; 'a']) zwraca 2. */

  def count[T](element: T, list: List[T]): Int = {
    if (list.isEmpty) 0
    else (if (element == list.head) 1 else 0) + count(element, list.tail)
  }


  /* Zdefiniuj funkcję replicate: 'a * int -> 'a list powtarzającą dany obiekt określoną liczbę
 razy i zwracającą wynik w postaci listy, np. replicate ("la",3) zwraca ["la"; "la"; "la"].*/

  def replicate[T](elem: T, times: Int): List[T] = {
    if (times == 0) Nil
    else elem :: replicate(elem, times - 1)
  }


  /* Zdefiniuj funkcję sqrList : int list -> int list podnoszącą do kwadratu wszystkie elementy
 danej listy liczb, np. sqrList [1;2;3;-4] zwraca [1; 4; 9; 16].*/

  def sqrList(list: List[Int]): List[Int] = {
    if (list.isEmpty) Nil
    else list.head * list.head :: sqrList(list.tail)
  }

  /*  Zdefiniuj funkcję palindrome : 'a list -> bool sprawdzającą, czy dana lista jest
 palindromem, tj. równa się sobie samej przy odwróconej kolejności elementów,
 np. palindrome ['a'; 'l'; 'a'] zwraca true. */

  def palindrome[T](list: List[T]): Boolean = {
    list == list.reverse
  }

  /* Zdefiniuj swoją funkcję listLength : 'a list -> int, obliczającą długość dowolnej listy
 (oczywiście bez użycia standardowej funkcji List.length).  */

  def listLength[T](list: List[T]): Int = {
    if (list.isEmpty) 0
    else 1 + listLength(list.tail)
  }

}
