import java.io.IOException;
import java.util.*;

public class Main {
    public class ObjectOrientedProgramming {

        public class BankAccount {
            private String accountName;
            private int accountNumber;
            private int accountBalance;
            private List<Transaction> transactions;

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
                Transaction transaction = new Transaction("DEPOSIT", amount, "currentTS");
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
                Transaction transaction = new Transaction("WITHDRAW", amount, "currentTS");
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

        private class Transaction {
            private String transactionType;
            private int transactionAmount;
            private String timestamp;

            private Transaction(String transactionType, int transactionAmount, String timestamp) {
                this.transactionAmount = transactionAmount;
                this.transactionType = transactionType;
                this.timestamp = timestamp;
            }
        }

    }

    public static void main(String[] args) throws IOException {
    }
}