import java.io.*;
import java.util.*;

public class BankSystem {

    private static final String FILE_NAME = "accounts.txt";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== Mini Banking System =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Search Account");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1:
                        createAccount();
                        break;
                    case 2:
                        depositAmount();
                        break;
                    case 3:
                        withdrawAmount();
                        break;
                    case 4:
                        searchAccount();
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void createAccount() throws IOException {
        System.out.print("Enter Account Number: ");
        String accNo = scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Initial Balance: ");
        double balance = scanner.nextDouble();
        scanner.nextLine();

        Account account = new Account(accNo, name, balance);

        FileWriter fw = new FileWriter(FILE_NAME, true);
        fw.write(account.toString() + "\n");
        fw.close();

        System.out.println("Account created successfully.");
    }

    private static List<Account> readAccounts() throws IOException {
        List<Account> accounts = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) return accounts;

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {
            accounts.add(Account.fromString(line));
        }

        br.close();
        return accounts;
    }

    private static void writeAccounts(List<Account> accounts) throws IOException {
        FileWriter fw = new FileWriter(FILE_NAME);

        for (Account acc : accounts) {
            fw.write(acc.toString() + "\n");
        }

        fw.close();
    }

    private static void depositAmount() throws IOException {
        System.out.print("Enter Account Number: ");
        String accNo = scanner.nextLine();

        List<Account> accounts = readAccounts();
        boolean found = false;

        for (Account acc : accounts) {
            if (acc.getAccountNo().equals(accNo)) {
                System.out.print("Enter amount to deposit: ");
                double amount = scanner.nextDouble();
                scanner.nextLine();

                acc.deposit(amount);
                found = true;
                break;
            }
        }

        if (found) {
            writeAccounts(accounts);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void withdrawAmount() throws IOException {
        System.out.print("Enter Account Number: ");
        String accNo = scanner.nextLine();

        List<Account> accounts = readAccounts();
        boolean found = false;

        for (Account acc : accounts) {
            if (acc.getAccountNo().equals(accNo)) {
                System.out.print("Enter amount to withdraw: ");
                double amount = scanner.nextDouble();
                scanner.nextLine();

                acc.withdraw(amount);
                found = true;
                break;
            }
        }

        if (found) {
            writeAccounts(accounts);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void searchAccount() throws IOException {
        System.out.print("Enter Account Number: ");
        String accNo = scanner.nextLine();

        List<Account> accounts = readAccounts();

        for (Account acc : accounts) {
            if (acc.getAccountNo().equals(accNo)) {
                System.out.println("\nAccount Found:");
                System.out.println("Name: " + acc.getName());
                System.out.println("Balance: " + acc.getBalance());
                return;
            }
        }

        System.out.println("Account not found.");
    }
}
