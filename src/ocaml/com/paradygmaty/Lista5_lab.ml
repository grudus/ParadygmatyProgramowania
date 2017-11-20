(* 
WAŻNE1: Do każdego zadania przygotuj 3 testy sprawdzające poprawność działania!
WAŻNE2: Wolno używać tylko funkcji bibliotecznych o złożoności obliczeniowej O(1).
Użyj następującej definicji listy oraz listy leniwej:
type 'a nlist = Koniec | Element of 'a * ('a nlist);;
type 'a llist = LKoniec | LElement of 'a * (unit -> 'a llist);;
Pomocne funkcje z wykładu: lfrom, ltake
WAŻNE3: Wszystkie funkcje napisz przy pomocy zwykłej rekurencji!!

1) Zdefiniuj funkcję "podziel" oraz "lpodziel" dzielącą listę leniwą na dwie listy leniwe.
 W pierwszej liście mają znaleźć się elementy o indeksach nieparzystych a w drugiej o parzystych.
 Przykład:
 [5;6;3;2;1] -> [5;3;1] oraz [6;2]
 Wyniki oczywiście powinny być zapisane w postaci list zadanej reprezentacji!
  *)

type 'a nlist = Koniec | Element of 'a * ('a nlist);;
type 'a llist = LKoniec | LElement of 'a * (unit -> 'a llist);;

let rec lfrom k = LElement (k, function () -> lfrom (k+1));;
let rec from k = Element(k, from(k+1)) ;;

let rec ltake = function
	| (0, _) -> []
	| (_, LKoniec) -> []
	| (n, LElement(x,xf)) -> x::ltake(n-1, xf());;

let rec take = function 
	| (0, _) -> [] 
	| (_, Koniec) -> [] 
	| (n, Element(a, xd)) -> a::take(n-1, xd) ;;



let rec podziel nlist = 
	let rec dziel = function
		| (_, Koniec) -> (Koniec, Koniec)
		
		| (i, Element(a, next)) when i mod 2 = 0 -> 
			let (left, right) = dziel (i+1, next) in 
			(left, Element(a, right))
		
		| (i, Element(a, next)) -> 
			let (left, right) = dziel (i+1, next) in
			(Element(a, left), right) 
	
	in dziel (1, nlist) ;;  

let rec lpodziel llist =
	let rec dziel = function
		| (_, LKoniec) -> (LKoniec, LKoniec)

		| (i, LElement(a, xf)) when i mod 2 = 0 -> 
			let (left, right) = dziel (i+1, xf()) in 
			(left, LElement(a, fun () -> right))

		| (i, LElement(a, xf)) -> 
			let (left, right) = dziel (i+1, xf()) in
			(LElement(a, fun() -> left), right) 
	
	in dziel (1, llist) ;;  
