package BankSys;

import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;

// Account class representing a bank account
class Account {
    private String accountId;
    private String name;
    private double balance;

    // Constructor for Account
    public Account(String name) {
        this.accountId = UUID.randomUUID().toString(); // Generate unique ID
        this.name = name;
        this.balance = 0.0; // Initial balance set to 0
    }

    // Getter for account ID
    public String getAccountId() {
        return accountId;
    }

    // Getter for account holder's name
    public String getName() {
        return name;
    }

    
    // Getter for balance // formatted to two decimal places
    public String getBalance() 
    {
        return String.format("%.2f", balance);
    }


    // Method to deposit money into account
    public void deposit(double amount) 
    {
        if (amount > 0) {
            balance += amount;
        } else {
            System.out.println("Invalid amount. Please enter a positive number.");
        }
    }

    // Method to withdraw money from account
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Invalid amount. Please enter a positive number up to your balance.");
        }
    }
}

// Bank class managing multiple accounts
class Bank {
    private HashMap<String, Account> accounts = new HashMap<>();

    // Method to create a new account
    public String createAccount(String name) {
        Account newAccount = new Account(name);
        accounts.put(newAccount.getAccountId(), newAccount); // Add new account to map
        return newAccount.getAccountId();
    }

    // Method to retrieve an account by ID
    public Account getAccount(String accountId) {
        return accounts.getOrDefault(accountId, null);
    }
}

// Main class for the banking system
public class BankSys {
    private static Bank bank = new Bank();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            // Display menu options
            System.out.println("\nWelcome to Simple Banking System");
            System.out.println("1. Create an Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();  // Read user input
            scanner.nextLine(); // Consume newline left-over

            // Process user input
            switch (option) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    System.out.println("Thank you for using Simple Banking System.");
                    return;  // Exit the program
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    // Method to handle account creation
    private static void createAccount() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        String accountId = bank.createAccount(name);
        System.out.println("New account created for " + name + ". Your account ID is: " + accountId);
    }

    // Method to handle depositing money
    private static void depositMoney() {
        System.out.print("Enter your account ID: ");
        String accountId = scanner.next();
        Account account = bank.getAccount(accountId);

        if (account != null) {
            System.out.print("Enter amount to deposit: ");
            double amount = scanner.nextDouble();
            account.deposit(amount);
            System.out.println("Deposited successfully. New balance: " + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    // Method to handle withdrawing money
    private static void withdrawMoney() {
        System.out.print("Enter your account ID: ");
        String accountId = scanner.next();
        Account account = bank.getAccount(accountId);

        if (account != null) {
            System.out.print("Enter amount to withdraw: ");
            double amount = scanner.nextDouble();
            account.withdraw(amount);
            System.out.println("Withdrawn successfully. New balance: " + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    // Method to check account balance
    private static void checkBalance() {
        System.out.print("Enter your account ID: ");
        String accountId = scanner.next();
        Account account = bank.getAccount(accountId);

        if (account != null) {
            System.out.println("Your current balance is: " + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }
}
