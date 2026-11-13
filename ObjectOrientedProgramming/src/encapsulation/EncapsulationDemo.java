package encapsulation;

import encapsulation.bank.BankAccount;
import encapsulation.bank.Transaction;

import java.io.IOException;
import java.util.List;

public class EncapsulationDemo {
    public static void main(String[] args) throws IOException {
        BankAccount account = new BankAccount("Neel", 1001);
        account.deposit(500);
        account.withdraw(200);
        System.out.println("Start balance: " + account.getBalance() + " (expected 300)");

        // Attack 1: set balance directly
        // account.accountBalance = 1_000_000;   // ❌ compile error: accountBalance has private access

        // Attack 2: withdraw more than balance
        try {
            account.withdraw(10_000);
            System.out.println("FAIL: attack 2 went through");
        } catch (RuntimeException e) {
            System.out.println("Attack 2 blocked: " + e.getMessage());
        }

        // Attack 3: deposit a negative amount
        try {
            account.deposit(-50);
            System.out.println("FAIL: attack 3 went through");
        } catch (RuntimeException e) {
            System.out.println("Attack 3 blocked: " + e.getMessage());
        }

        // Attack 4: add a fake transaction to the returned list
        List<Transaction> copy = account.getTransactions();
        copy.add(copy.get(0));
        System.out.println("Attack 4: my copy has " + copy.size()
                + ", real history has " + account.getTransactions().size() + " (expected 2)");

        // Attack 5: clear the returned list
        account.getTransactions().clear();
        System.out.println("Attack 5: real history still has "
                + account.getTransactions().size() + " (expected 2)");

        // Attack 6: change a past transaction
        // account.getTransactions().get(0).transactionAmount = 999;   // ❌ compile error: private access
        // ...and there's no setter, and the field is final

        // Attack 7: forge a transaction from outside the bank package
        // new Transaction(TransactionType.DEPOSIT, 1_000_000, Instant.now());   // ❌ compile error: constructor not public

        // Failed operations must leave no trace
        System.out.println("End balance: " + account.getBalance() + " (expected 300)");
        for (Transaction t : account.getTransactions()) {
            System.out.println(t.getTransactionType() + " " + t.getTransactionAmount() + " at " + t.getTimestamp());
        }
    }
}
