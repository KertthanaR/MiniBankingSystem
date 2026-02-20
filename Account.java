/* javac *.java
    java BankSystem
     in terminal to run code- vs code
     */
import java.io.Serializable;
// Account class represents a single bank account
public class Account implements Serializable {
// Private variables to store account details
    private String accountNo;
    private String name;
    private double balance;

    private static final double MIN_BALANCE = 500.0;
// Constructor to initialize account details
    public Account(String accountNo, String name, double balance) {
        this.accountNo = accountNo;
        this.name = name;
        this.balance = balance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }
        // Method to deposit money into the account
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
            return;
        }

        if ((balance - amount) >= MIN_BALANCE) {
            balance -= amount;
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Cannot withdraw. Minimum balance must be maintained (500).");
        }
    }

    @Override
    public String toString() {
        return accountNo + "," + name + "," + balance;
    }

    public static Account fromString(String line) {
        String[] parts = line.split(",");
        return new Account(parts[0], parts[1], Double.parseDouble(parts[2]));
    }
}
