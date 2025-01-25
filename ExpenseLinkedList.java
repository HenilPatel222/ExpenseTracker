import java.io.*;
public class ExpenseLinkedList implements Serializable{
    private static final long serialVersionUID =1L;
    private ExpenseNode head;
    public void addExpense(double amount,String category,String date){
        ExpenseNode newNode=new ExpenseNode(amount,category,date);
        if(head==null){
            head=newNode;
        }
        else{
            ExpenseNode current=head;
            while(current.next!=null){
                current=current.next;
            }
            current.next=newNode;
        }
    }
    public void printExpense() {
        ExpenseNode current = head;
        if (current == null) {
            System.out.println("No Expenses record found");
            return;
        }
        while (current != null) {
            System.out.printf("Amount: $%.2f, Category: %s, Date: %s\n",
                    current.amount, current.category, current.date);
            current = current.next; // Move to the next node
        }
    }
    public double getTotalExpenses(){
        double total=0;
        ExpenseNode current=head;
        while(current!=null){
            total+=current.amount;
            current=current.next;
        }
        return total;
    }
    public void saveToFile(String fileName) throws IOException {
        try(ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(fileName))){
                out.writeObject(this);
                System.out.println("Expense saved successfully to "+fileName);
        }
        catch(IOException e){
            System.err.println("Error saving expense to file "+fileName);
            e.printStackTrace();
        }
    }
    public static ExpenseLinkedList loadFromFile(String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            return (ExpenseLinkedList) in.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No previous data found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading from file: " + e.getMessage());
        }
        return new ExpenseLinkedList(); // Return empty list if error occurs
    }
}
