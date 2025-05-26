# Getting Started

## Observacao
Para rodar a aplicação localmente, pelo menos no meu caso que estou fazendo o teste no windows foi necessários ajudar o comando do simulador

### Comando para subir o simulador via docker:
docker run -d -p 3000:3000 cfontes0estapar/garage-sim:1.0.0

### Comando para subir o postgres via docker:
docker run -d \
--name estapar-postgres \
-e POSTGRES_DB=db_estapar \
-e POSTGRES_USER=postgres \
-e POSTGRES_PASSWORD=suasenha123 \
-p 5432:5432 \
-v pgdata:/var/lib/postgresql/data-estapar \
postgres:latest

