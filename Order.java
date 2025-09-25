package grocery;
import java.util.Date;

public class Order {
    private String orderId;
    private String paymentId;
    private Product product;
    private int productCount;
    private double totalCost;
    private double taxRate;
    private Date orderDate;
    private Date deliveryDate;
    private boolean confirmed;
    private boolean canceled;
    private boolean delivered;
    private boolean returnInitiated;
    private boolean returnCompleted;

    // Constructor
    public Order() {
    }
}