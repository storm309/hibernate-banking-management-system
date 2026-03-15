# Banking Management System

A complete Java Hibernate (JPA) console application demonstrating CRUD operations and entity relationships.

## Project Structure

```
Banking_Management_System/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       ├── entity/
│   │   │       │   ├── Bank.java
│   │   │       │   ├── Branch.java
│   │   │       │   ├── Customer.java
│   │   │       │   ├── Account.java
│   │   │       │   └── Loan.java
│   │   │       ├── repository/
│   │   │       │   ├── BankRepository.java
│   │   │       │   ├── BranchRepository.java
│   │   │       │   ├── CustomerRepository.java
│   │   │       │   ├── AccountRepository.java
│   │   │       │   └── LoanRepository.java
│   │   │       ├── database/
│   │   │       │   └── HibernateConnection.java
│   │   │       └── mainmenu/
│   │   │           └── MainMenu.java
│   │   └── resources/
│   │       └── META-INF/
│   │           └── persistence.xml
```

## Technologies Used

- **Java 11+**
- **Hibernate ORM 5.6.14**
- **JPA 2.2**
- **MySQL 8.0**
- **Maven 3.8+**

## Prerequisites

Before running the application, ensure you have:

1. **Java 11 or higher** installed
2. **Maven 3.8+** installed
3. **MySQL 8.0** installed and running
4. **VS Code** with Java extensions

## Database Setup

1. **Create the database:**
   ```sql
   CREATE DATABASE banking_system;
   ```

2. **Update persistence.xml** (if needed):
   - Open `src/main/resources/META-INF/persistence.xml`
   - Update the JDBC URL, username, and password:
     ```xml
     <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/banking_system?useSSL=false&amp;serverTimezone=UTC&amp;allowPublicKeyRetrieval=true"/>
     <property name="javax.persistence.jdbc.user" value="root"/>
     <property name="javax.persistence.jdbc.password" value="your_password"/>
     ```

## Building the Project

1. Open terminal in the project root directory
2. Run Maven clean and build:
   ```bash
   mvn clean install
   ```

## Running the Application

### Method 1: Using Maven
```bash
mvn exec:java -Dexec.mainClass="com.mainmenu.MainMenu"
```

### Method 2: Using Java Directly (after building)
```bash
java -cp target/banking-management-system-1.0.0.jar:target/lib/* com.mainmenu.MainMenu
```

### Method 3: Using VS Code
1. Open the project in VS Code
2. Go to Run → Run Without Debugging (Ctrl+F5)
3. Select "Java" as the language
4. Select "MainMenu" as the main class

## Entity Relationships

### Bank ↔ Branch
- **Relationship**: One-to-Many
- A Bank can have multiple Branches
- Uses: `@OneToMany`, `CascadeType.ALL`

### Branch ↔ Bank
- **Relationship**: Many-to-One
- Each Branch belongs to one Bank
- Uses: `@ManyToOne`, `@JoinColumn`

### Customer ↔ Account
- **Relationship**: Many-to-Many
- Customers can have multiple Accounts
- Accounts can have multiple Customers
- Uses: `@ManyToMany`, `@JoinTable`

### Customer ↔ Loan
- **Relationship**: Many-to-Many
- Customers can have multiple Loans
- Loans can be shared by multiple Customers
- Uses: `@ManyToMany`, `@JoinTable`

## Menu Options

1. **Add Bank** - Add a new bank
2. **Remove Bank** - Delete a bank by code
3. **Update Bank** - Update bank information
4. **Find Bank** - Search for a bank by code
5. **Add Branch** - Add a new branch to a bank
6. **Add Customer** - Create a new customer
7. **Add Account** - Create a new bank account
8. **Add Loan** - Create a new loan
9. **View All Banks** - Display all banks
10. **View All Branches** - Display all branches
11. **View All Customers** - Display all customers
12. **View All Accounts** - Display all accounts
13. **View All Loans** - Display all loans
0. **Exit** - Close the application

## Database Tables

The following tables are automatically created:

- `bank` - Stores bank information
- `branch` - Stores branch information (linked to bank)
- `customer` - Stores customer information
- `account` - Stores account information
- `loan` - Stores loan information
- `customer_account` - Junction table for Customer-Account relationship
- `customer_loan` - Junction table for Customer-Loan relationship

## Example Usage

1. Start the application
2. Add a Bank:
   - Code: `BANK001`
   - Name: `National Bank`
   - Address: `123 Main Street`

3. Add a Branch:
   - Select bank code: `BANK001`
   - Name: `Downtown Branch`
   - Address: `456 Oak Avenue`

4. Add a Customer:
   - Name: `John Doe`
   - Address: `789 Elm Street`
   - Phone: `555-1234`

5. Add an Account:
   - Account Number: `ACC001`
   - Type: `Saving`
   - Balance: `5000.00`

6. Add a Loan:
   - Type: `Home`
   - Amount: `200000.00`

## Troubleshooting

### Database Connection Issues
- Ensure MySQL is running: `mysql -u root -p`
- Check the database exists: `SHOW DATABASES;`
- Verify persistence.xml database URL, username, and password

### Build Errors
- Run `mvn clean install -DskipTests` to rebuild
- Check Java version: `java -version` (should be 11+)
- Ensure Maven is installed: `mvn -version`

### Runtime Errors
- Check database tables were created: `USE banking_system; SHOW TABLES;`
- Review console output for SQL errors
- Verify JAR dependencies are properly loaded

## Notes

- The application uses `hibernate.hbm2ddl.auto = update` to automatically create/update tables
- Set `show_sql = true` to see Hibernate SQL queries in the console
- All CRUD operations handle transactions properly
- Entity relationships use proper cascade types for data integrity

## Future Enhancements

- Add transaction history feature
- Implement account balance tracking
- Add loan payment scheduling
- Create reports generation
- Implement authentication
- Add data validation and error handling

---

**Created**: March 2026
**Version**: 1.0.0
