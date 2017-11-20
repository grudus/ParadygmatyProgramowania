(* Dla drzewa BST (Binary Search Tree):
a) Napisz funkcję contains typu int_tree -> int -> bool sprawdzającą, czy
drzewo zawiera daną liczbę.
b) Napisz funkcję sum_tree typu int_tree -> int zwracającą sumę elementów
w drzewie.
c) Napisz funkcję prod_tree typu int_tree -> int zwracającą iloczyn
elementów w drzewie. *)
type 'a tree = Empty | Node of 'a * 'a tree * 'a tree ;;

let tree1 = Node(55, Empty, Node(2, Empty, Empty)) ;;


let rec contains tree item = 
  match tree with 
  | Empty -> false
  | Node(v,l,r) when v = item -> true
  | Node(_,l,r) -> contains l item || contains r item;; 


let rec sum_tree tree = match tree with
	| Empty -> 0
	| Node(_, l, r) -> 1 + sum_tree l + sum_tree r ;;

let rec prod_tree tree = match tree with
	| Empty -> 1
	| Node(v, l, r) -> v * prod_tree l * prod_tree r ;;



 let filter xss x = List.filter (fun xs -> List.filter (fun h -> h = x) xs = []) xss ;;