# Hibernate Banking Management System

A comprehensive, console-based banking application built with Java, Hibernate, and MySQL. This project provides a robust backend for managing core banking functionalities, including customer accounts, branches, loans, and transactions, all powered by Hibernate ORM for seamless database interaction.

## Features

- **Bank & Branch Management**: Create, update, and manage banks and their associated branches.
- **Customer & Account Management**: Onboard new customers and manage their savings or checking accounts.
- **Loan Management**: Handle loan issuance and tracking for customers.
- **Transactional Operations**: Perform deposits, withdrawals, and balance inquiries.
- **Object-Relational Mapping (ORM)**: Utilizes Hibernate for efficient and reliable data persistence with a MySQL database.
- **Console-Based UI**: A straightforward and interactive command-line interface for all banking operations.

## Technologies Used

- **Java 21**: The core programming language for the application.
- **Hibernate 6.4.4.Final**: For Object-Relational Mapping and data persistence.
- **MySQL Connector/J 8.4.0**: JDBC driver for MySQL database connectivity.
- **Maven**: For project build and dependency management.
- **SLF4J & Logback**: For application logging.

## Setup and Installation

1.  **Prerequisites**:
    *   Java 21 LTS
    *   Apache Maven
    *   MySQL Server

2.  **Clone the Repository**:
    ```bash
    git clone https://github.com/storm309/hibernate-banking-management-system.git
    cd hibernate-banking-management-system
    ```

3.  **Database Configuration**:
    *   Create a new database in MySQL:
        ```sql
        CREATE DATABASE banking_system;
        ```
    *   Update the database credentials in `src/main/resources/META-INF/persistence.xml`:
        ```xml
        <property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/banking_system?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true"/>
        <property name="jakarta.persistence.jdbc.user" value="your_username"/>
        <property name="jakarta.persistence.jdbc.password" value="your_password"/>
        ```

## How to Run

1.  **Build the Project**:
    ```bash
    mvn clean install
    ```

2.  **Run the Application**:
    ```bash
    mvn exec:java
    ```

## Database Schema

The application uses the following entities, which are mapped to tables in the `banking_system` database:

-   **Bank**: Stores bank information.
-   **Branch**: Stores branch details and is associated with a Bank.
-   **Customer**: Stores customer data and is associated with one or more Accounts.
-   **Account**: Manages account details, including account number, type, and balance.
-   **Loan**: Tracks loans issued to customers.
The relationships between these entities are managed by Hibernate.

