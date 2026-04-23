# Prova1PW

Projeto Spring Boot para cadastro e consulta de **pacientes**, **médicos** e **consultas**.

## Tecnologias usadas
- Java 17
- Spring Boot 4.0.5
- Spring Web MVC
- Spring Data JPA
- MySQL

## Pré-requisitos
Antes de rodar o projeto, verifique se você tem:
- Java 17 instalado
- MySQL rodando localmente
- Maven Wrapper disponível no projeto (`mvnw.cmd` no Windows)

## Configuração do banco
A configuração do banco fica em `src/main/resources/application.properties`.

O ponto mais importante é a URL do MySQL:

```properties
spring.datasource.url=jdbc:mysql://localhost:3300/Prova1PW?createDatabaseIfNotExist=true
```
### Credenciais atuais
Essas são as credenciais configuradas no projeto:

```properties
spring.datasource.username=root
spring.datasource.password=1234
```

### Observação
Se o seu MySQL estiver em outra porta, você precisa alterar a URL no `application.properties`.

## Endpoints principais

### Médico
- `POST /medico` → cadastra médico
- `GET /medico` → lista médicos
- `GET /medico/{id}` → busca médico por id
- `PUT /medico/{id}` → atualiza médico
- `DELETE /medico/{id}` → remove médico

### Paciente
- `POST /paciente` → cadastra paciente
- `GET /paciente` → lista pacientes

### Consulta
- `POST /consulta` → cadastra consulta
- `GET /consulta` → lista consultas

## Estrutura do projeto
- `controller/` → recebe as requisições HTTP
- `service/` → contém regras de negócio e validações
- `mapper/` → converte entre entidades e DTOs
- `repository/` → faz acesso ao banco
- `model/` → entidades JPA e enums
- `dto/` → objetos de entrada e saída da API
## Classe principal
A aplicação sobe por meio da classe:
```java
inf.frohlich.prova1pw.Prova1PwApplication
```

Ela é a classe principal anotada com `@SpringBootApplication`.

