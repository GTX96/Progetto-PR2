(*Eccezioni*)
exception InvalidExpression;;
exception WrongMatchException;;
exception EmptyEnv;;

(*AMBIENTE*)

(*Ambiente vuoto*)
let emptyEnv = [];;

(*binding*)
let bind (env,nome,valore) = (nome,valore)::env;;

(*Ricerca valore di una variabile*)
let rec lookup (var,env) = match env with
	|(nome,valore)::env1 -> if nome = var then valore else lookup (var,env1)
	|_ -> raise EmptyEnv;;

(*SINTASSI ASTRATTA*)

(*Gli identificatori sono stringhe*)
type ide = string

(*L'ambiente e' una lista di coppie nomi valori*)
and env = (ide * eval)list

(*Valori esprimibili*)
and eval =	
	|Int of int
	|Bool of bool
	|Unbound
	|Funval of efun
	|RecFunval of efun_rec
	|TuplaVal of etupla
	|PipeVal of pipe
	
(*Tipo delle : funzioni,tupla,pipe*)
and pipe = exp list

and efun = ide * exp * env (*parametro,corpo,ambiente*)							

and efun_rec = ide * ide * exp * env (*nome_funzione,parametro,corpo,ambiente*)

(*tipo della tupla*)
and etupla = eval list (*valori all'interno della tupla*)

and exp = 
	|Eint of int
	|Ebool of bool
	|Den of ide (*variabile*)
	|Prod of exp * exp (*prodotto*)
	|Sum of exp * exp (*somma*)
	|Diff of exp * exp (*Differenza*)
	|Eq of exp * exp (*Uguaglianza*)
	|Minus of exp (*Segno*)
	|And of exp * exp 
	|Or of exp * exp
	|Not of exp
	|Ifthenelse of exp * exp * exp
	|LetIn of ide * exp * exp (*Dichiarazione di una variabile*)
	|LetRec of ide * ide * exp * exp
	|Function of ide * exp 
	|ApplyFun of exp * exp
	|Etup of tuple (*tuple*)
	|Pipe of tuple (*concatenazioni di funzioni*)
	|AppPipe of exp * exp (*Applicazione di una Pipe ad un argomento*)
	|ManyTimes of int * exp (*esecuzione iterata di funzioni*)
	|AppManyTimes of exp * exp	
	
and tuple =
	|Nil (*tupla vuota*)
	|Seq of exp * tuple;; (*tuple di espressioni*)

(*SUPPORTO RUNTIME*)
		
(*Controllo dei tipi*)
let typecheck (x,y) = match x with
	|"int" -> ( match y with
					|Int(u) -> true
					|_ -> false )
					
	|"bool" -> (match y with
					|Bool(u) -> true
					| _ -> false)
	|"tupla" -> ( match y with
					|TuplaVal(_) -> true
					|_ -> false )
	|"funzione" -> ( match y with
					|Funval(_,_,_) -> true
					|RecFunval(_,_,_,_) -> true
					|_ -> false )
	|"pipe" -> ( match y with
					|PipeVal(_) -> true
					|_ -> false )

	|_ -> failwith ("tipo non valido");;

(*Implementazione operazioni *)

let not x = if typecheck("bool",x)
				then (match x with
					|Bool(i) -> if i = true then Bool(false) else Bool(true)
					|_ -> raise WrongMatchException)
				else failwith("errore di tipo");;
			
(*Segno*)
let minus x = if typecheck ("int",x)
				then (match x with 
					|Int(y) -> Int(-y)
					|_ -> raise WrongMatchException)
				else failwith ("errore di tipo");;
(*Somma*)
let somma (x,y) = if typecheck ("int",x) &&	typecheck ("int",y)
					then (match (x,y) with 
					|(Int(u),Int(w)) -> Int(u+w)
					|_ -> raise WrongMatchException)
					else failwith ("errore di tipo");;
(*Uguaglianza*)
let equals (x,y) = if (typecheck ("int",x) && typecheck ("int",y) || typecheck ("bool",x) && typecheck ("bool",y))
					then (match (x,y) with 
					|(Int(u),Int(w)) -> Bool(u = w)
					|(Bool(u),Bool(w)) -> Bool(u = w)
					|_ -> raise WrongMatchException)
					else failwith ("errore di tipo");;
(*Prodotto*)
let prod (x,y) = if typecheck ("int",x) && typecheck ("int",y)
					then (match (x,y) with 
					|(Int(u),Int(w)) -> Int(u * w)
					|_ -> raise WrongMatchException)
					else failwith ("errore di tipo");;
(*Differenza*)
let diff (x,y) = if typecheck ("int",x) && typecheck ("int",y)
					then (match (x,y) with 
					|(Int(u),Int(w)) -> Int(u-w)
					| _ -> raise WrongMatchException)
					else failwith ("errore di tipo");;

(*And*)					
let f_and (x,y) = if typecheck ("bool",x) && typecheck("bool",y)
					then (match (x,y) with
					|(Bool(u),Bool(w)) -> Bool(u && w)
					|_ -> raise WrongMatchException)
					else failwith ("errore di tipo");;
(*Or*)
let f_or (x,y) = if typecheck ("bool",x) && typecheck("bool",y)
					then (match (x,y) with
					|(Bool(u),Bool(w)) -> Bool(u || w)
					|_ -> raise WrongMatchException)
					else failwith ("errore di tipo");;

(*Supporto per trasformare le tuple in exp list*)
let rec eval_tupla (x:tuple) = match x with
				|Nil -> []
				|Seq(exp,tupla) -> [exp] @ eval_tupla(tupla);;

(*Funzione che reversa il contenuto di una lista*)
let rec reverse lst = 
	let rec aux acc = function
		| [] -> acc
		| h::t -> aux (h::acc) t in
	aux [] lst;;

(*Semantica*)
let rec sem ((e:exp),(r:env)) = match e with
	|Eint(i) -> Int(i)
	|Ebool(i) ->Bool(i)
	|Den x -> lookup (x,r)
	|Prod(x,y) -> prod(sem(x,r),sem(y,r))
	|Sum(x,y) -> somma(sem(x,r),sem(y,r))
	|Diff(x,y) -> diff(sem(x,r),sem(y,r))
	|Eq(x,y) -> equals(sem(x,r),sem(y,r))
	|And(x,y) -> f_and(sem(x,r),sem(y,r))
	|Or(x,y) -> somma(sem(x,r),sem(y,r))
	|Minus x -> minus(sem(x,r))
	|Not x -> not(sem(x,r))
	|Ifthenelse(guard,then_body,else_body) -> let g = (sem (guard,r)) in
								if typecheck("bool",g) then
									if(g=Bool(true)) then (sem(then_body,r)) else (sem(else_body,r))
									else failwith("guardia non booleana")

	|LetIn(var,value,body) -> sem(body,bind(r,var,sem(value,r)))

	|LetRec(fun_name,par,body,f_in) ->let benv = bind(r,fun_name,(RecFunval(fun_name,par,body,r)))
											in sem(f_in,benv)

	|Function(param,body) -> Funval(param,body,r)

	|ApplyFun(funName,arg) ->( let closure = sem((funName,r)) in 
									match closure with
							  			|Funval(param,body,r1) ->( match arg with
																	|Etup(tupla) ->( let lst = eval_tupla tupla in
																						match lst with
																							|[] -> failwith("Nessun Argomento trovato")
																							|x::xs -> sem(body,bind(r1,param,sem(x,r))) )

																	|Pipe(tupla) -> failwith("Impossibile applicare ad una funzione una Pipe")
																	|Function(_,_) -> failwith("Impossibile applicare ad una funzione la definizione di una funzione")

																	|_ -> sem(body,bind(r1,param,sem(arg,r))) )

							  			|RecFunval(funName,par,body,in_f) -> ( match arg with
																				|Etup(tupla) ->( let lst = eval_tupla tupla in
																						match lst with
																							|[] -> failwith("Nessun Argomento trovato")
																							|x::xs -> let rEnv = bind(in_f,funName,closure) in
																	 									let aEnv = bind(rEnv,par,sem(x,r)) in
																											sem(body,aEnv) )	

																				|Pipe(tupla) -> failwith("Impossibile applicare ad una funzione ricorsiva una Pipe")
																				|Function(_,_) ->failwith("Impossibile applicare ad una funzione ricorsiva la definizione di una funzione")

																				|_ -> let rEnv = bind(in_f,funName,closure) in
																	 					let aEnv = bind(rEnv,par,sem(arg,r)) in
																							sem(body,aEnv)		 )

																				
													
							  			|_ -> failwith("Nessuna funzione da applicare") )
	
	|Etup(tupla) -> let rec to_eval_list (lst: exp list) = match lst with
							| [] -> []
							| x::xs -> (sem(x,r))::(to_eval_list xs)
								
						in 
							let eval_list = to_eval_list (eval_tupla tupla )
								in TuplaVal(eval_list)
									
	|Pipe(tupla) -> PipeVal(eval_tupla tupla)

	|AppPipe(pipeName,arg) -> ( match sem(pipeName,r) with
								|PipeVal(fun_list) -> ( let rec apply_pipe (list_of_fun:exp list) (arg_to_apply: exp) = match (list_of_fun,arg_to_apply) with
																	|([],_) -> sem(arg_to_apply,r)
																	|(x::xs,_) ->  if typecheck("funzione",sem(x,r)) 
																						then apply_pipe xs (ApplyFun(x,arg_to_apply))

																					else if (typecheck("pipe",sem(x,r)))
																						then apply_pipe xs (AppPipe(x,arg_to_apply))

																					else failwith("Uno o piu' argomenti della Pipe non sono funzioni")
															in
																apply_pipe (reverse fun_list) arg )
																	
								| _ -> failwith("Nessun Pipe da applicare")  )	

	|ManyTimes(n,funName) ->( let funz = sem(funName,r) in 
								if (typecheck("funzione",funz))
									then ( let rec manytimes (num) funzione acc = ( if num < 0 then failwith ("Impossibile applicare una funzione un numero negativo di volte")	
																						else if num = 0 then acc
																							else manytimes (num-1)  (funzione) (funzione::acc) )
										
																						in PipeVal(manytimes n funName [])	)

								else failwith("ManyTimes non valida") )

	|AppManyTimes(name,arg) -> sem(AppPipe(name,arg),r)	



(*TEST*)

(*TEST MANYTIMES*)

(*ApplyManytimes(manytimes(3,incremento),Int 1)*)
(*

let incremento = Function("x",Sum(Den "x",Eint 1));;

let manytimes = ManyTimes(3,incremento);;

sem(LetIn("manytimes",manytimes,AppManyTimes(Den "manytimes",Eint 1)),emptyEnv);;

(*Pipe con Manytimes,su argomento tupla*)

let decremento = Function("x",Diff(Den "x",Eint 1));;
let incremento = Function("x",Sum(Den "x",Eint 1));;

(*doppio decremento*)
let manytimes_decremento = ManyTimes(2,decremento);;
(*triplo incremento*)
let manytimes_incremento = ManyTimes(3,incremento);;

let pipe = Pipe(Seq(manytimes_incremento,Seq(manytimes_decremento,Nil)));;

(*tupla = [Int 1,true]*)
let tupla = Etup(Seq(Eint 1,Seq(Ebool true,Nil)));;

sem(LetIn("pipe",pipe,AppPipe(Den "pipe",tupla)),emptyEnv);;


(*TEST PIPE*)

let incremento = Function("x",Sum(Den "x",Eint 1));;
let decremento = Function("x",Diff(Den "x",Eint 1));;

let pipe = Pipe(Seq(incremento,Seq(decremento,Nil)));;

(*Pipe[incremento,decremento]*)
sem(LetIn("pipe",pipe,AppPipe(Den "pipe",Eint 1)),emptyEnv);;

(*Pipe con tupla*)
(*AppPipe(Pipe[incremento,decremento],Tupla[Int 1 ,Int 2])*)
let tupla = Etup(Seq(Eint 1,Seq(Eint 2,Nil)));;
sem(LetIn("pipe",pipe,AppPipe(Den "pipe",tupla)),emptyEnv);;

(*Pipe non valida*)
let pipe_errata = Pipe(Seq(Sum(Eint 1,Eint 2),Nil));;
sem(LetIn("pipe",pipe_errata,AppPipe(Den "pipe",tupla)),emptyEnv);;


(*TEST ETUP*)

(*1)Tupla = [Int 1,Int 10]*)
let tupla = Etup(Seq(LetIn("x",Eint 1 , Sum(Den "x",Eint 1)),Seq(Eint 10,Nil)));;
sem(tupla,emptyEnv);;

(*2)Tupla[Bool true,Int 2,Funval(x,x+1)]*)
let tupla2 = Etup(Seq(Ebool true,Seq(Eint 2,Seq(Function("x",Sum(Den "x",Eint 1)),Nil))));;
sem(tupla2,emptyEnv);;

