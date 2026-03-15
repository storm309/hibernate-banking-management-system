# Quick Start Guide

## Setup Steps (5 minutes)

### Step 1: Database Setup
```sql
-- Open MySQL and run:
CREATE DATABASE banking_system;
```

### Step 2: Verify Persistence Configuration
Edit `src/main/resources/META-INF/persistence.xml` and ensure these match your MySQL setup:
```xml
<property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/banking_system?useSSL=false&amp;serverTimezone=UTC&amp;allowPublicKeyRetrieval=true"/>
<property name="javax.persistence.jdbc.user" value="root"/>
<property name="javax.persistence.jdbc.password" value="your_password_here"/>
```

### Step 3: Build the Project
Open terminal in project root and run:
```bash
mvn clean install
```

### Step 4: Run the Application
```bash
mvn exec:java -Dexec.mainClass="com.mainmenu.MainMenu"
```

## Test Data (Quick Example)

Once the app is running, try this:

1. **Add a Bank**
   - Code: `HDFC`
   - Name: `HDFC Bank`
   - Address: `Mumbai`

2. **Add a Branch**
   - Bank Code: `HDFC`
   - Name: `Downtown Branch`
   - Address: `Bandra, Mumbai`

3. **Add a Customer**
   - Name: `Rajesh Kumar`
   - Address: `Bandra, Mumbai`
   - Phone: `9876543210`

4. **Add an Account**
   - Account Number: `1001`
   - Type: `Saving`
   - Balance: `50000`

5. **Add a Loan**
   - Type: `Home`
   - Amount: `500000`

## File Summary

| Component | Files |
|-----------|-------|
| Entities | 5 classes (Bank, Branch, Customer, Account, Loan) |
| Repositories | 5 classes with CRUD operations |
| Database | HibernateConnection.java |
| Menu | MainMenu.java with 13 operations |
| Config | persistence.xml + pom.xml |

## Key Features

✅ Complete CRUD operations for all entities
✅ OneToMany relationship (Bank-Branch)
✅ ManyToOne relationship (Branch-Bank)
✅ ManyToMany relationships (Customer-Account, Customer-Loan)
✅ Transaction management with proper rollback
✅ Cascade delete/update with orphan removal
✅ Hibernate auto-schema creation
✅ SQL query logging for debugging

## Troubleshooting

### Maven Build Fails
- Check Java version: `java -version` (needs 11+)
- Clear Maven cache: `mvn clean`

### Database Connection Error
- Ensure MySQL is running
- Check credentials in persistence.xml
- Verify database `banking_system` exists

### Tables Not Created
- Check logs for SQL errors
- Verify Hibernate dialect: `org.hibernate.dialect.MySQL8Dialect`
- `hibernate.hbm2ddl.auto` must be `update` or `create`

---

Once this runs successfully, you have a fully functional Banking Management System! 🚀
