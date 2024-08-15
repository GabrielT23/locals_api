# Locals API

Uma API Spring Boot para gerenciar e notificar usuários sobre locais afetados por desastres naturais.

## Funcionalidades

- **Gestão de Usuários**: Crie, autentique e gerencie usuários com autenticação JWT.
- **Gestão de Locais**: Publique, atualize e recupere informações sobre locais afetados por desastres naturais.
- **Upload de Imagens**: Faça upload de imagens relacionadas aos locais e armazene-as no servidor.
- **Notificações Baseadas em Localização**: Notifique usuários sobre desastres próximos com base em sua localização.
- **Evacuação Baseada em Altitude**: Encontre a cidade de maior altitude dentro de um raio de 50 km para evacuação segura durante inundações.

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
    git clone https://github.com/seuusuario/locals-api.git
    cd locals-api
    ```

2. **Configure o banco de dados**:
   
   Certifique-se de ter um banco de dados PostgreSQL em execução e crie um banco de dados chamado `locals`.

3. **Configure as variáveis de ambiente**:

   Crie um arquivo `.env` no diretório raiz e adicione a configuração do seu banco de dados:

    ```env
    DATABASE_URL=jdbc:postgresql://localhost:5432/locals
    DATABASE_USERNAME=postgres
    DATABASE_PASSWORD=docker
    ```

4. **Construa o projeto**:

    ```bash
    mvn clean install
    ```

5. **Execute a aplicação**:

    ```bash
    mvn spring-boot:run
    ```

### Endpoints da API

| Método | Endpoint             | Descrição                                         | Autenticação   |
|--------|----------------------|---------------------------------------------------|----------------|
| GET    | `/locals`             | Recupera todos os locais                         | Nenhuma        |
| POST   | `/locals`             | Cria um novo local                                | JWT Requerido  |
| PUT    | `/locals/{id}`        | Atualiza um local existente                       | JWT Requerido  |
| GET    | `/locals/{id}`        | Recupera um local específico por ID               | Nenhuma        |
| POST   | `/auth/login`         | Autentica um usuário e retorna um JWT             | Nenhuma        |
| POST   | `/auth/register`      | Registra um novo usuário                          | Nenhuma        |

### Executando Testes

Para rodar os testes unitários:

```bash
mvn test
