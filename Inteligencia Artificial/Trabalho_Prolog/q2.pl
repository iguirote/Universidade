%Atv 3 - Conectvidade de cidades
estrada("Jaguari", "São Vicente").
estrada("São Vicente", "Jaguari").
estrada("São Vicente", "São Pedro").
estrada("São Pedro", "Santa Maria").

pode_viajar(De, Para) :-
    estrada(De, Para).

%Teste
%pode_viajar("São Pedro","Santa Maria"). devolve true
%pode_viajar("São Pedro","Jaguari"). devolve true

%bonus: O seguinte fato: estrada("São Vicente", "São Pedro"). demonstra que existe uma
%estrada entre São vicente e São Pedro. Se formos usar a lógica humana, chegamos a 
%conclusão que se existe uma estrada, logo temos dois caminhos A->B e B->A (ida e volta).
%Porém na IA, precisamos criar uma regra de simetria para demonstrar essa lógica.
%Poderíamos por exemplo montar a seguinte regra:
%Caminho(X, Y) :- estrada(X, Y).
%Caminho(X, Y) :- estrada(Y, X).

%Atv4 - Localização de objetos 

esta_em("Tv","Estante").
esta_em("Livro","Armário").
esta_em("Faca","Gaveta").

comodo_em("Estante","Quarto").
comodo_em("Armário","Sala de estar").
comodo_em("Gaveta","Cozinha").

localizacao_geral(Objeto, Casa) :-
    esta_em(Objeto, Comodo),
    comodo_em(Comodo, Casa).

%Teste
%localizacao_geral("Tv", "Quarto"). devolve true
%localizacao_geral("Tv", "Cozinha"). devolve false
