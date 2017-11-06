(* 1. Podaj (i wyjaśnij!) typy poniższych funkcji (samodzielnie, bez pomocy kompilatora OCamla!) :
 a) let f1 x y z = x y z;;
  *)

let f1 = function x -> function y -> function z -> x y z;;

(* x: ('a -> 'b -> 'c) ;; y : 'a ;; z: 'c ;;  f: ('a -> 'b -> 'c) -> 'a -> 'b ->'c *)


(*  b) let f2 x y = function z -> x::y; *)

(* x: 'a ;; y: list 'a ;; f1: ('a -> list 'a) -> 'b -> list 'a *)

(* (OCaml) Napisz dowolną funkcję f: 'a -> 'b. *)
