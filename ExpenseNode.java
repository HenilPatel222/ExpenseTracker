import java.io.Serializable;
public class ExpenseNode  implements Serializable{
    private static final long serialVersionUID =1L;
    double amount;
    String category;
    String date;
    ExpenseNode next;
    public ExpenseNode(double amount, String category, String date) {
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.next = null;
    }
}
