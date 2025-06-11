# Arquitetura de Backend do MovIT

A arquitetura de backend do MovIT adota o padrão MVC (Model-View-Controller) em conjunto com uma API REST no modelo cliente-servidor. Segue abaixo as responsabilidades de cada camada e suas principais classes.

---

## Padrão MVC e API REST

- **Model (Modelagem de Domínio e Persistência):**
  - Engloba as classes de entidade:
    - `Bicicleta`
    - `Usuario`
    Responsáveis por representar as tabelas e atributos do banco de dados.
  - Contém repositórios:
    - `BicicletaRepository`
    - `UsuarioRepository`
    Que implementam operações de acesso a dados (CRUD) por meio de uma abstração sobre a JPA implementando ORM Hibernate
  - Inclui os DTOs (Data Transfer Objects):
    - `DadosCadastroBicicleta`
    - `DadosCadastroUsuario`
    Que agrupam e validam informações de entrada e saída entre cliente e servidor, sem expor diretamente a estrutura interna das entidades, pois não mostram dados da entidades

- **View (Camada de Apresentação):**
  - Localizada no front-end, é responsável por renderizar as interfaces de usuário e exibir os dados vindos do servidor.
  - Toda a lógica visual (HTML, CSS e JavaScript) reside nessa camada, que consome a API REST provida pelos controllers.

- **Controller (Camada de Controle e Serviço de API REST):**
  - Contém classes como:
    - `BicicletaController`
    - `UsuarioController`
    Que definem endpoints REST e métodos HTTP (`GET`, `POST`, `PUT`, `DELETE` etc.).
  - Cada método de controller:
    1. Recebe requisições do cliente.  
    2. Valida e transforma dados por meio dos DTOs correspondentes.  
    3. Invoca serviços de negócio para processar a lógica requerida.  
    4. Retorna respostas HTTP adequadas (códigos de status, cabeçalhos e payload em JSON) para o front-end.

---

## Entidades, Repositórios e DTOs

### 1. Entidades (`Bicicleta`, `Usuario`)
- Definem atributos persistidos no banco, por exemplo:
  - `Bicicleta`: código, modelo, estado e etc..
  - `Usuario`: nome, e-mail e etc..

### 2. Repositórios (`BicicletaRepository`, `UsuarioRepository`)
- Geralmente estendem interfaces do framework do Spring Data).
- Fornecem métodos prontos (e customizados, quando necessário) para operações de leitura e escrita, tais como:
  - `findById()`

### 3. DTOs (`DadosCadastroBicicleta`, `DadosCadastroUsuario`)
- Classes simples que contêm apenas os campos relevantes para:
  - Requisições de cadastro.
  - Requisições de atualização.
  - Requisições de visualização.
- Aplicam validações via anotações:
  - `@NotNull`
  - `@Size`
  - `@Email`
  Assegurando a integridade dos dados antes de converter em entidade.
- Evitam acoplamento direto do front-end ao modelo da entidade, provendo uma camada intermediária de transformação.

---

## Serviços de Negócio (Services)

As classes de serviço centralizam a lógica de domínio e as regras funcionais exigidas pelo sistema:

- **`CorridaService`**
  - Gerencia o ciclo de vida de uma corrida de bicicleta:
    1. Iniciação.
    2. Cálculo de distância percorrida e duração.
    3. Encerramento.
    4. Persistência dos dados.
  - Valida, por exemplo, se o usuário tem permissão para iniciar ou encerrar uma corrida e se os dados recebidos estão coerentes.

- **`PontuacaoService`**
  - Calcula e atualiza pontuações ou recompensas atribuídas ao usuário com base em eventos do sistema:
    - Conclusão de corrida.
    - Atingir metas de distância.
  - Implementa regras de pontuação (percentuais, níveis, bônus) e persiste o histórico no banco de dados.

- **`SegurancaService`**
  - Responsável por gerenciar aspectos de segurança específicos ao usuário em relação às suas bicicletas:
    - Controle de permissões para desbloqueio e bloqueio.
    - Registro de acesso às bicicletas.
  - Executa validações de autorização:
    - Verifica se o usuário está autorizado a manipular ou visualizar determinada bicicleta.
    - Registra tentativas de acesso inválidas.
    - Pode disparar alertas em caso de tentativas não autorizadas.

- **`SocialService`**
  - Lida com funcionalidades de integração social:
    - Seguir outros usuários.
    - Compartilhar trajetos ou resultados.
    - Convidar amigos.
    - Enviar notificações de atividade.
  - Implementa regras de relacionamento entre usuários e orquestra eventos assíncronos ou enfileirados (por exemplo, disparo de e-mail ou push notification).
