#
🥊 UFC Fight Predictor

##
A full-stack application that predicts the winner of UFC fights using historical data and machine learning.
The backend is built with Java + Spring Boot, uses MySQL for persistence, and a Smile ML model for predictions.
The frontend is implemented with React, HTML, and CSS.

📌 Project Overview

This project predicts the outcome of UFC fights by analyzing fighter statistics, historical fight data, and contextual factors.
The backend exposes REST APIs that the frontend consumes to display predictions and fighter comparisons.

Key goals of the project:

Apply machine learning to a real-world sports problem

Practice clean backend architecture with Spring Boot

Separate ML logic, business logic, and data access

Follow secure configuration practices (no secrets in Git)

🏗️ Architecture Overview
Frontend (React)
        ↓
Spring Boot REST API
        ↓
Prediction Service
        ↓
Machine Learning Model (Smile)
        ↓
MySQL Database

Backend Layers

Controller Layer – Handles HTTP requests

Service Layer – Business logic & feature engineering

ML Layer – Model loading, training, and prediction

Repository Layer – Database access via JPA

Database – Stores fighters, fights, and statistics

⚙️ Tech Stack
Backend

Java

Spring Boot

Spring Web

Spring Data JPA

Hibernate

Machine Learning

Smile (classification & regression models)

Database

MySQL 8

Frontend

React

HTML

CSS

Tools & DevOps

Maven – Dependency management & build

Git – Version control

GitHub – Source control & collaboration

Postman – API testing

🧠 Machine Learning

Uses historical UFC fight data

Feature engineering includes:

Differences between fighter stats

Per-minute normalization

Recent fight performance

Models are trained offline and serialized

Spring Boot loads the trained model at startup for fast predictions

⚠️ Note: Training is not done during live API requests.

🔐 Configuration & Security

Sensitive values (database credentials) are not committed to Git.

Credentials are provided using environment variables:

spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}


This follows industry best practices for secret management.

🚀 Running the Backend
Prerequisites

Java 17+

Maven

MySQL running locally

Environment variables set:

DB_USERNAME

DB_PASSWORD

Steps
git clone https://github.com/your-username/ufc-predictor.git
cd ufc-predictor
./mvnw spring-boot:run


The backend will start on:

http://localhost:8080

🔍 API Testing

The backend APIs can be tested using Postman.

Typical endpoints include:

GET /fighters

POST /predict

GET /fights/{id}

Swagger/OpenAPI support can be added for interactive documentation.

🧪 Testing

Unit and integration tests use Spring Boot Test

Repositories and services are tested independently

API endpoints are manually tested using Postman

📁 Project Structure (Backend)
src/main/java
 ├── controller
 ├── service
 ├── ml
 ├── repository
 ├── model
 └── config

src/main/resources
 ├── application.properties
 └── models/

📈 Future Improvements

Add user accounts & authentication

Improve feature engineering and model accuracy

Integrate live betting odds

Deploy using Docker & cloud services

Add caching for frequent predictions

👤 Author

Ryan Pugh
Computer Science Student
Project focused on backend architecture, ML integration, and secure configuration
