import random
from cromossomo import Cromossomo
from util import Util

class AG:
    @staticmethod
    def gerar_populacao(populacao, tamanho_populacao, estado_final):
        for i in range(tamanho_populacao):
            populacao.append(Cromossomo(Util.gerar_palavra(len(estado_final)), estado_final))
    
    @staticmethod
    def exibir(populacao):        
        for i in populacao:
            print(i)

    @staticmethod
    def selecionar_por_torneio(populacao, nova_populacao, taxa_selecao):
        torneio = []
        qtd_selecionados = int(taxa_selecao * len(populacao) / 100)
        
        # Elitismo: Garante o melhor da geração atual na próxima
        nova_populacao.append(populacao[0]) 
        
        i = 1
        while (i < qtd_selecionados):
            c1 = populacao[random.randrange(len(populacao))]
            
            while (True):            
                c2 = populacao[random.randrange(len(populacao))]
                if c2 != c1:
                    break
            
            while (True):            
                c3 = populacao[random.randrange(len(populacao))]
                if c3 != c2 and c3 != c1:
                    break            

            torneio.append(c1)
            torneio.append(c2)
            torneio.append(c3)
            torneio.sort() 

            selecionado = torneio[0]

            if selecionado not in nova_populacao:
                nova_populacao.append(selecionado)
                i += 1
            
            torneio.clear()      
                
    @staticmethod
    def reproduzir(populacao, nova_populacao, taxa_reproducao, estado_final):
        qtd_reproduzidos = int(taxa_reproducao * len(populacao) / 100)

        i = 0
        while (i < qtd_reproduzidos):            
            pai = populacao[random.randrange(len(populacao))]
                
            while (True):            
                mae = populacao[random.randrange(len(populacao))]
                if mae != pai:
                    break               

            sPai = pai.valor
            sMae = mae.valor
            
            cortar = int(len(sPai) / 2)
            
            # Cruzamento mapeado (Sem gerar números duplicados nas rotas)
            filho1_lista = list(sPai[0:cortar])
            for gene in sMae:
                if gene not in filho1_lista:
                    filho1_lista.append(gene)
            sFilho1 = "".join(filho1_lista)

            filho2_lista = list(sMae[0:cortar])
            for gene in sPai:
                if gene not in filho2_lista:
                    filho2_lista.append(gene)
            sFilho2 = "".join(filho2_lista)

            nova_populacao.append(Cromossomo(sFilho1, estado_final))
            nova_populacao.append(Cromossomo(sFilho2, estado_final))
            i = i + 2

    @staticmethod
    def mutar(nova_populacao, estado_final):
        # Sorteia quantos cromossomos vão sofrer mutação (até 20% da população nova)
        qtd_mutantes = random.randrange(max(1, int(len(nova_populacao) / 5))) + 1
        
        while (qtd_mutantes > 0):
            posicao_mutante = random.randrange(len(nova_populacao))
            mutante = nova_populacao[posicao_mutante]
            
            valor_mutado = list(mutante.valor)
            if len(valor_mutado) > 1:
                # Swap mutation: Inverte a posição de duas cidades aleatórias
                # Isso altera o cromossomo sem gerar nenhuma duplicidade de número
                pos1 = random.randrange(len(valor_mutado))
                pos2 = random.randrange(len(valor_mutado))
                while pos1 == pos2:
                    pos2 = random.randrange(len(valor_mutado))
                
                valor_mutado[pos1], valor_mutado[pos2] = valor_mutado[pos2], valor_mutado[pos1]
            
            str_mutada = "".join(valor_mutado)
            nova_populacao[posicao_mutante] = Cromossomo(str_mutada, estado_final)
            qtd_mutantes -= 1