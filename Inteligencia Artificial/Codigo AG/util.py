import random

class Util:
    letras = "123456789"
    tamanho = len(letras)
    
    @staticmethod
    def gerar_palavra(n):     
        lista_cidades = list(Util.letras)
        random.shuffle(lista_cidades)
        return "".join(lista_cidades)