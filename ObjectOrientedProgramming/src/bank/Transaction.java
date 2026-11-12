package bank;

import java.time.Instant;

public final class Transaction {
    private final TransactionType transactionType;
    private final int transactionAmount;
    private final Instant timestamp;

    Transaction(TransactionType transactionType, int transactionAmount, Instant timestamp) {
        this.transactionAmount = transactionAmount;
        this.transactionType = transactionType;
        this.timestamp = timestamp;
    }

    public TransactionType getTransactionType() {
        return this.transactionType;
    }

    public int getTransactionAmount() {
        return this.transactionAmount;
    }

    public Instant getTimestamp() {
        return this.timestamp;
    }
}