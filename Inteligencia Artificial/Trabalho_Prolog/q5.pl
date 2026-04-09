%Atv 8 - Zoologia Básica
tem_penas("Galinha").
tem_penas("Gaivota").

poe_ovos("Galinha").
poe_ovos("Ornitorrinco").

ave(X) :-
    tem_penas(X),
    poe_ovos(X).

%teste
% ave("Galinha"). volta true
% ave("Ornitorrinco").volta false

%Atv 9 - Ecommerce

cliente("Igor",50).
cliente("Gustavo", 10).

produto("Videogame",20).
produto("TV", 60).

pode_comprar(Nome, Item) :-
    cliente(Nome, Saldo),
    produto(Item, Preco),
	Saldo < Preco -> false; true. 
%Teste
% pode_comprar("Igor","Videogame"). devolve true
% pode_comprar("Gustavo","Videogame"). devolve false

%Atv 10 - Torneio de jogos

venceu("Gustavo","Igor").
venceu("Igor", "Giuliano").

invicto(Jogador) :-
    not(venceu(_,Jogador)).

% Teste
% invicto("Giuliano"). devolve false
% invicto("Gustavo"). devolve true
