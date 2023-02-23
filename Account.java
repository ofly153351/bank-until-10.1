
public class Account {
    protected double balance;

    public Account() {
    }

    public Account(double amount) {
        balance = amount;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) throws WithdrawException {
        if (balance >= amount) {
            balance -= amount;
            return true;
        } else {
            throw new WithdrawException();
        }
    }

    public void updateBalance(double b) {
        balance = b;
    }

    public double getBalance() {
        return balance;
    }

    public void showBalance() {
        System.out.println(balance);
    }

}