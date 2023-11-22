(*Question 1*)
let rec pow x n = match n with
| 0 -> 1
| 1 -> x
| _ -> x * pow x (n-1);;

let rec float_pow x n = match n with
| 0 -> 1.0
| 1 -> x
| _ -> x *. float_pow x (n-1);;

(*Question 2*)
let rec compress = function
|a::(b:: _ as others) ->
    if a=b then compress others
    else a::compress others
|smaller -> smaller;;

(*Question 3*)
let rec remove_if lst predicate = match lst with
  | [] -> []
  | h::t -> if predicate h 
    then remove_if t predicate 
    else h::(remove_if t predicate);;

(*Question 4*)
let rec slice lst i j = match lst, i, j with
  | [], _, _ -> []
  | _, i, j when i >= j -> []
  | h::t, 0, j -> 
      if j > 0 then h::slice t 0 (j-1)
      else []
  | h::t, i, j -> 
      slice t (i-1) (j-1);;

(*Question 5*)
let rec equivs func lst = match lst with
|_::_
|[] -> [[]];;

(*Question 6*)
let is_prime value =
  if value < 2 then false
  else
    let rec divisor_check d =
      if value < d * d
        then true
      else if value mod d = 0 
        then false
      else divisor_check (d+1)
        in divisor_check 2;;

let goldbachpair n =
    let rec find_prime k =
      if is_prime k && is_prime (n-k) then (k, n-k)
      else find_prime (k+1)
    in if n <= 2 || n mod 2 <> 0 
      then (0,0)
      else find_prime 2;;

(*Question 7*)
  let rec identical_on f g lst =
    match lst with
    | [] -> true
    | h::t -> if (f h) = (g h) then identical_on f g t
    else false;;

(*Question 8*)
let rec pairwisefilter cmp lst =
  match lst with
  | [] -> []
  | h::[] -> [h]
  | h::mid::t -> 
      let x = pairwisefilter cmp t in (cmp h mid) :: x;;

(*Question 9*)
let rec polynomial a = fun n ->
  match a with
  |(x, power) :: t -> let constant = polynomial t in
  let z = (float_of_int n) ** (float_of_int power) |> int_of_float in
  (x * z) + (constant n)
  | [] -> 0 ;;

(*Question 10*)
let rec powerSetCreator func lst = match lst with
  | [] -> []
  | h :: t -> (func h) :: (powerSetCreator func t);;

let rec powerset = function
| [] -> [[]]
| h::t -> let returnlst = powerset t in 
  returnlst  @ powerSetCreator (fun s -> h :: s) returnlst;;
