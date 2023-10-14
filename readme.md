# Projeto E-commerce ![E-commerce](https://img.icons8.com/color/48/000000/shopping-cart-loaded.png)

## Visão Geral
Este projeto é um sistema de comércio eletrônico que combina Servlets (Java) no lado do servidor e React no lado do cliente para proporcionar uma experiência de compra online. As funcionalidades principais incluem:

- **Autenticação de Usuário**: Os usuários podem fazer login no sistema, recebendo um token JWT para acessar áreas restritas.

- **Registro de Conta**: Os usuários que não têm uma conta podem acessar a rota "register" e criar uma conta. O sistema armazena as informações e envia uma solicitação para registrar e fazer login. O sistema verifica se a conta já existe usando o Criteria Builder. Se não houver uma conta existente, uma nova é criada; caso contrário, retorna um erro.

- **Seleção de Produtos e Carrinho de Compras**: Os produtos são exibidos em várias categorias usando Criteria Builder. Os usuários podem adicionar produtos ao carrinho ![Carrinho](https://img.icons8.com/color/24/000000/shopping-cart.png). Quando um usuário adiciona um produto ao carrinho, o sistema envia uma solicitação para o backend com o token do usuário e o ID do produto. O sistema pega essas informações, adiciona o produto ao carrinho do usuário ![Produto Adicionado](https://img.icons8.com/color/24/000000/checked--v2.png) e armazena as informações do carrinho no frontend.

- **Sessão e Logout do Usuário**: Quando um usuário deseja sair, ele pode clicar no ícone de perfil ![Perfil](https://img.icons8.com/color/24/000000/user.png). O sistema limpa o token do usuário, impedindo o acesso a rotas privadas.

## Tecnologias Utilizadas
- **Backend**:
    - Java (Servlets)
    - Spring Boot
    - Hibernate
    - MySQL Connector
    - Gson
    - Jackson
    - JWT (JSON Web Token)
    - Lombok
    - Logback
    - JUnit e Spring Test
    - Servlet API
    - Hibernate Validator
    - Piranha Servlet WebServlet
    - Spring Security
    - Validation API

## Configuração do Ambiente
1. **Configuração do Tomcat**:
    - Crie um `TomcatApplication` no IntelliJ IDEA.
    - Configure o Apache Tomcat com base neste [vídeo tutorial](https://www.youtube.com/watch?v=2C8RGyC-dlY).
    -  pasta do Apache Tomcat (versão 9.0.9) está na pasta "lib" do projeto(usada no video mas em um diretorio diferente neste projeto).

## Funcionamento do Projeto

-**Rota base**: localhost:3000/market/login
- **Autenticação de Usuário**: O processo de autenticação envolve o envio de credenciais, obtenção e uso do token JWT.

- **Registro de Conta**: Os usuários podem criar uma conta na rota \register e o sistema verifica se já existe uma conta usando Criteria Builder.

- **Seleção de Produtos e Carrinho de Compras**: Os produtos são exibidos em várias categorias usando Criteria Builder. Os usuários podem adicionar produtos ao carrinho ![Carrinho](https://img.icons8.com/color/24/000000/shopping-cart.png).

- **Sessão e Logout do Usuário**: A sessão do usuário é mantida enquanto o token JWT é válido. O usuário pode sair do sistema a qualquer momento.

## Configuração do Banco de Dados
1. **Instalação do MySQL**:
    - Baixe e siga as instruções de instalação em [https://dev.mysql.com/downloads/mysql/](https://dev.mysql.com/downloads/mysql/).

2. **Criação do Banco de Dados**:
    - No cliente MySQL, crie um banco de dados com o comando SQL abaixo:
      ```sql
      CREATE DATABASE ecommerce;
      ```

3. **Execução do Script SQL**:
    - Copie o código SQL do arquivo "sql.txt" no projeto ![Script SQL](https://img.icons8.com/color/24/000000/sql.png) e cole-o no MySQL para criar as tabelas necessárias.

4. **Configuração no application.properties**:
    - No arquivo application.properties do projeto Spring Boot, configure as seguintes propriedades com suas credenciais MySQL, se necessário:
      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
      spring.datasource.username=seu_usuario
      spring.datasource.password=sua_senha
      ```

## Contato
- **Email**: [![Email](https://img.icons8.com/color/24/000000/email.png)](mailto:igorccampos9@gmail.com)(clique no ícone)
- **LinkedIn**: [![LinkedIn](https://img.icons8.com/color/24/000000/linkedin.png)](https://www.linkedin.com/in/igor-de-campos-46a755265/)(clique no ícone)

