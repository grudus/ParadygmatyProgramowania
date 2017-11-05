(* Zadanie 1 *)
let contains list elem = List.fold_left (fun acc e -> acc || e = elem) false list;;

let filter listOfLists elem = List.filter (fun list -> contains list elem) listOfLists ;;


(* Zadanie 3 *)
(* 
let rec zigi = function
    [] -> failwith "Cannot find any item"    
  |	h::t -> let listWithoutItem = removeFirst t h in
      if listWithoutItem = t then h else zigi listWithoutItem ;;


let rec removeFirst list item = match list with 
	  [] -> []
	| h::t when h = item -> t
	| h::t -> h :: removeFirst t item ;;

 *)
let rec zigi list = 
	let binaries = List.map (fun x -> binary x) list in


let rec binary item = match item with 
	[] -> []
	h::t 
