import requests

class CidadeClima:
    def __init__(self, nome, temperatura, umidade, condicao):
        self.nome = nome
        self.temperatura = temperatura
        self.umidade = umidade
        self.condicao = condicao

    def __str__(self):
        return (
            f"Cidade      : {self.nome}\n"
            f"Temperatura : {self.temperatura} °C\n"
            f"Umidade     : {self.umidade}%\n"
            f"Condição    : {self.condicao}\n"
        )


CODIGOS_CLIMA = {
    0: "Céu limpo", 1: "Majoritariamente limpo", 2: "Parcialmente nublado",
    3: "Nublado", 45: "Nevoeiro", 51: "Garoa leve", 53: "Garoa moderada",
    61: "Chuva leve", 63: "Chuva moderada", 65: "Chuva forte",
    71: "Neve leve", 80: "Pancadas leves", 95: "Tempestade",
}

cidades = ["São Paulo", "London", "Tokyo", "New York", "Paris", "Rio grande", "Jaguari"]
relatorio_clima = []

for cidade in cidades:
    geo = requests.get(
        "https://geocoding-api.open-meteo.com/v1/search",
        params={
            "name": cidade,
            "count": 1,
            "format": "json"
        }
    ).json()

    if not geo.get("results"):
        print(f"Cidade '{cidade}' não encontrada.")
        continue

    resultado = geo["results"][0]

    clima = requests.get(
        "https://api.open-meteo.com/v1/forecast",
        params={
            "latitude": resultado["latitude"],
            "longitude": resultado["longitude"],
            "current": "temperature_2m,relative_humidity_2m,weathercode"
        }
    ).json()["current"]

    objeto = CidadeClima(
        nome=resultado["name"],
        temperatura=clima["temperature_2m"],
        umidade=clima["relative_humidity_2m"],
        condicao=CODIGOS_CLIMA.get(clima["weathercode"], "Desconhecido")
    )

    relatorio_clima.append(objeto)


print("\nRELATÓRIO CLIMÁTICO\n")

for cidade_obj in relatorio_clima:
    print(cidade_obj)
    print("-" * 40)