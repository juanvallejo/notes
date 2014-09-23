father(john,lol).
foo(0,[]).
foo(X,[_|T]) :- foo(Y,T), X is Y+1.

lastof(0,[]).
lastof(X,[_|T]) :- lastof(Y,T), X is Y.

memberof(X,[X|_]).
memberof(X,[_|T]) :- memberof(X,T).

sizeof(0,[]).sizeof(X,[_|T]):- sizeof(Y,T),X is Y+1.