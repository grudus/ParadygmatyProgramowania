(*Na wykładzie zostały zdefiniowane listy leniwe.
OCaml: type 'a llist = LNil | LCons of 'a * (unit -> 'a llist);;
1. (OCaml i Scala)
Zdefiniuj funkcję lrepeat : int -> 'a llist -> 'a llist, która dla danej dodatniej liczby całkowitej k i listy
leniwej (strumienia w Scali) [x 0 , x 1 , x 2 , x 3 , ... ] zwraca listę leniwą (strumień w Scali), w której
każdy element jest powtórzony k razy, np.
lrepeat 3 [x 0, x 1 , x 2 , x 3 , ... ] => [x 0 , x 0 , x 0 , x 1 , x 1 , x 1 , x 2 , x 2 , x 2 , x 3 , x 3 , x 3 , ... ]
Uwaga. Dla zwiększenia czytelności zastosowano tu notację dla zwykłych list. *)

type 'a llist = LNil | LCons of 'a * (unit -> 'a llist);;

let rec ltake = function 
  | (0,_) -> [] 
  | (_, LNil) -> [] 
  | (i, LCons(x, xf)) -> x :: ltake(i-1, xf()) ;;


let lrepeat (times, llist) = 
	let rec repeat (k, xs) = match (k, xs) with
		| (_, LNil) -> LNil
		| (0, LCons(_, xf)) -> repeat (k, xf())
		| (i, LCons(a, _)) -> LCons(a, function () -> repeat  (i-1, xs))
	in repeat  (times, llist) ;;

(* val lrepeat : int * 'a llist -> 'a llist = <fun> *)

(* Zdefiniuj (w inny sposób, niż na wykładzie) leniwą listę liczb Fibonacciego
a) (OCaml) lfib : int llist
b) (Scala) lfib : Stream[Int]. *)

let lfib = 
	let rec fib(a, b) =
		LCons(a, function() -> fib(b, a+b))
	in fib(0,1);;

(* val lfib : int llist = LCons (0, <fun>) *)


(* 3. (OCaml i Scala) Polimorficzne leniwe drzewa binarne można zdefiniować następująco:
OCaml:
type 'a lBT = LEmpty | LNode of 'a * (unit ->'a lBT) * (unit -> 'a lBT);;
Scala:
sealed trait lBT[+A]
case object LEmpty extends lBT[Nothing]
case class LNode[+A](elem:A, left:()=>lBT[A], right:()=>lBT[A]) extends lBT[A]
a) Napisz funkcję lTree , która dla zadanej liczby naturalnej n konstruuje nieskończone leniwe
drzewo binarne z korzeniem o wartości n i z dwoma poddrzewami lTree (2*n) oraz lTree( 2*n+1).
To drzewo jest przydatne do testowania funkcji z następnego podpunktu.
b) Napisz funkcję, tworzącą leniwą listę w OCamlu (strumień w Scali), zawierającą wszystkie
wartości węzłów leniwego drzewa binarnego.
Wskazówka: zastosuj obejście drzewa wszerz, reprezentując kolejkę jako zwykłą listę. *)

type 'a lBT = LEmpty | LNode of 'a * (unit ->'a lBT) * (unit -> 'a lBT);;

let rec ltree n =
	LNode(n, (fun () -> ltree(2*n)), (fun () -> ltree(2*n + 1))) ;;

(* val ltree : int -> int lBT = <fun> *)


let rec bfs tree =
	let rec helper queue = match queue with
		| [] -> LNil
		| LEmpty::t -> helper t
		| LNode(v, l, r) :: t -> LCons(v, fun() -> helper (t @ [l();r()]))
	in helper [tree] ;;

(* val bfs : 'a lBT -> 'a llist