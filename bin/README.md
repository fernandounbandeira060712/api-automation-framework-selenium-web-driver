# 🚀 API-REST-Assured

## 🧪 Testes Automatizados de API com RestAssured + Java

Este projeto contém uma suíte de testes automatizados de API desenvolvida em **Java**, utilizando a biblioteca **RestAssured** para validação dos endpoints da API pública:

🔗 https://reqres.in

---

## 🎯 Objetivo

Demonstrar boas práticas de automação de testes de API, incluindo:

- ✔ Validação de status codes
- ✔ Validação de JSON Schema
- ✔ Estrutura organizada de testes
- ✔ Uso de Maven para build e execução
- ✔ Preparação para integração com CI/CD (Jenkins)

---

## 🛠️ Tecnologias Utilizadas

- ☕ Java
- 🔗 RestAssured
- 📦 Maven
- 🧪 TestNG / JUnit
- 📄 JSON Schema Validator
- 🔄 Jenkins (CI/CD)

---

## 📌 Endpoints Testados

### 🔍 GET `/api/users/{id}`

Retorna os dados de um usuário específico.

**Validações realizadas:**
- Status code 200 (OK)
- Estrutura da resposta via JSON Schema
- Dados obrigatórios do usuário

---

### ➕ POST `/api/users`

Cria um novo usuário.

**Validações realizadas:**
- Status code 201 (Created)
- Estrutura da resposta
- Campos retornados após criação

---

### 🔄 PUT `/api/users/{id}`

Atualiza os dados de um usuário.

**Validações realizadas:**
- Status code 200 (OK)
- Validação de payload atualizado
- Estrutura da resposta

---

### ❌ DELETE `/api/users/{id}`

Remove um usuário.

**Validações realizadas:**
- Status code 204 (No Content)
- Garantia de exclusão sem retorno de corpo

---

## 🧪 Validação de Contrato (JSON Schema)

Os testes utilizam validação de JSON Schema para garantir:

- Integridade da estrutura da resposta
- Tipagem correta dos campos
- Conformidade com o contrato da API

---

## ⚙️ Pré-requisitos

Antes de executar o projeto, você precisa ter instalado:

- Java JDK 8+
- Maven
- Acesso à internet

---

## ▶️ Como Executar

Clone o repositório:
git clone https://github.com/seu-usuario/API-REST-Assured.git

Acesse a pasta do projeto:
cd API-REST-Assured

Execute os testes:
mvn clean test

---

## 🔄 Integração com CI/CD

Este projeto possui suporte para execução automatizada via Jenkins, utilizando um Jenkinsfile com as seguintes funcionalidades:

⏰ Execução agendada (cron)
🔁 Rerun automático de testes falhos
📊 Geração de relatório com Allure
📦 Empacotamento de evidências (logs, screenshots e resultados)
📁 Arquivamento de artefatos
📬 Notificação de sucesso/falha
🚀 Criação automática de Pull Request

---

## 📊 Relatórios

Os testes podem gerar relatórios utilizando:

📈 Allure Report (quando configurado no ambiente CI)

---

## 💡 Boas Práticas Aplicadas
Organização de testes por endpoint
Separação de responsabilidades
Validação de contrato (JSON Schema)
Parametrização de execução
Preparação para ambientes de CI/CD
