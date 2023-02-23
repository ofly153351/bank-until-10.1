import java.util.ArrayList;

public class Customer {
    public static String dat;
    private String firstName;
    private String lastName;
    private ArrayList<Account> acct;
    // private int numOfAccount = 0;

    public Customer(String fName, String lName) {
        firstName = fName;
        lastName = lName;
        acct = new ArrayList<Account>();
    }

    public String toString() {
        return getFirstName() + " " + getLastName();
    }

    public Customer() {
        acct = new ArrayList<Account>();
    }

    public void setFirstName(String fName) {
        firstName = fName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lName) {
        lastName = lName;
    }

    public String getLastName() {
        return lastName;
    }

    public void addAccount(Account acct) {
        if (acct instanceof CheckingAccount)
            this.acct.add((CheckingAccount) acct);
        else if (acct instanceof SavingAccount)
            this.acct.add((SavingAccount) acct);
        else
            this.acct.add((Account) acct);

    }

    public Account getAccount(int index) {
        return acct.get(index);
    }

    public int getNumOfAccount() {
        return acct.size();
    }

    public void printReport() {
        System.out.println(getFirstName() + " " + getLastName() + " " + acct.size());
        for (int i = 0; i < acct.size(); i++) {
            if (acct.get(i) instanceof CheckingAccount) {
                System.out.println("Checking Account " + (i + 1) + " : credit = "
                        + ((CheckingAccount) acct.get(i)).getCredit() + " current balance = "
                        + acct.get(i).getBalance());
            } else {
                System.out.println("Saving Account " + (i + 1) + " : current balance = " + acct.get(i).getBalance());
            }
        }
    }

}