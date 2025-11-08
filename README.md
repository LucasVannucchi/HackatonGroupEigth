# 🧩 TaskManagement

> Sistema de Gerenciamento de Tarefas desenvolvido durante o **Hackathon**, com foco em produtividade, colaboração e controle de atividades em equipe.

---

## 🛠️ Tecnologias Utilizadas

![Java](https://img.shields.io/badge/Java-17-orange?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?logo=springboot)
![Spring Security](https://img.shields.io/badge/Spring%20Security-JWT-success?logo=springsecurity)
![Gradle](https://img.shields.io/badge/Gradle-Build%20Tool-02303A?logo=gradle)
![MySQL](https://img.shields.io/badge/MySQL-Database-blue?logo=mysql)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-green?logo=swagger)
![Postman](https://img.shields.io/badge/Postman-API%20Testing-orange?logo=postman)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ%20IDEA-IDE-black?logo=intellijidea)
![DBeaver](https://img.shields.io/badge/DBeaver-DB%20Manager-lightgrey?logo=dbeaver)

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

## 🗂️ Estrutura do Projeto

A organização segue o padrão **Clean Architecture**, garantindo separação de responsabilidades e fácil manutenção do código:

src/
└── main/
    ├── java/
    │   └── com/
    │       └── groupEight/
    │           └── TaskManagement/
    │               ├── config/          # Configurações gerais (Swagger, Security, etc.)
    │               ├── controller/      # Endpoints REST (Tarefas, Usuários, etc.)
    │               ├── DTO/             # Objetos de transferência de dados
    │               │   ├── requests/    # DTOs de entrada (POST, PUT)
    │               │   └── responses/   # DTOs de saída (GET)
    │               ├── enuns/           # Enumerações (Status, Prioridade, Ações)
    │               ├── exception/       # Tratamento de exceções personalizadas
    │               ├── mappers/         # Conversores entre entidades e DTOs
    │               ├── models/          # Entidades JPA (Tarefa, Usuário, Histórico)
    │               ├── repository/      # Interfaces de acesso ao banco de dados
    │               ├── services/        # Regras de negócio
    │               └── utils/           # Classes utilitárias
    ├── resources/                       # Configurações do Spring e application.yml
    └── test/                            # Testes automatizados

---

## ⚙️ Tecnologias Utilizadas

- **Java 17+**
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
| **Luan Silva** | Frontend Developer |
| **Gustavo Anjos** | UX/UI Designer |

---

## 💡 Melhorias Futuras

- Integração com frontend React/Angular  
- Notificações em tempo real (WebSocket)  
- Dashboard com métricas de produtividade  
- Sistema de comentários e anexos em tarefas  

---

## 📜 Licença

Este projeto foi desenvolvido exclusivamente para fins educacionais durante o hackathon e não possui fins comerciais.

---

> Feito com 💻 e ☕ pela equipe **Group Eight**
