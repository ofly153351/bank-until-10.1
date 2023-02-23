
public class CheckingAccount extends Account {
    private double credit;

    public CheckingAccount() {
    }

    public CheckingAccount(double amount, double credit) {
        balance = amount;
        this.credit = credit;
    }

    public double getCredit() {
        return credit;
    }

    public boolean withdraw(double amount) throws WithdrawException {
        if (amount <= balance + credit) {
            balance -= amount;
            return true;
        } else {
            throw new WithdrawException();
        }
    }
}
