# 🧩 TaskManagement

 Sistema de Gerenciamento de Tarefas desenvolvido durante o **Hackathon AgiBank**, com foco em produtividade, colaboração e controle de atividades em equipe.

---

## 🛠️ Tecnologias Utilizadas

![Java](https://img.shields.io/badge/Java-21-orange?logo=java&style=for-the-badge)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?logo=springboot&style=for-the-badge)
![Spring Security](https://img.shields.io/badge/Spring%20Security-JWT-success?logo=springsecurity&style=for-the-badge)
![Gradle](https://img.shields.io/badge/Gradle-Build%20Tool-02303A?logo=gradle&style=for-the-badge)
![MySQL](https://img.shields.io/badge/MySQL-Database-blue?logo=mysql&style=for-the-badge)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-green?logo=swagger&style=for-the-badge)
![Postman](https://img.shields.io/badge/Postman-API%20Testing-orange?logo=postman&style=for-the-badge)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ%20IDEA-IDE-black?logo=intellijidea&style=for-the-badge)
![DBeaver](https://img.shields.io/badge/DBeaver-DB%20Manager-lightgrey?logo=dbeaver&style=for-the-badge)


---

## 📋 Sobre o Projeto

O **TaskManagement** é uma aplicação desenvolvida em **Java com Spring Boot**, com o objetivo de facilitar o gerenciamento de tarefas dentro de uma equipe.  
O sistema permite criar, atribuir, atualizar e acompanhar tarefas em tempo real, além de manter um histórico de alterações e status.

---

## 🧠 Funcionalidades Principais

- ✅ Cadastro e gerenciamento de tarefas  
- 👥 Atribuição de responsáveis  
- 🔄 Histórico de alterações por tarefa  
- ⏳ Controle de status e prioridade  
- 🧾 Autenticação e segurança via JWT  
- 💬 Integração com Swagger (documentação da API)  

---

:

## 🏗 Estrutura do Projeto

O projeto segue o padrão **Clean Architecture**, garantindo organização, separação de responsabilidades e fácil manutenção.

**Camadas e Pastas principais:**

| 📁 Pasta / Pacote | 📝 Descrição |
|------------------|-------------|
| ⚙️ `config`       | Configurações gerais (Swagger, Security, CORS, etc.) |
| 🚪 `controller`   | Endpoints REST (Tarefas, Usuários, etc.) |
| 📦 `DTO`          | Objetos de Transferência de Dados (entrada e saída) |
| 🔢 `enuns`        | Enumerações (Status, Prioridade, Ações) |
| ❗ `exception`    | Tratamento personalizado de erros |
| 🔄 `mappers`      | Conversão entre entidades JPA e DTOs |
| 🏷 `models`       | Entidades JPA representando tabelas do banco de dados |
| 💾 `repository`   | Interfaces de acesso ao banco de dados (Spring Data JPA) |
| 🛠 `services`     | Lógica de negócio central da aplicação |
| 🧰 `utils`        | Classes utilitárias e helpers |
| 📁 `resources`    | Arquivos de configuração (`application.yml`, etc.) |
| ✅ `test`         | Testes unitários e de integração |


---

## ⚙️ Tecnologias Utilizadas

- **Java 21+**
- **Spring Boot 3**
- **Spring Security + JWT**
- **Spring Data JPA (Hibernate)**
- **Lombok**
- **Swagger (OpenAPI 3)**
- **Gradle**
- **H2 / PostgreSQL (dependendo do ambiente)**

---

## 🚀 Como Executar o Projeto

### 1️⃣ Clonar o repositório
git clone https://github.com/seu-usuario/TaskManagement.git

### 2️⃣ Acessar o diretório
cd TaskManagement

### 3️⃣ Rodar o projeto com Gradle
./gradlew bootRun
# ou, no Windows:
gradlew.bat bootRun

### 4️⃣ Acessar a aplicação
A API estará disponível em:
http://localhost:8080

### 5️⃣ Documentação da API (Swagger)
http://localhost:8080/swagger-ui/index.html

---

## 🧪 Estrutura de Endpoints (exemplo)

| Método | Endpoint | Descrição |
|---------|-----------|-----------|
| POST | /api/tarefas | Cria uma nova tarefa |
| GET | /api/tarefas | Lista todas as tarefas |
| GET | /api/tarefas/{id} | Busca uma tarefa pelo ID |
| PUT | /api/tarefas/{id} | Atualiza uma tarefa existente |
| DELETE | /api/tarefas/{id} | Exclui uma tarefa |

---

## 👨‍💻 Time de Desenvolvimento

| Nome | Função |
|------|---------|
| **Lucas Vannucchi** | Backend Developer |
| **Luiz Henrique** | Backend Developer |
| **Mauricio Zazeri** | Backend Developer |
| **Luan Silva** | Backend Developer |
| **Gustavo Anjos** | Backend Developer |

---

## 💡 Melhorias Futuras

- Integração com frontend React/Angular  
- Notificações em tempo real (WebSocket)  
- Dashboard com métricas de produtividade  
- Sistema de comentários e anexos em tarefas  

---

## 📜 Licença

Este projeto foi desenvolvido exclusivamente para fins educacionais durante o Hackathon do AgiBank e não possui fins comerciais.

---
