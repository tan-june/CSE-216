(*Last element of a list*)
let rec last lst = match lst with
|[] -> None
|[x] -> Some x
|(_::t) -> last t;;

(*Last two elements of a list*)
let rec lasttwo lst = match lst with
|[] -> None
|[_] -> None
|[x;y] -> Some(x,y)
|h::t -> lasttwo t;;

(*get nth element*)
let rec get_nth_element lst k = match lst with
|[] -> None
|h::t -> if (k = 0) then Some h else get_nth_element t (k-1);;

  (*is palidrome*)
  let is_palin lst =
   lst = List.rev lst;;

   let rec compress lst = match lst with
   | [] -> []
   | a :: (b :: _ as t) -> if a = b then compress t else a :: compress t
   |smaller -> smaller;;

   let rec duplicate lst = match lst with
    | [] -> []
    | h::t -> h :: h :: duplicate t;;

    let remove_nth lst n =
      let rec aux i = function
      | [] -> []
      | h::t -> if i = n then aux 1 lst else aux (i+1) t in
      aux 1 lst;;

      (*remove kth element - do again*)
    let rec remove_at i = function
    |[] -> []
    |h::t -> if i = 0 then t else h:: remove_at (i-1) t;;

    let rec insert_at a i = function
    |[] -> [a]
    |h::t -> if i =0 then h::a::t else h:: add_kth a (i-1) t;;

    let range a b = 
      let rec aux a b = 
        if a > b then [] else a :: aux (a+1) b
      in if a > b then List.rev(aux b a) else aux a b ;;

    let rec givelastthree lst = match lst with
      | [] -> []
      | [x;y;z] -> [x;y;z]
      | h::t -> givelastthree t;;

      let givefirstthree lst =
        let rec aux = function 
          | [] -> []
          | [x;y;z] -> [x;y;z]
          | h::t -> aux t
        in List.rev (aux (List.rev lst));;

      let exampleTree = 
        
