/*
 Class: Order.java

 Purpose:
 - Represents an order placed by a customer in the online shopping system
 - Manages the full order cycle: processing -> cancellation (optional) -> delivery -> returns
 - Update Product stock accordingly based on order status
*/

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Order {
  private static final int MAX_INSTANCES = 100;
  private static int instanceCount = 0;

  private static int orderCount = 0;
  private String orderId;
  private String paymentId;
  private HashMap<Product, Integer> productOrdered;
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
    if (instanceCount >= MAX_INSTANCES) {
      throw new IllegalStateException("Reached max Order limit (" + MAX_INSTANCES + ")");
    }

    productOrdered = new HashMap<>();
    orderCount++;
    this.orderId = "#" + orderCount;
    this.taxRate = 0.05;

    instanceCount++;
  }

  public static int getInstanceCount() {
    return instanceCount;
  }

  // Getters and setters
  public String getOrderId() {
    return orderId;
  }

  public String getPaymentId() {
    return paymentId;
  }

  public HashMap<Product, Integer> getProductOrdered() {
    return productOrdered;
  }

  public double getTotalCost() {
    return totalCost;
  }

  public double getTaxRate() {
    return taxRate;
  }

  public Date getOrderDate() {
    return orderDate;
  }

  public Date getDeliveryDate() {
    return deliveryDate;
  }

  public static int getOrderCount() {
    return orderCount;
  }

  public boolean isConfirmed() {
    return confirmed;
  }

  public boolean isCanceled() {
    return canceled;
  }

  public boolean isDelivered() {
    return delivered;
  }

  public boolean isReturnInitiated() {
    return returnInitiated;
  }

  public boolean isReturnCompleted() {
    return returnCompleted;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public void setPaymentId(String paymentId) {
    this.paymentId = paymentId;
  }

  public void setProductOrdered(HashMap<Product, Integer> productOrdered) {
    this.productOrdered = new HashMap<>(productOrdered);
  }

  public void setTotalCost(double totalCost) {
    this.totalCost = computeTotalCost();
  }

  public void setTaxRate(double taxRate) {
    this.taxRate = taxRate;
  }

  public void setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
  }

  public void setDeliveryDate(Date deliveryDate) {
    this.deliveryDate = deliveryDate;
  }

  public void setConfirmed(boolean confirmed) {
    this.confirmed = confirmed;
  }

  public void setCanceled(boolean canceled) {
    this.canceled = canceled;
  }

  public void setDelivered(boolean delivered) {
    this.delivered = delivered;
  }

  public void setReturnInitiated(boolean returnInitiated) {
    this.returnInitiated = returnInitiated;
  }

  public void setReturnCompleted(boolean returnCompleted) {
    this.returnCompleted = returnCompleted;
  }

  // Methods
  // processOrder():
  // processes and confirms order
  // update Product stock according
  public void processOrder() {
    if (confirmed) {
      System.out.println("Order has already been confirmed.");
      return;
    }

    // Check: enough stock for all the products ordered
    for (Map.Entry<Product, Integer> entry : productOrdered.entrySet()) {
      Product product = entry.getKey();
      int quantity = entry.getValue();
      if (product.getStock() < quantity) {
        System.out.println(
            "Not enough stock for product " + product.getName() + ". Order can't be confirmed.");
        return;
      }
    }

    // Update Product stock accordingly
    for (Map.Entry<Product, Integer> entry : productOrdered.entrySet()) {
      Product product = entry.getKey();
      int quantity = entry.getValue();
      int currentStock = product.getStock();
      try {
        product.setStock(currentStock - quantity);
      } catch (InvalidStockException e) {
        System.out.println("Error updating stock for " + product.getName() + ": " + e.getMessage());
        return;
      }
    }

    confirmed = true;
    this.orderDate = new java.util.Date();
    System.out.println("Order has been confirmed successfully.");
  }

  // computeTotalCost():
  // calculates the finalized total cost of the order (including tax)
  public double computeTotalCost() {
    if (!confirmed) {
      System.out.println("Order is not confirmed. Please process order first.");
      return -1;
    }

    double totalCost = 0.0;

    // Add cost for each product ordered
    for (Map.Entry<Product, Integer> entry : productOrdered.entrySet()) {
      Product product = entry.getKey();
      int quantity = entry.getValue();
      totalCost += product.getCurrentPrice() * quantity;
    }

    // Calculate tax
    totalCost = totalCost * (1 + taxRate);

    // Round to 2 decimals
    totalCost = Math.round(totalCost * 100.0) / 100.0;

    return totalCost;
  }

  // cancelOrder():
  // Cancel order only under this condition: confirmed and not delivered
  public void cancelOrder() {
    if (!confirmed) {
      System.out.println("Order is not confirmed. Cancellation is not allowed.");
      return;
    }

    if (delivered) {
      System.out.println("Order is delivered. Please create a return instead.");
      return;
    }

    if (canceled) {
      System.out.println("Order has already been canceled.");
      return;
    }

    canceled = true;
    System.out.println("Order has been canceled successfully.");
  }

  // initiateReturn():
  // allow Customer to initiate a return only under this condition: order confirmed and delivered
  public void initiateReturn() {
    if (!confirmed) {
      System.out.println("Order is not confirmed. Return is not allowed.");
      return;
    }

    if (canceled) {
      System.out.println("Order is canceled. Return is not allowed.");
      return;
    }

    if (!delivered) {
      System.out.println(
          "Order is not delivered. Please create a return after order is delivered.");
      return;
    }

    returnInitiated = true;
    System.out.println("Return initiated successfully.");
  }

  // completeReturn():
  // allows Customer to manually record a completed return only under this condition: return
  // initiated
  // adds returned product(s) back to Product stock
  public void completeReturn() {
    if (!returnInitiated) {
      System.out.println("Return has not been initiated. Please create a return first.");
      return;
    }

    // Add returned product(s) back to Product stock
    for (Map.Entry<Product, Integer> entry : productOrdered.entrySet()) {
      Product product = entry.getKey();
      int quantity = entry.getValue();
      int restocked = product.getStock() + quantity;
      try {
        product.setStock(restocked);
      } catch (InvalidStockException e) {
        System.out.println("Error restocking product " + product.getName() + ": " + e.getMessage());
      }
    }

    returnCompleted = true;
    returnInitiated = false;
    confirmed = false;
    delivered = false;
    System.out.println("Return has been completed successfully.");
  }

  // refundStatus():
  // allows customer to check their refund status
  public void refundStatus() {
    if (!returnCompleted) {
      System.out.println("Return has not been completed. Please return the order for a refund.");
      return;
    }

    System.out.println("Order has been refunded successfully.");
    System.out.println(
        "Please allow 3-5 business days for the amount to be reflected on your original payment method.");
  }
}
