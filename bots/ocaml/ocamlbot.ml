open Random;;

let rec read5 acc num =
  match num with
    0 -> acc
  | _ -> read5 (acc ^ read_line ()) (num - 1);;
  
try
 while true do
   let line = read_line () in
    match line with
      "ready?"  -> Printf.printf "ready!\n"
    | "generate code" -> let shuffle = Random.int 24 in
                           for i = 0 to 24 do
                             Printf.printf "%s" (if i = shuffle then "#" else ".");
                             if ((i + 1) mod 5) = 0 then Printf.printf "\n"                                                   
                           done;
    | "find code" -> let code = (read5 "" 5) in
                     let sharp = String.index code '#' in
                     let y = sharp / 5 in
                     let x = sharp - (y * 5) in
                       Printf.printf "%d %d\n" x y;
    | "bye!" | _ -> Printf.printf "bye!\n"; exit 0
 done;
 None
with
 End_of_file -> None
;;