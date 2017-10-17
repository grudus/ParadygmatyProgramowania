
(* Napisać funkcję anonimową sprawdzającą czy dana liczba jest podzielna przez *)
(function n -> n mod 3 = 0)

(* Napisz funkcję rozdzielającą listy wejściowe na dwie podlisty. W pierwszej podliście mają się znaleźć
wszystkie elementy o wartościach parzystych. W drugiej podliście mają się znaleźć wszystkie
elementy o wartościach nieparzystych.

Porządek elementów musi być zachowany. Wynik zwrócić w postaci pary list. *)
let rec rozdziel = function
	  [] -> ([], [])
	| h::t when h mod 2 = 0 -> 
		let tuple = rozdziel t in (h :: (fst tuple), snd tuple)
	| h::t -> 
		let tuple = rozdziel t in (fst tuple, h :: (snd tuple)) ;;