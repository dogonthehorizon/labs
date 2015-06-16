open Core.Std

(*
 * Accept floats from stdin and sum their total.
 *
 * Note: currently fails when two consecutive enters are hit. Error looks
 * something like this:
 *      Uncaught exception:
 *          (Invalid_argument "Float.of_string ")
 *)
let rec read_and_accumulate accum =
    let line = In_channel.input_line In_channel.stdin in
    match line with
    | None -> accum
    | Some x -> read_and_accumulate (accum +. Float.of_string x)

let () =
    printf "Total: %F\n" (read_and_accumulate 0.)
