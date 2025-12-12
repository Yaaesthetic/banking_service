package ma.demo;

// NOTE:
// This class is a simple manual test runner used only for demonstration.
// In real-world Java projects, automated tests are written using proper unit testing frameworks such as JUnit (for test execution) and Mockito (for mocking dependencies).

class BankingSystemTest {
    public static void main(String[] args) {
        System.out.println("Running Banking System Tests\n");

        testAcceptanceCriteria();
        testInvalidDeposit();
        testInvalidWithdrawal();
        testInsufficientBalance();
        testMultipleTransactions();

        System.out.println("\nAll Tests Completed");
    }

    private static void testAcceptanceCriteria() {
        System.out.println("Test 1: Acceptance Criteria");
        System.out.println("----------------------------");

        Account account = new Account();
        account.deposit(1000);
        account.deposit(2000);
        account.withdraw(500);
        account.printStatement();

        assert account.getBalance() == 2500;
        System.out.println("Acceptance criteria test passed\n");
    }

    private static void testInvalidDeposit() {
        System.out.println("Test 2: Invalid Deposit");
        System.out.println("-----------------------");

        try {
            Account account = new Account();
            account.deposit(-100);
            System.out.println("Test failed: Should have thrown InvalidAmountException\n");
        } catch (InvalidAmountException e) {
            System.out.println("Correctly rejected negative deposit: " + e.getMessage() + "\n");
        }
    }

    private static void testInvalidWithdrawal() {
        System.out.println("Test 3: Invalid Withdrawal");
        System.out.println("--------------------------");

        try {
            Account account = new Account();
            account.deposit(1000);
            account.withdraw(0);
            System.out.println("Test failed: Should have thrown InvalidAmountException\n");
        } catch (InvalidAmountException e) {
            System.out.println("Correctly rejected zero withdrawal: " + e.getMessage() + "\n");
        }
    }

    private static void testInsufficientBalance() {
        System.out.println("Test 4: Insufficient Balance");
        System.out.println("----------------------------");

        try {
            Account account = new Account();
            account.deposit(500);
            account.withdraw(1000);
            System.out.println("Test failed: Should have thrown InsufficientBalanceException\n");
        } catch (InsufficientBalanceException e) {
            System.out.println("Correctly rejected overdraft: " + e.getMessage() + "\n");
        }
    }

    private static void testMultipleTransactions() {
        System.out.println("Test 5: Multiple Transactions");
        System.out.println("-----------------------------");

        Account account = new Account();
        account.deposit(1000);
        account.deposit(500);
        account.withdraw(300);
        account.deposit(2000);
        account.withdraw(700);
        account.printStatement();

        assert account.getBalance() == 2500;
        System.out.println("Multiple transactions test passed\n");
    }
}