(*  Liczby Fibonacciego są zdefiniowane następująco:
 f(0) = 0
 f(1) = 1
 f(n+2) = f(n) + f(n+1)
 Napisz dwie funkcje, które dla danego n znajdują n-tą liczbę Fibonacciego:
a) opartą bezpośrednio na powyższej definicji,
b) wykorzystującą rekursję ogonową.
 Porównaj (bez mierzenia) ich szybkość wykonania, obliczając np. 42-gą liczbę
 Fibonacciego *)

let rec fibonacciDefinition = function
	0 -> 0 |
	1 -> 1 |
	i -> fibonacciDefinition(i-1) + fibonacciDefinition(i-2) ;;


 let fibonacci number =
 	let rec fib counter prev next = match counter with 
 		0 -> prev |
 		_ -> fib (counter - 1) next (prev + next) in
 	fib number 0 1 ;;


for i = 0 to 100 do let () = print_int (fibonacci i) in print_endline "" done ;;




(* 
. Dla zadanej liczby rzeczywistej a oraz dokładności e można znaleźć pierwiastek
 trzeciego stopnia z a wyliczając kolejne przybliżenia xi tego pierwiastka (metoda
 Newtona-Raphsona):
 x0 = a/3 dla a > 1
 x0 = a dla a ≤ 1
 xi+1 = xi + (a/xi
2
– xi )/3
 Dokładność jest osiągnięta, jeśli |xi^3 – a| <= e * |a| .
 Napisz efektywną (wykorzystującą rekursję ogonową) funkcję root3, która dla zadanej
 liczby a znajduje pierwiastek trzeciego stopnia z dokładnością 10^-15
.
 *)


 let cubeRoot number = 
 	let firstNumber = if number > 1.0 then number /. 3.0 else number in
 	let rec cube epsilon currentCube = match currentCube with 
 		  i when abs_float (currentCube ** 3.0 -. number) <= epsilon *. (abs_float number) -> currentCube
 		| _ -> cube epsilon (currentCube +. (number /. (currentCube ** 2.0) -. currentCube) /. 3.0) in
 	cube 1e-15 firstNumber ;;


 for i = -100 to 100 do let () = print_float (cubeRoot (float_of_int i)) in print_endline "" done ;;


 (* 4. Zwiąż zmienną x z wartością 0 konstruując wzorce, do których mają się dopasować
 następujące wyrażenia:
 a) [-2; -1; 0; 1; 2] b) [ (1,2); (0,1) ]
 Np. dla wyrażenia (true,"hello",0) wymaganym wzorcem jest (_ ,_ ,x).
  *)


 let [_;_;x;_;_] = [-2; -1; 0; 1; 2] ;;
 let [(_,_);(x2,_)] = [ (1,2); (0,1) ] ;;


 (* 5. Zdefiniuj funkcję initSegment : 'a list * 'a list -> bool sprawdzającą w czasie liniowym, czy
 pierwsza lista stanowi początkowy segment drugiej listy. Każda lista jest swoim
 początkowym segmentem, lista pusta jest początkowym segmentem każdej listy.
  *)

  let rec initSegment = function
  	  ([], _) -> true
  	| (h1::t1, h2::t2) when h1 = h2 -> initSegment (t1, t2)
  	| _ -> false ;;


 (*  a) Zdefiniuj funkcję replaceNth : 'a list * int* 'a -> 'a list, zastępującą n-ty element listy
 podaną wartością (pierwszy element ma numer 0), np.
replaceNth (['o'; 'l'; 'a'] ,1, 's') => ['o'; 's'; 'a']
 Nie wykorzystuj żadnej funkcji bibliotecznej!
 b) Jaka jest złożoność obliczeniowa tej funkcji? Zilustruj rysunkiem (patrz wykład str.37-
 40) reprezentację obu list.
  *)

  let rec replace_nth (list, index, elem) = match list with
  	  [] -> []
  	| h::t when index = 0 -> elem :: t
  	| h::t -> h :: (replaceNth (t, (index - 1), elem)) ;;