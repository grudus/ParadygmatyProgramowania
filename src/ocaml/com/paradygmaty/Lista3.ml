(*  Podaj (i wyjaśnij!) typy poniższych funkcji (samodzielnie, bez pomocy kompilatora OCamla!) :
 a) let f1 x = x 2 2;; b) let f2 x y z = x ( y ^ z );;

 a) x: (int -> int -> 'a), -> 'a
b) f2: (string -> 'a) -> string -> string -> 'a
*)


let curry3 f a b c = f(a,b,c) ;;
let curry3 = function f -> function x -> function y -> function z -> f(x,y,z) ;;
(*(* val curry3 : ('a * 'b * 'c -> 'd) -> 'a -> 'b -> 'c -> 'd = <fun> *)*)

(* 
	let add (a,b,c) = a + b + c
	add 1 2 3 => error
	add (1,2,3) => spoko
	(curry3 add) 1 2 3 => spoko
 *)
let uncurry3 f (a,b,c) = f a b c ;;
let uncurry3 = function f -> function (a,b,c) -> f a b c ;; 
(*( val uncurry3 : ('a -> 'b -> 'c -> 'd) -> 'a * 'b * 'c -> 'd = <fun> *)

(* 
	let add = function a -> function b -> function c -> a + b + c
	add 1 2 3 => spoko
	add (1,2,3) => error
	(uncurry3 add) (1,2,3) => spoko
 *)



(*  Przekształć poniższą rekurencyjną definicję funkcji sumProd, która oblicza jednocześnie
 sumę i iloczyn listy liczb całkowitych na równoważną definicję nierekurencyjną z
 jednokrotnym użyciem funkcji bibliotecznej fold_left (Scala – foldLeft ), której argumentem jest
 odpowiednia funkcja anonimowa (literał funkcyjny).
 OCaml Scala
let rec sumProd xs = def sumProd(xs:List[Int]):(Int,Int) =
 match xs with xs match {
 h::t -> let (s,p)= sumProd t case h::t => {val (s,p)=sumProd(t)
 in (h+s,h*p) (h+s,h*p)
 | [] -> (0,1);; }
 case Nil => (0,1)
 }  *)


 let sumProd list = List.fold_left (fun (sum, prod) x -> (sum + x, prod * x)) (0, 1) list ;;



 let rec insertionSort isGreater list =
	let rec insert element list =
		match list with
		| [] -> [element]
		| (h :: t) ->
				if isGreater element h then h :: (insert element t)
				else element :: h :: t
	in
	match list with
	| [] -> []
	| h :: t -> insert h (insertionSort isGreater t)

insertionSort (fun (a, _) (b, _) -> a > b) [(4, 2); (3, 1); (3, 2); (5, 6)]

(* przez łączenie (scalanie) z zachowaniem stabilności i złożoności O(n lg n)
mergesort : (‘a->‘a->bool) -> ‘a list -> ‘a list *)

let rec merge f = function
	| ((h1::t1 as l1), (h2::t2 as l2)) -> if f h1 h2 then h1::(merge f (t1,l2)) else h2::(merge f (l1,t2))
	| ([], l) -> l
	| (l, []) -> l;;


let split list n =
    let rec aux i acc = function
      | [] -> List.rev acc, []
      | h :: t as l -> if i = 0 then List.rev acc, l
                       else aux (i-1) (h :: acc) t  in
    aux n [] list;;

let mergesort f list =
	let rec aux len = function 
  	| []	-> []
  	| [elem] -> [elem]
  	| l -> let (l1,l2) = split l (len/2) in merge f (aux (len/2) l1, aux (len - (len/2)) l2)
	in aux (List.length list) list;;


mergesort (fun (a,_) (b,_) -> a <= b) [(4, 2); (3, 1); (3, 2); (5, 6)];;
