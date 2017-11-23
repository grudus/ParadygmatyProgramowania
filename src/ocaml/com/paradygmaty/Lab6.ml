(* WAŻNE1: Do każdego zadania przygotuj 3-5 testów sprawdzających poprawność działania!
WA zNE2: Wolno używać tylko funkcji bibliotecznych o złożoności obliczeniowej O(1).
1) Napisz funkcje wkładającą element do posortowanej kolekcji, w taki sposób, aby wyjściowa kolekcja
pozostawała posortowana. *)


let rec insert list value = match list with
	| [] -> []
	| h::t when value > h -> h :: (insert t value)
	| h::t -> value :: h :: t ;;


(*  Zdefiniuj funkcję "lpowiel" i "powiel" powielającą elementy w leniwej/gorliwej liście liczb, tyle razy ile
wynosi wartość aktualnej liczby.
 Przykład: [1;2;3] daje [1;2;2;3;3;3]
 Wyniki powinny być zapisane w postaci leniwej/gorliwej! *)

type 'a nlist = Koniec | Element of 'a * ('a nlist);;
type 'a llist = LKoniec | LElement of 'a * (unit -> 'a llist);;


let rec ltake = function 
  | (0,_) -> [] 
  | (_, LKoniec) -> [] 
  | (i, LElement(x, xf)) -> x :: ltake(i-1, xf()) ;;

let rec take = function 
  | (0,_) -> [] 
  | (_, Koniec) -> [] 
  | (i, Element(x, xx)) -> x :: take(i-1, xx) ;;


let lpowiel llist =
	let rec repeat(k, xs) = match (k, xs) with 
		| (_, LKoniec) -> LKoniec
		| (0, LElement(_, xf)) -> match xf() with
			| LKoniec -> LKoniec
			| LElement(x, _) as next -> repeat(x, next) in
		| (i, LElement(a, _)) -> LElement(a, fun () -> repeat(i-1, xs))
	in repeat llist ;;


let lpowiel (times, llist) = 
	let rec repeat (k, xs) = match (k, xs) with
		| (_, LKoniec) -> LKoniec
		| (0, LElement(_, xf)) -> repeat (times, xf())
		| (i, LElement(a, _)) -> LElement(a, function () -> repeat  (i-1, xs))
	in repeat  (times, llist) ;;

