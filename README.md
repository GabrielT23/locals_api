# Locals API

Uma API Spring Boot para cadastrar e gerenciar locais. A aplicação também possui cadastro e autenticação de 
usuário, além de permitir o envio de arquivos e contar com a presença de testes unitários.

## Funcionalidades

- **Gestão de Usuários**: Crie, autentique e gerencie usuários com autenticação JWT.
- **Gestão de Locais**: Publique, atualize e recupere informações sobre locais.
- **Upload de Imagens**: Faça upload de imagens relacionadas aos locais e armazene-as no servidor.
- **Implementação de testes**: Implementação de testes unitários para garantir o funcionamento da aplicação.
## Tecnologias Utilizadas

- **Spring Boot**
- **Spring Data JPA**
- **PostgreSQL**
- **Autenticação JWT**
- **Docker** (para ambientes em container)

## Começando

### Pré-requisitos

- Java 17
- Maven 3.8+
- PostgreSQL
- Docker (opcional, para containerização)

### Instalação

1. **Clone o repositório**:

    ```bash
    git clone https://github.com/GabrielT23/locals_api.git
    ```

   ```bash
    cd locals-api
    ```
2. **Configure o banco de dados**:
   
   Certifique-se de ter um banco de dados PostgreSQL em execução e crie um banco de dados chamado `locals`.
   Para facilitar a instalação do banco utilize o docker-compose da própria aplicação para configurar o banco de dados.
   ```bash
    docker-compose up -d
    ```
   obs: Certifique-se de ter o docker instalado na sua maquina de preferência na versão mais atual.
4. **Configure as variáveis de ambiente**:

   Crie um arquivo `.env` no diretório raiz e adicione a configuração do seu banco de dados:

    ```env
    DATABASE_URL=jdbc:postgresql://localhost:5432/locals
    DATABASE_USERNAME=postgres
    DATABASE_PASSWORD=docker
    ```

5. **Construa o projeto**:

    ```bash
    mvn clean install
    ```

6. **Execute a aplicação**:

    ```bash
    mvn spring-boot:run
    ```

### Endpoints da API

| Método | Endpoint                   | Descrição                                         | Autenticação   |
|--------|----------------------      |---------------------------------------------------|--------------- |
| GET    | `/locals`                  | Recupera todos os locais                          | Nenhuma        |
| POST   | `/locals`                  | Cria um novo local                                | JWT Requerido  |
| PUT    | `/locals/{id}`             | Atualiza um local existente                       | JWT Requerido  |
| DELETE | `/locals/{id}`             | Deleta um local específico por ID                 | JWT Requerido  |
| POST   | `/auth`                    | Autentica um usuário e retorna um JWT             | Nenhuma        |
| POST   | `/users`                   | Registra um novo usuário                          | Nenhuma        |
| GET    | `/users/{id}`              | Recupera um usuário pelo ID                       | JWT Requerido  |
| PUT    | `/users/{id}`              | Atualiza um usuário pelo ID                       | JWT Requerido  |
| DELETE | `/users/{id}`              | Deleta um usuário pelo ID                         | JWT Requerido  |
| GET    | `swagger-ui/index.html#/`  | Documentação da API Swagger                       | JWT Requerido  |

### Executando Testes

Para rodar os testes unitários:

```bash
mvn test
```

### Documentação da Aplicação
Para acessar a documentação da Api com todas as rotas, explicação e corpo de requisição, inicie a aplicação
e acesse o endpoint:

/swagger-ui/index.html#/
