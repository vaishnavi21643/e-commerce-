# E-Commerce Platform

A full-stack E-Commerce web application built using Spring Boot, React.js, Spring Security, JWT, and MySQL.

This project includes secure authentication, role-based access control, product management, order management, cart functionality, and a responsive frontend UI.

---

## 🚀 Features

### User Features
- User Registration and Login using JWT Authentication
- Browse Products
- Live Product Search
- Category-based Product Filtering
- Add to Cart
- Real-time Stock Validation
- Place Orders Securely
- Protected User Routes

### Admin Features
- Admin Login
- Add / Update / Delete Products
- Upload Product Images
- Manage Orders
- Role-based Access Control

---

## 🛠 Tech Stack

### Backend
- Java
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA
- Hibernate
- MySQL
- Maven

### Frontend
- React.js
- Axios
- Context API
- React Router DOM
- Bootstrap / CSS

### Database
- MySQL

---

## 📂 Project Structure

Backend:
src/main/java
- controller
- service
- repository
- model
- config
- security
- dto

Frontend:
src/
- components
- pages
- context
- services
- routes

---

## 🔐 Security Features

- JWT Token-based Authentication
- Role-based Authorization (ADMIN / USER)
- Protected Frontend Routes
- Axios Interceptors for Auto Token Injection
- Password Encryption using BCrypt

---

## ⚙️ How to Run the Project

## Step 1: Clone Repository

git clone your-repository-link

---

## Step 2: Backend Setup

### Open backend project in IntelliJ / STS

### Configure MySQL in application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update

---

## Step 3: Run Spring Boot Application

Run:
EcommerceApplication.java

Server runs on:
http://localhost:8080

---

## Step 4: Frontend Setup

### Open frontend folder in VS Code

Install dependencies:

npm install

Run frontend:

npm start

Frontend runs on:
http://localhost:3000

---

## 📸 Main Modules

- Authentication Module
- Product Management
- Cart Management
- Order Management
- Admin Dashboard
- Search & Filter System
- Image Upload Module

---

## 💡 Learning Highlights

- Layered Architecture Implementation
- Clean REST API Design
- Transaction Management using @Transactional
- Secure Authentication using JWT
- Full-stack Integration
- Production-level Project Structure

---

## 👩‍💻 Developed By

Vaishnavi

Aspiring Java Full Stack Developer 

---
