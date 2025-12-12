# Banking System

A lightweight Java banking system that supports deposits, withdrawals, transaction history tracking, and balance validation.
The project is packaged as a runnable JAR and can be executed locally or inside a Docker container.

---

## Features

* Deposit and withdraw money
* Balance validation (no overdraft)
* Transaction history with date and running balance
* Clear domain exceptions:

    * `InvalidAmountException`
    * `InsufficientBalanceException`
* Manual test runner for demonstration purposes
* Dockerized execution (no Java required on host)

---

## Project Structure

```
src/
└── main/java/ma/demo/
    ├── Account.java
    ├── AccountService.java
    ├── Transaction.java
    ├── TransactionType.java
    ├── InvalidAmountException.java
    ├── InsufficientBalanceException.java
    ├── BankingSystemTest.java
    └── Main.java
```

---

## Requirements

* Java 17 (for local execution)
* Maven
* Docker (optional)

---

## Build the Project

### Local build

```bash
mvn clean package
```

This generates the JAR in:

```
target/banking_service-1.0-SNAPSHOT.jar
```

---

## Run Locally

### Run main application

```bash
java -cp target/banking_service-1.0-SNAPSHOT.jar ma.demo.Main
```

### Run manual test runner

```bash
java -cp target/banking_service-1.0-SNAPSHOT.jar ma.demo.BankingSystemTest
```

---

## Run with Docker

### Build the Docker image

```bash
docker build -t banking-service .
```

---

### Run main application (default)

```bash
docker run banking-service
```

---

### Run Banking System Tests

```bash
docker run banking-service ma.demo.BankingSystemTest
```

This executes the manual test suite inside the container.

---

## Notes

* `BankingSystemTest` is a **manual test runner**, not a JUnit test.
* Tests should be implemented using:

    * **JUnit 5** for unit testing
    * **Mockito** for mocking
* This project is intentionally kept simple to demonstrate:

    * Clean Java design
    * Exception handling
    * Docker-friendly CLI execution