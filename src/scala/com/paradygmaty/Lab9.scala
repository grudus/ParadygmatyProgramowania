package com.paradygmaty

/*.
2.
a) Napisz klasę Time, w której konstruktor główny pobiera godzinę jako argument.
Konstruktor ma zamieniać wartości ujemne na 0. Klasa ma mieć jedno pole
modyfikowalne. Kolejne zmiany wartości godziny mają również zamieniać wartości
ujemne na 0.
b) Dodaj do klasy Time obiekt towarzyszący, umożliwiający tworzenie instancji tej klasy
bez użycia new.*/

class Time(h: Int) {
  var _hour = Math.max(h, 0)

  def hour = _hour

  def hour_=(h: Int) = _hour = Math.max(0, h)

}

object Time {
  def apply(h: Int): Time = new Time(h)
}

/*Zdefiniuj klasę Time z dwiema modyfikowalnymi własnościami hour i minute, oraz
metodą before(other: Time): Boolean sprawdzającą, czy zapamiętany moment czasowy
poprzedza inny moment. Egzemplarz klasy powinien być konstruowany za pomocą
new Time(godz, min), np. new Time(21, 15). Sprawdzaj poprawność argumentów, w razie
potrzeby zgłaszaj wyjątek IllegalArgumentException. Poprawność zmian czasu też ma
być sprawdzana.
b) Zmień klasę Time z podpunktu a) w taki sposób, żeby czas był pamiętany jako liczba
minut, które upłynęły od północy. Klasa ma mieć tylko jedno pole. Publiczny interfejs
klasy i jej funkcjonalność mają pozostać niezmienione, tzn. obie implementacje klasy
mają być pod tym względem nierozróżnialne.*/

class Time2(h: Int, m: Int) {
  var hour_ : Int = if (h >= 0 && h <= 24)
    h else throw new IllegalArgumentException
  var minute_ : Int = if (m >= 0 && m <= 60)
    m else throw new IllegalArgumentException

  def hour = hour_

  def hour_=(hour: Int) = if (hour >= 0 && hour <= 24)
    hour else throw new IllegalArgumentException

  def minute = minute_

  def minute_=(minute: Int): Int = if (minute >= 0 && minute <= 60)
    minute else throw new IllegalArgumentException


  def before(time: Time2): Boolean =
    hour < time.hour && minute < time.minute
}

class Time3(m: Int) {
  private var minute_ = if (m >= 0 && m <= 60 * 24) m
  else throw new IllegalArgumentException

  def hour = minute_ / 60

  def hour_=(h: Int) = minute_ = if (m >= 0 && m <= 60 * 24) h * 60 + minute
  else throw new IllegalArgumentException

  def minute = minute_ % 60

  def minute_=(min: Int) = minute_ = if (min >= 0 && min <= 60)
    hour * 60 + min else throw new IllegalArgumentException


  def before(time: Time3): Boolean =
    minute < time.minute

  override def toString() = s"$hour:$minute"
}

class Pojazd(val producent: String, val model: String, val rok: Int, var rejestracja: String) {

  def this(p: String, m: String) {
    this(p, m, -1, "")
  }

  def this(p: String, m: String, r: Int) {
    this(p, m, r, "")
  }


  def this(p: String, m: String, r: String) {
    this(p, m, -1, r)
  }

}