(*TEST FUNZIONE,CON ARGOMENTI PARTICOLARI*)

let funzione = Function("x",Sum(Den "x",Eint 1));;

(*Valori argomenti funzione*)
let tupla = Etup(Seq(Eint 5,Seq(Eint 2,Nil)));;
let tupla_errata = Etup(Seq(Ebool false,Seq(Eint 2,Nil)));;
let pipe = Pipe(Seq(Eint 1,Nil));;

sem(LetIn("incr",funzione,ApplyFun(Den "incr",tupla)),emptyEnv);;
sem(LetIn("incr",funzione,ApplyFun(Den "incr",tupla_errata)),emptyEnv);;
sem(LetIn("incr",funzione,ApplyFun(Den "incr",pipe)),emptyEnv);;
sem(LetIn("incr",funzione,ApplyFun(Den "incr",funzione)),emptyEnv);;


(*TEST EVAL TUPLA*)
let tupla = Seq(Eint 1,Seq(Eint 2, Nil));;
eval_tupla tupla;;

(*TEST FUNZIONI*)
(*fattoriale ricorsiva*)
let fattoriale = LetRec( "fact", "n", Ifthenelse( Eq(Den "n",Eint 0) , Eint 1 , Prod(Den "n",ApplyFun(Den "fact",Diff(Den "n",Eint 1)))), ApplyFun(Den "fact",Eint 3));;
sem(fattoriale,emptyEnv);;

let funzione = LetIn("fun",Function("x",Sum(Den "x",Eint 1)),ApplyFun(Den "fun",Eint 1));;
sem(funzione,emptyEnv);;

(*TEST LETIN*)
(*let x = 1 in x+4*)
let let1 = LetIn("x",Eint 1,Sum(Den "x",Eint 4));;
sem (let1,emptyEnv);;

(*TEST IFTHENELSE*)
(*if true then 4+1 else 4-1*)
let ifz = Ifthenelse(Ebool true,Sum(Eint 4,Eint 1),Diff(Eint 4,Eint 1));;
sem(ifz,emptyEnv);;

(*TEST BASE*)
sem((Sum(Eint 4,Eint 1)),emptyEnv);;
*)
