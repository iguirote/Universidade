%Atv 1 -Sistema Escolar

aluno("Igor").
aluno("Gustavo").

disciplina("Algoritmos A").
disciplina("Algoritmos B").
disciplina("Estrutura de dados").
disciplina("projeto de software").

pre_requisito("Algoritmos A", "Algoritmos B").
pre_requisito("Estrutura de dados","Projetos de software").

ja_cursou("Igor","Algoritmos A").

pode_cursar(Aluno, Disciplina) :-
    ja_cursou(Aluno, PreRequisito),
    pre_requisito(PreRequisito, Disciplina). 

%teste
% pode_cursar("Igor","Algoritmos B").  deve devolver true
% pode_cursar("Igor","Projetos de software").  deve devolver false

%Atv 2 - Status

nota("Igor", "Algoritmos A", 8).

status(Aluno, Disciplina) :-
    nota(Aluno, Disciplina, Nota),
    Nota >=7,
    write("Aprovado").

status(Aluno, Disciplina) :-
    nota(Aluno, Disciplina, Nota),
    Nota <5,
    write("Reprovado").    

%teste
%status("Igor","Algoritmos A").  deve devolver true
