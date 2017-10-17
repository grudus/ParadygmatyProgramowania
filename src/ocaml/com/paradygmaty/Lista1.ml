let printi listOfInts = print_string (String.concat " " (List.map string_of_int listOfInts)) ;;
let prints listOfString = print_string (String.concat " " listOfString) ;;
(* 
   Zdefiniuj funkcję flatten : 'a list list -> 'a list, która dla argumentu będącego listą list
 tworzy listę, złożoną z elementów wszystkich podlist z zachowaniem ich kolejności,
np. flatten [[5;6];[1;2;3]] zwraca [5; 6; 1; 2; 3], czyli spłaszcza listę o jeden poziom. *)

let rec flatten listOfLists = 
	if listOfLists = [] then []
else List.hd listOfLists @ flatten (List.tl listOfLists) ;;

printi (flatten [[1;2;3];[4;5]]) ;;

print_endline "\n";;
(* 

  /* Zdefiniuj funkcję count : 'a * 'a list -> int obliczającą ile razy dany obiekt występuje
  w danej liście, np. count ('a', ['a'; 'l'; 'a']) zwraca 2. */ *)

  let rec count (elem, list) = 
  if list = [] then 0
else 
	(if List.hd list = elem then 1 else 0) + count (elem, (List.tl list)) ;;  

	print_int (count ('a', ['a'; 'l'; 'a'])) ;;
	print_endline "\n";;

(* 
Zdefiniuj funkcję replicate: 'a * int -> 'a list powtarzającą dany obiekt określoną liczbę
razy i zwracającą wynik w postaci listy, np. replicate ("la",3) zwraca ["la"; "la"; "la"] *)

let rec replicate elem times = 
	if times <= 0 then []
else elem :: replicate elem (times - 1) ;;

prints (replicate "la" 3) ;;

(*    Zdefiniuj funkcję sqrList : int list -> int list podnoszącą do kwadratu wszystkie elementy
danej listy liczb, np. sqrList [1;2;3;-4] zwraca [1; 4; 9; 16]. *)

let rec sqrList intList = 
	if intList = [] then []
else List.hd intList * List.hd intList :: sqrList(List.tl intList) ;;

print_endline "\n";;
printi (sqrList [1;2;3;-4]) ;;

(* Zdefiniuj funkcję palindrome : 'a list -> bool sprawdzającą, czy dana lista jest
 palindromem, tj. równa się sobie samej przy odwróconej kolejności elementów,
 np. palindrome ['a'; 'l'; 'a'] zwraca true *)

 let palindrome list = List.rev list = list ;;

 (* Zdefiniuj swoją funkcję listLength : 'a list -> int, obliczającą długość dowolnej listy
 (oczywiście bez użycia standardowej funkcji List.length) *)

 let rec listLength list = 
 	if list = [] then 0
 	else 1 + listLength (List.tl list) ;; 

