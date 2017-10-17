(* Napisz funkcję, która przyjmuje na wejściu tablicę liczb rzeczywistych, a następnie zwraca sumę tych
liczb. Proszę przygotować minimalny zestaw testów dla tej funkcji prezentujący jej działanie. (Pusta
tablica to też tablica 😉). *)

let rec sum list = 
	if list = [] then 0.0
	else List.hd list +. sum (List.tl list) ;; 

print_float (sum [1.0;2.5;3.0]) ;;
print_endline "" ;;
print_float (sum [-1.0]) ;;
print_endline "" ;;
print_float (sum []) ;;
print_endline "" ;;

(* Napisz funkcję, która przejmuje dwa parametry: napis (będący separatorem) oraz tablicę napisów.
Funkcja ta ma zwracać pojedynczy napis składający się z napisów tablicy oddzielonych separatorem *)

let rec join (separator, listOfTexts) = 
	if listOfTexts = [] then ""
	else if List.tl listOfTexts = [] then List.hd listOfTexts  
	else List.hd listOfTexts ^ separator ^ join (separator, (List.tl listOfTexts)) ;;
w
print_endline (join (" || ", ["ala";"ma";"kota";"a";"kot";"ma";"ale"])) ;;
print_endline (join ("", ["ala";"ma"])) ;;
print_endline (join ("|", ["ala"])) ;;
print_endline (join ("|", [])) ;;

let rec areOrdered (x1, x2, x3) = x3 > x2 && x2 > x1 ;;

print_endline (if areOrdered(1,2,3) then "true" else "false") ;;
print_endline (if areOrdered(3,2,1) then "true" else "false") ;;
print_endline (if areOrdered(3,3,3) then "true" else "false") ;;