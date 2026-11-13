package inheritance.account;

public class Account {
    private final int accountNumber;
    private int balance;

    Account(int accountNumber, int openingBalance) {
        this.accountNumber = accountNumber;
        this.balance = openingBalance;
    }

    public void deposit(int amount) {
        if (amount <= 0) {
            throw new RuntimeException("Invalid amount");
        }
        balance += amount;
    }

    public void withdraw(int amount) {
        if (amount > balance) {
            throw new RuntimeException("Insufficient balance");
        }
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }

    public String describe() {
        return "Account #" + accountNumber + ", balance " + balance;
    }
}
