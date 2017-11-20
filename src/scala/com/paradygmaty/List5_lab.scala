package com.paradygmaty

object List5_lab {

  /*Zdefiniuj funkcję "ldzialanie" przyjmującą dwie listy leniwe i wykonującą podane działanie
na elementach list. Wynikiem jest lista leniwa.
Użyj strumieni przedstawionych na wykładzie 5, strona 16.
Przykład:
[1;2;3], [2;3;4;5] oraz + daje [3;5;7;5]
Wyniki oczywiście powinny być zapisane w postaci leniwej!*/


  def addSteams(stream1: Stream[Int], stream2: Stream[Int]): Stream[Int] = {
    (stream1, stream2) match {
      case (Stream.Empty, stream) => stream
      case (stream, Stream.Empty) => stream
      case (h1 #:: t1, h2 #:: t2) =>
        val added = addSteams(t1, t2)
        (h1 + h2) #:: added
    }
  }


  println(addSteams(Stream(1, 2, 3), Stream(2, 3, 4, 5)).toList)

}
