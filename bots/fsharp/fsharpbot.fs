open System

let main() = 
    let mutable action = String.Empty  

    // print function that addes a linebreak and flushes the output
    let print_to_host (msg:obj) =
        match msg with        
        | :? (string[]) as arr -> printf "%s\n" ( Array.reduce (fun acc s -> acc + s) (msg :?> string[]))
        | _ -> printf "%s\n" (msg :?> string)
        stdout.Flush()    
    
    let slurp_lines n =
        [for i in 1..n do yield Console.ReadLine().Trim()]

    while true do
        System.Threading.Thread.Sleep(10)
        action <- Console.ReadLine().Trim()
        
        match action with
        | "ready?" ->  print_to_host "ready!"
        | "generate code" -> do
            let rnd = new System.Random()
            let r = rnd.Next(24)
            Array.create 25 "."
            |> fun a -> Array.concat [| a.[0..r-1]; [| "#" |]; a.[r+1..]  |] // inserts '#' at a random position
            |> fun a -> [| for x in 0..5..24 do yield a.[x..x+4] |] // splits it in 5 arrays of 5 strings each
            |> Array.iter print_to_host
        | "find code" -> do
            let code = slurp_lines 5 |> List.reduce (fun acc s -> acc + s)
            let sharp = code.IndexOf('#')
            let y = sharp / 5
            let x = sharp - (y * 5)
            print_to_host (sprintf "%d %d" x y)
        | "bye!" | _ -> do
            print_to_host "bye!"
            System.Environment.Exit(0)

main()