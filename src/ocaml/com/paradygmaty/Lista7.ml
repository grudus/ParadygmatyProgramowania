(* 1) Napisz moduł realizujący operacje na binarnym drzewie poszukiwań. Moduł powinien udostępniać
na zewnątrz funkcje tylko w postaci sygnatury. Operacje do implementacji:
o create() - tworzy strukturę drzewa
o push( Int ) - dodaje element do drzewa poszukiwań
o remove( Int ) - usuwa element z drzewa poszukiwań
o find( Int ) - sprawdź czy istnieje dany element
o getPreOrder() - pobiera listę leniwą przechodzącą przez wierzchołki w sposób PreOrder
o getPostOrder() - jw. tylko PostOrder
o getInOrder() - jw. tylko InOrder
Przykład:
T.create()
T.push(5)
T.push(3)
T.push(1)
T.push(4)
T.push(7)
T.getPreOrder() -> [5;3;1;4;7]
T.getPostOrder() -> [1;4;3;7;5]
T.getInOrder() -> [1;3;4;5;7] *)


module Tree = struct

type bt = Leaf | Node of bt * int *  bt ;;
type llist = LEmpty | LList of int * (unit -> llist);;

let rec combine l1 l2 = match l1 with
	| LEmpty -> l2
	| LList (x, xf) -> LList(x, (fun () -> combine (xf()) l2)) ;;


let rec ltake = function 
  | (0,_) -> [] 
  | (_, LEmpty) -> [] 
  | (i, LList(x, xf)) -> x :: ltake(i-1, xf()) ;;


let tree = ref Leaf;;


let create() = tree := Leaf ;;

let push number = 
	let rec insert x = function
		| Leaf -> Node(Leaf, x, Leaf)
		| Node(left, k, right) -> 
			if x < k then Node(insert x left, k, right)
			else Node(left, k, insert x right)
	in tree := insert number (!tree) ;;


let find number =
	let rec doFind = function
		| Leaf -> None
		| Node(left, k, right) ->
			if number < k then doFind left
			else if number > k then doFind right
			else Some k
	in doFind (!tree) ;;

let getPreOrder () =
	let rec preOrder = function
		| Leaf -> LEmpty
		| Node(left, k, right) -> combine (LList(k, fun() -> preOrder left)) (preOrder right)
	in preOrder !tree ;; 

let getPostOrder() =
	let rec postOrder = function
		| Leaf -> LEmpty
		| Node (left, k, right) -> let combined = combine (postOrder left) (postOrder right)
			in LList(k, fun() -> combined)
	in postOrder !tree ;;

let getInOrder () =
	let rec inOrder = function
		| Leaf -> LEmpty
		| Node (left, k, right) -> let combined = combine (inOrder left) (LList(k, fun() -> LEmpty))
			in combine combined (inOrder right)
	in inOrder !tree ;;





let rec max = function
	| Leaf -> failwith "No max value"
	| Node (_, _, right) as node -> 
		match right with
			| Leaf -> node
			| _ -> max right ;;


 let remove number =
	let rec delete x = function
		| Leaf -> failwith "Cannot find value in tree"
		| Node (left, k, right) ->
			if k < x then Node (delete x left, k, right)
			else if k > x then Node (left, k, delete x right)
			else match (left, right) with
				| (Leaf, Leaf) -> Leaf
				| (left, Leaf) -> left
				| (Leaf, right) -> right
				| (_, _) -> let Node(_, value, _) = max left 
					in Node(delete value left, value, right) 
	in tree := delete number (!tree) ;; 				

end;;

open Tree ;;