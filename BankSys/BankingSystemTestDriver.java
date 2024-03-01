package BankSys;

public class BankingSystemTestDriver {

    public static void main(String[] args) {
        // Create a new bank instance for testing
        Bank testBank = new Bank();

        // Test account creation
        String johnAccountId = testBank.createAccount("John Doe");
        String janeAccountId = testBank.createAccount("Jane Smith");

        System.out.println("Test Account Creation:");
        System.out.println("John's Account ID: " + johnAccountId);
        System.out.println("Jane's Account ID: " + janeAccountId);

        // Test depositing money
        Account johnAccount = testBank.getAccount(johnAccountId);
        Account janeAccount = testBank.getAccount(janeAccountId);

        johnAccount.deposit(500);
        janeAccount.deposit(1000);

        System.out.println("\nTest Depositing Money:");
        System.out.println("John's Balance (should be 500): " + johnAccount.getBalance());
        System.out.println("Jane's Balance (should be 1000): " + janeAccount.getBalance());

        // Test withdrawing money
        johnAccount.withdraw(200);
        janeAccount.withdraw(1200); // More than balance to test error handling

        System.out.println("\nTest Withdrawing Money:");
        System.out.println("John's Balance (should be 300): " + johnAccount.getBalance());
        System.out.println("Jane's Balance (should remain 1000): " + janeAccount.getBalance());

        // Test balance checking
        System.out.println("\nTest Balance Checking:");
        System.out.println("John's Balance: " + johnAccount.getBalance());
        System.out.println("Jane's Balance: " + janeAccount.getBalance());

        // Test error handling
        johnAccount.deposit(-100); // Invalid deposit
        janeAccount.withdraw(-500); // Invalid withdrawal

        // Test account retrieval with invalid ID
        Account invalidAccount = testBank.getAccount("invalid_id");
        System.out.println("\nTest Invalid Account Retrieval (should be null): " + invalidAccount);
    }
}
