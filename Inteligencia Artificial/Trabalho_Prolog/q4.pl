%Atv 7 - Domínio de Blocos
sobre("Bloco A","Bloco B").
sobre("Bloco C", "Bloco D").

no_chao("Bloco B").
no_chao("Bloco D").

abaixo(X, Y) :-
    sobre(Y, X).

bloco_livre(X) :-
    not(abaixo(X,_)).

%Teste
% abaixo("Bloco B","Bloco A"). devolve true
% abaixo("Bloco A","Bloco B"). devolve false
% bloco_livre("Bloco A"). devolve true
% bloco_livre("Bloco B"). devolve false
    