package ma.demo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Account implements AccountService {
    private int balance;
    private final List<Transaction> history;
    private final DateTimeFormatter formatter;

    public Account() {
        this.balance = 0;
        this.history = new ArrayList<>();
        this.formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }

    @Override
    public void deposit(int amount) {
        validateAmount(amount);
        balance += amount;
        recordTransaction(LocalDate.now(), amount, TransactionType.DEPOSIT);
    }

    @Override
    public void withdraw(int amount) {
        validateAmount(amount);
        validateBalance(amount);
        balance -= amount;
        recordTransaction(LocalDate.now(), amount, TransactionType.WITHDRAWAL);
    }

    @Override
    public void printStatement() {
        System.out.println("DATE       | AMOUNT  | BALANCE");

        for (int i = history.size() - 1; i >= 0; i--) {
            Transaction t = history.get(i);
            String date = t.getDate().format(formatter);
            String amount = formatAmount(t.getAmount(), t.getType());
            System.out.println(date + " | " + amount + " | " + t.getBalance());
        }
    }

    public int getBalance() {
        return balance;
    }

    private void validateAmount(int amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be positive: " + amount);
        }
    }

    private void validateBalance(int amount) {
        if (balance < amount) {
            throw new InsufficientBalanceException(
                    "Insufficient balance: " + balance + ", requested: " + amount
            );
        }
    }

    private void recordTransaction(LocalDate date, int amount, TransactionType type) {
        history.add(new Transaction(date, amount, type, balance));
    }

    private String formatAmount(int amount, TransactionType type) {
        return (type == TransactionType.WITHDRAWAL ? "-" : "+") + amount;
    }
}