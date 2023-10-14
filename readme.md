<div align="center">
  <img src="https://exemplo.com/logo.png" alt="Logo do E-Commerce" width="200">
</div>

# Backend Java - E-Commerce

Bem-vindo ao backend do nosso sistema de E-Commerce! Abaixo, você encontrará uma lista dos principais endpoints que podem ser acessados para interagir com nossa plataforma.

## Índice

- [Autenticação](#autenticação)
- [Produtos](#produtos)
- [Carrinho de Compras](#carrinho-de-compras)
- [Registro de Usuário](#registro-de-usuário)

## Autenticação

### `/market/login` (Rota não autenticada) - POST

![Endpoint](https://img.icons8.com/color/48/000000/api-settings.png)

Este endpoint é usado para autenticar os usuários no sistema.

**Requisição:**

- Recebe o email e senha do usuário.

**Resposta:**

- Valida as credenciais do usuário e retorna um token de autenticação.

![Response](https://img.icons8.com/color/48/000000/api-settings.png)

### `/market/cart/add` (Rota autenticada) - POST

![Endpoint](https://img.icons8.com/color/48/000000/api-settings.png)

Use este endpoint para adicionar um produto ao carrinho de compras do usuário.

**Requisição:**

- Recebe o hash como referência para identificar o carrinho.
- Recebe o ID do produto a ser adicionado.

**Resposta:**

- Adiciona o produto ao carrinho do usuário.

![Response](https://img.icons8.com/color/48/000000/api-settings.png)

## Produtos

### `/market/home/product` (Rota autenticada) - GET

![Endpoint](https://img.icons8.com/color/48/000000/api-settings.png)

Este endpoint retorna a lista de produtos com base na categoria fornecida.

**Requisição:**

- Recebe a categoria do produto como parâmetro.

**Resposta:**

- Retorna a lista de produtos da categoria especificada.

![Response](https://img.icons8.com/color/48/000000/api-settings.png)

### `/market/register` (Rota não autenticada) - POST

![Endpoint](https://img.icons8.com/color/48/000000/api-settings.png)

Use este endpoint para registrar um novo usuário no sistema.

**Requisição:**

- Recebe nome, email e senha do usuário.

**Resposta:**

- Registra o novo usuário no sistema.

![Response](https://img.icons8.com/color/48/000000/api-settings.png)

## Carrinho de Compras

### `/market/cart/home/remove` (Rota autenticada) - DELETE

![Endpoint](https://img.icons8.com/color/48/000000/api-settings.png)

Este endpoint permite ao usuário remover um produto do carrinho.

**Requisição:**

- Recebe o ID do produto a ser removido.
- Requer autenticação do usuário por meio de token ou hash.

**Resposta:**

- Remove o produto do carrinho na base de dados.

![Response](https://img.icons8.com/color/48/000000/api-settings.png)

### `/market/home/cart` (Rota autenticada) - GET

![Endpoint](https://img.icons8.com/color/48/000000/api-settings.png)

Este endpoint retorna o carrinho de compras do usuário.

**Requisição:**

- Recebe o token do usuário (ou o hash correspondente).

**Resposta:**

- Retorna o carrinho de compras do usuário.

![Response](https://img.icons8.com/color/48/000000/api-settings.png)

## Autenticação JWT

Nossa autenticação é baseada em tokens JWT, eliminando a necessidade de armazenar tokens em nosso sistema. Basta autenticar com suas credenciais e usar o token JWT recebido para acessar os endpoints autenticados.

</div>
