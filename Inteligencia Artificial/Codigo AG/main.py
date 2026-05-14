import copy
import os
from ag import AG

os.system('cls' if os.name == 'nt' else 'clear')

tamanho_original = int(input("Tamanho da população: "))
estado_final = "123456789" 
taxa_selecao = int(input("Taxa de seleção (entre 20 a 40%): "))
taxa_reproducao = 100 - taxa_selecao
taxa_mutacao = int(input("Taxa de mutação (entre 5 a 10%): "))
qtd_generacoes = int(input("Quantidade máxima de gerações: "))

populacao = []
nova_populacao = []

# Geração 1
AG.gerar_populacao(populacao, tamanho_original, estado_final)
populacao.sort()
print("\nGeração 1")
AG.exibir(populacao)

if populacao[0].valor == estado_final:
    print(f"\nA rota perfeita {estado_final} foi encontrada na Geração 1!")
    exit()

# Laço das gerações
for i in range(1, qtd_generacoes):
    # Passa pela seleção por torneio e gera novos filhos na nova_populacao
    AG.selecionar_por_torneio(populacao, nova_populacao, taxa_selecao)
    AG.reproduzir(populacao, nova_populacao, taxa_reproducao, estado_final)
    
    # Aplica mutação controlada na nova geração caso a condição seja aceita
    if (i % max(1, int(len(populacao) / taxa_mutacao)) == 0):
        AG.mutar(nova_populacao, estado_final)
    
    # Transfere a nova_populacao para a populacao ativa
    populacao.clear()
    populacao = copy.deepcopy(nova_populacao)
    nova_populacao.clear()
    
    # CORREÇÃO VISUAL E LÓGICA DA PODA:
    # 1. Ordena a população (do melhor com menos penalidade para o pior com mais penalidade)
    populacao.sort()
    
    # 2. Exclui os piores resultados do final da lista para manter o tamanho original estável
    while len(populacao) > tamanho_original:
        populacao.pop()

    print(f"\n---------------------------------------------")
    print(f"Geração {(i + 1)}")
    print(f"---------------------------------------------")
    AG.exibir(populacao)
    
    # Critério de parada obrigatório ao achar a combinação correta
    if populacao[0].valor == estado_final:
        print(f"\nSucesso! A rota perfeita '{estado_final}' com penalidade 0 foi encontrada na Geração {i + 1}.")
        break