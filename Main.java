import java.util.Scanner;

public class Main {
    private static final String filename = "expenses.dat";

    public static void main(String[] args) {
        ExpenseLinkedList exp;

        try {
            exp = ExpenseLinkedList.loadFromFile(filename);
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
            exp = new ExpenseLinkedList();
        }
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nExpense Menu:");
            System.out.println("1. Add Expense");
            System.out.println("2. Print Expenses");
            System.out.println("3. Get Total Expenses");
            System.out.println("4. Save Expenses");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter date (dd/mm/yyyy): ");
                    String date = scanner.nextLine();
                    exp.addExpense(amount, category, date);
                    break;

                case 2:
                    exp.printExpense();
                    break;

                case 3:
                    System.out.printf("Total expenses: $%.2f%n", exp.getTotalExpenses());
                    break;

                case 4:
                    try {
                        exp.saveToFile(filename);
                        System.out.println("Expenses saved successfully.");
                    } catch (Exception e) {
                        System.out.println("Error saving data: " + e.getMessage());
                    }
                    break;

                case 5: // Exit
                    System.out.print("Do you want to save your expenses before exiting? (yes/no): ");
                    String saveChoice = scanner.nextLine().trim().toLowerCase();
                    if (saveChoice.equals("yes")) {
                        try {
                            exp.saveToFile(filename);
                        } catch (Exception e) {
                            System.out.println("Error saving data: " + e.getMessage());
                        }
                    }
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
