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
 	let rec cube a epsilon currentCube = match currentCube with 
 		  i when abs_float (currentCube ** 3.0 -. a) <= epsilon *. (abs_float a) -> currentCube
 		| _ -> cube a epsilon (currentCube +. (a /. (currentCube ** 2.0) -. currentCube) /. 3.0) in
 	cube number 0.000000000000001 firstNumber ;;


 for i = -100 to 100 do let () = print_float (cubeRoot (float_of_int i)) in print_endline "" done ;;