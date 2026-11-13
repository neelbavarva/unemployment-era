package inheritance.account;

public class SavingsAccount extends Account {
    private double interestRate;

    SavingsAccount(int accountNumber, int openingBalance, double interestRate) {
        super(accountNumber, openingBalance);
        this.interestRate = interestRate;
        System.out.println("SavingsAccount constructor");
    }

    public void addInterest() {
//        super.setBalance((int) (super.getBalance() + (super.getBalance() * interestRate)));
    }

    @Override
    public void withdraw(int amount) {
        if (super.getBalance() - amount < 1000) {
            throw new RuntimeException("Insufficient balance");
        }
        super.withdraw(amount);
    }

    @Override
    public String describe() {
        return super.describe() + ", interest " + interestRate;
    }
}
