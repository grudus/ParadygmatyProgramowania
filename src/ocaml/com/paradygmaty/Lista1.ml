let printi listOfInts = print_string (String.concat " " (List.map string_of_int listOfInts)) ;;
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

 let rec count elem list = 
 	if list = [] then 0
 	else 
 		(if List.hd list = elem then 1 else 0) + count elem (List.tl list) ;;  

 print_int (count 'a' ['a'; 'l'; 'a']) ;;
