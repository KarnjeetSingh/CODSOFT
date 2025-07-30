import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
}

class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please choose 1 to 4.");
            }
        }
    }

    private void checkBalance() {
        System.out.printf("Current balance: %.2f\n", account.getBalance());
    }

    private void deposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            account.deposit(amount);
            System.out.printf("%.2f deposited successfully.\n", amount);
        } else {
            System.out.println("Invalid amount. Deposit must be positive.");
        }
    }

    private void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (account.withdraw(amount)) {
            System.out.printf("%.2f withdrawn successfully.\n", amount);
        } else {
            System.out.println("Withdrawal failed. Check if you have sufficient balance and entered a valid amount.");
        }
    }
}

public class ATMinterface {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(10000.00);
        ATM atm = new ATM(userAccount);
        atm.start();
    }
}
