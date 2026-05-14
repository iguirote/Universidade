class Cromossomo:

    def __init__(self, valor, estado_final):
        self.valor = valor
        self.penalidade = self.calcular_penalidade(estado_final)
    
    def calcular_penalidade(self, estado_final):
        pts_penalidade = 0
        
        # 1. Penalidade por duplicidade (Sempre será 0 devido ao controle de mutação/cruzamento)
        cidades_vistas = set()
        for cidade in self.valor:
            if cidade in cidades_vistas:
                pts_penalidade += 20
            cidades_vistas.add(cidade)
            
        # 2. Penalidade de ordem (+10 pontos se um número maior vier antes de um menor)
        for i in range(len(self.valor)):
            for j in range(i + 1, len(self.valor)):
                if int(self.valor[i]) > int(self.valor[j]):
                    pts_penalidade += 10
                    
        return pts_penalidade
    
    def __eq__(self, other):
        if isinstance(other, Cromossomo):
            return self.valor == other.valor
        return False
    
    def __gt__(self, other):
        # Para o sort() deixar os melhores (menor penalidade) no começo:
        return self.penalidade >= other.penalidade
    
    def __str__(self):
        return f"valor= {self.valor}, penalidade= {self.penalidade}"