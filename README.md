# 🏪 SaaS POS System (Multi-Store Management)

A **SaaS-based Point of Sale (POS) backend** built with **Java 17 and Spring Boot**. It lets multiple stores operate on one platform, with secure **JWT authentication**, **role-based access control** and an **admin approval workflow** for stores.

---

## 📌 Overview

The system provides REST APIs for user signup and login, store management and user profiles. Each store has an admin and employees, and a platform admin can approve or block stores. The backend follows a clean layered architecture and uses DTO mappers to keep API responses separate from database entities.

---

## 🚀 Features

- 🔐 **Signup and login** with stateless JWT authentication
- 🔑 **BCrypt password hashing**
- 👥 **Role-based access control** with 6 roles
- 🏬 **Store management**: create, view, update and delete stores
- 🧑‍💼 **Store lookup** by store admin or by employee
- ✅ **Store moderation workflow**: Pending, Active, Blocked
- 👤 **User profile** APIs
- 🧱 **Layered architecture**: controller, service, repository, mapper, DTO
- 🗄️ **MySQL** persistence with Spring Data JPA and Hibernate

---

## 🛠️ Tech Stack

| Layer | Technology |
| --- | --- |
| Language | Java 17 |
| Framework | Spring Boot, Spring MVC |
| Security | Spring Security, JWT (jjwt), BCrypt |
| Persistence | Spring Data JPA, Hibernate |
| Database | MySQL |
| Validation | Spring Boot Starter Validation |
| Utilities | Lombok |
| Build Tool | Maven (Maven Wrapper included) |

---

## 👥 User Roles

| Role | Description |
| --- | --- |
| `ROLE_ADMIN` | Platform administrator (access to `/api/super-admin/**`) |
| `ROLE_STORE_ADMIN` | Owner or admin of a store |
| `ROLE_STORE_MANAGER` | Manages a store |
| `ROLE_BRANCH_MANAGER` | Manages a store branch |
| `ROLE_CASHIER` | Handles billing at the counter |
| `ROLE_USER` | Regular user |

**Store statuses:** `PENDING`, `ACTIVE`, `BLOCKED`

---

## 📂 Project Structure

```
src/main/java/com/Saas
│
├── configuration     # Security config, JWT provider and validator
├── controller        # REST controllers (Auth, Store, User)
├── domain            # Enums (UserRole, StoreStatus)
├── exceptions        # Custom exceptions
├── mapper            # Entity <-> DTO mappers
├── model             # JPA entities (User, Store, StoreContact, Product)
├── payload           # Request and response objects
├── repository        # Spring Data JPA repositories
├── service           # Business logic
└── SaasPosSystemApplication.java
```

---

## 📡 REST API Endpoints

### Authentication

| Method | Endpoint | Description |
| --- | --- | --- |
| POST | `/auth/signup` | Register a new user |
| POST | `/auth/login` | Login and receive a JWT |

### Stores (authentication required)

| Method | Endpoint | Description |
| --- | --- | --- |
| POST | `/api/stores` | Create a store |
| GET | `/api/stores` | Get all stores |
| GET | `/api/stores/{id}` | Get a store by ID |
| GET | `/api/stores/admin` | Get the store of the logged-in store admin |
| GET | `/api/stores/employee` | Get the store of the logged-in employee |
| PUT | `/api/stores/{id}` | Update a store |
| PUT | `/api/stores/{id}/moderate` | Moderate a store (Pending, Active, Blocked) |
| DELETE | `/api/stores/{id}` | Delete a store |

### Users (authentication required)

| Method | Endpoint | Description |
| --- | --- | --- |
| GET | `/api/users/profile` | Get the profile of the logged-in user |
| GET | `/api/users/{id}` | Get a user by ID |

---

## 🔒 Security

- **Stateless sessions** using JWT; no server-side session is stored
- A **custom JWT validator filter** checks the token on every request
- All `/api/**` routes require authentication
- `/api/super-admin/**` is restricted to the `ADMIN` role
- Passwords are hashed with **BCrypt**

Send the token returned by login in the `Authorization` header of protected requests.

---

## ⚙️ Getting Started

### Prerequisites

- Java 17 or later
- MySQL 8 or later
- Maven (or use the included `mvnw`)

### 1. Clone the repository

```bash
git clone https://github.com/pratik2704-cmd/SaaS-Project-Using-Java.git
cd SaaS-Project-Using-Java
```

### 2. Create the database

```sql
CREATE DATABASE saas_pos;
```

### 3. Configure `src/main/resources/application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/saas_pos
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> Never commit real passwords or secrets. Use environment variables for anything sensitive.

### 4. Run the application

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

The API starts at `http://localhost:8080`.

### 5. Test with Postman

1. Call `POST /auth/signup` to create a user.
2. Call `POST /auth/login` and copy the JWT from the response.
3. Add the token to the `Authorization` header and call any `/api/**` endpoint.

---

## 🔮 Roadmap

- [ ] Product management APIs (the `Product` entity, repository and service are already in place)
- [ ] Orders, billing and invoices
- [ ] Payment gateway integration (Razorpay / Stripe)
- [ ] Email notifications
- [ ] API documentation with Swagger / OpenAPI
- [ ] Unit and integration tests
- [ ] Docker support

---

## 👨‍💻 Author

**Pratik Kumbhar**
Java Full Stack Developer

📧 [pratikkumbhar2704@gmail.com](mailto:pratikkumbhar2704@gmail.com)
🔗 [GitHub](https://github.com/pratik2704-cmd)

---

## ⭐ Support

If you found this project useful, please consider giving it a star on GitHub.
