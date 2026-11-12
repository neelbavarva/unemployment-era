package bank;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public final class BankAccount {
    private final String accountName;
    private final int accountNumber;
    private int accountBalance;
    private final List<Transaction> transactions;

    public BankAccount(String accountName, int accountNumber) {
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.accountBalance = 0;
        transactions = new ArrayList<>();
    }

    public Transaction deposit(int amount) {
        if (amount <= 0) {
            throw new RuntimeException("Invalid Amount");
        }
        this.accountBalance += amount;
        Transaction transaction = new Transaction(TransactionType.DEPOSIT, amount, Instant.now());
        this.transactions.add(transaction);
        return transaction;
    }

    public Transaction withdraw(int amount) {
        if (amount <= 0) {
            throw new RuntimeException("Invalid Amount");
        }
        if (amount > accountBalance) {
            throw new RuntimeException("Insufficient Balance");
        }
        this.accountBalance -= amount;
        Transaction transaction = new Transaction(TransactionType.WITHDRAW, amount, Instant.now());
        this.transactions.add(transaction);
        return transaction;
    }

    public int getBalance() {
        // For a primitive (int), the value itself is copied
        return accountBalance;
    }

    public List<Transaction> getTransactions() {
        // For an object, the reference is copied
        return new ArrayList<>(transactions);
    }

}
