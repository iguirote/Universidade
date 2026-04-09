%Atv 5 - Cardápio
ingrediente("Pastel","Massa").
ingrediente("Pastel","Palmito").
ingrediente("Pastel","Pimenta").
ingrediente("Pastel","Cebola").
ingrediente("Pastel","Queijo").
ingrediente("Torrada","Pão").
ingrediente("Torrada", "Presunto").

vegano("Massa").
vegano("Palmito").
vegano("Pimenta").
vegano("Cebola").
vegano("Pão").

prato_vegano(Prato):-
    ingrediente(Prato, Ingrediente),
    vegano(Ingrediente).

%Teste
%prato_vegano("Pastel"). devolve 4 true e 1 false.
%prato_vegano("Torrada"). devolve 1 true e 1 false.

%Atv 6 - Streaming

filme("Kung fu panda","Animacao").
filme("Diário de um banana","Comedia").
filme("Caça Fantasmas","Comedia").
filme("O grito", "Terror").

usuario_gosta("Gustavo","Terror").
usuario_gosta("Igor","Comedia").
usuario_gosta("Gustavo","Animacao").

recomendar(Filme, Usuario):-
    filme(Filme, Genero),
    usuario_gosta(Usuario, Genero).

%Teste
%recomendar("Kung fu panda","Gustavo"). devolve true
%recomendar("Kung fu panda","Igor"). devolve false

