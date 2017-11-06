(* 1. Podaj (i wyjaśnij!) typy poniższych funkcji (samodzielnie, bez pomocy kompilatora OCamla!) :
a) let f1 x y z = x y z;;
b) let f2 x y = function z -> x::y;; *)

let f1 x y z = x y z ;;
let f1 = function x -> function y -> function z -> x y z
(*
(* y: 'a ;; z: 'b ;; x: ('a -> 'b -> 'c) *)
(* f1: ('a -> 'b -> 'c) -> 'a -> 'b -> 'c *)
  *)
let f2 x y = function z -> x::y ;;
let f2 = function x -> function y -> function z -> x::y ;;
(* 
(* x: 'a ;; y: 'a list ;; z: 'b *)
(* f2: ('a -> 'a list) -> 'b -> 'a list *)
 *)


(* (OCaml) Napisz dowolną funkcję f: 'a -> 'b. *)

let dupa a = failwith "Always fail"


(* Dla drzew binarnych, zdefiniowanych na wykładzie, napisz funkcję breadthBT : 'a bt -> 'a list
obchodzącą drzewo wszerz i zwracającą zawartość wszystkich węzłów drzewa w postaci listy.
Np. dla poniższego drzewa tt
 *)

 type 'a tree = Empty | Node of 'a * 'a tree * 'a tree;;

 let tt = 
 Node(1,
		Node(2,
			Node(4,
			Empty,
			Empty
		),
		Empty
	),
	Node(3,
		Node(5,
			Empty,
			Node(6,	
				Empty,
				Empty
			)
		),
	Empty
	)
);;


let breadthBT t =
	let rec breadth queue = match queue with 
		| [] -> []
		| Empty :: t -> breadth t
		| Node(v, l, r) :: t -> v :: breadth (t @ [l;r])
	in breadth [t];;


breadthBT tt;;

(* W regularnym drzewie binarnym każdy z węzłów jest bądź liściem, bądź ma stopień dwa (patrz
Cormen i in. §5.5.3). Zauważ, że drzewa ’a bt są drzewami regularnymi – traktujemy konstruktor
Empty jako liść.
Długość ścieżki wewnętrznej i regularnego drzewa binarnego jest sumą, po wszystkich węzłach
wewnętrznych drzewa, głębokości każdego węzła. Długość ścieżki zewnętrznej e jest sumą, po
wszystkich liściach drzewa, głębokości każdego liścia. Głębokość węzła definiujemy jako liczbę
krawędzi od korzenia do tego węzła.
Napisz dwie funkcje, obliczające odpowiednio
a) długość ścieżki wewnętrznej
b) długość ścieżki zewnętrznej
zadanego regularnego drzewa binarnego. *)


let geti tree = 
	let rec aux = function
	| (Node(_,l,r),depth) -> depth + aux (l,depth+1) + aux (r,depth+1)
	| (Empty,_) -> 0
	in aux (tree,0);;

let gete tree = 
	let rec aux = function
		| (Node(_,l,r),depth) -> 0 + aux (l,depth+1) + aux (r,depth+1)
		| (Empty,depth) -> depth
	in aux (tree,0);;


(* Dla grafów, zdefiniowanych na wykładzie, napisz funkcję depthSearch : 'a graph -> 'a -> 'a list,
obchodzącą graf w głąb zaczynając od zadanego wierzchołka i zwracającą zawartość
zwizytowanych węzłów w postaci listy. Np. dla grafu g z wykładu:
depthSearch g 4 => [4; 0; 3; 2; 1] *)

type 'a graphs = Graph of ('a -> 'a list);;

let g = Graph 
	(function 
		0 -> [3]
		| 1 -> [0;2;4]
		| 2 -> [1]
		| 3 -> []
		| 4 -> [0;2]
		| n -> failwith ("Graph g: node " ^ string_of_int n ^ " cannot be found")
	) ;;


let breadthSearch (Graph succ) startNode =
	let rec search visited = function
		[] -> []
		| h::t -> 
				if List.mem h visited then search visited t
				else h::search (h::visited) (t @ succ h)
	in search [] [startNode];;


let depthSearch (Graph succ) startNode =
	let rec search visited = function
		[] -> []
		| h::t -> 
				if List.mem h visited then search visited t
				else h::search (h::visited) ((succ h) @ t) 
	in search [] [startNode];;