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
  public Order() {}

  // Getters and setters
  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getPaymentId() {
    return paymentId;
  }

  public void setPaymentId(String paymentId) {
    this.paymentId = paymentId;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public int getProductCount() {
    return productCount;
  }

  public void setProductCount(int productCount) {
    this.productCount = productCount;
  }

  public double getTotalCost() {
    return totalCost;
  }

  public void setTotalCost(double totalCost) {
    this.totalCost = totalCost;
  }

  public double getTaxRate() {
    return taxRate;
  }

  public void setTaxRate(double taxRate) {
    this.taxRate = taxRate;
  }

  public Date getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
  }

  public Date getDeliveryDate() {
    return deliveryDate;
  }

  public void setDeliveryDate(Date deliveryDate) {
    this.deliveryDate = deliveryDate;
  }

  public boolean isConfirmed() {
    return confirmed;
  }

  public void setConfirmed(boolean confirmed) {
    this.confirmed = confirmed;
  }

  public boolean isCanceled() {
    return canceled;
  }

  public void setCanceled(boolean canceled) {
    this.canceled = canceled;
  }

  public boolean isDelivered() {
    return delivered;
  }

  public void setDelivered(boolean delivered) {
    this.delivered = delivered;
  }

  public boolean isReturnInitiated() {
    return returnInitiated;
  }

  public void setReturnInitiated(boolean returnInitiated) {
    this.returnInitiated = returnInitiated;
  }

  public boolean isReturnCompleted() {
    return returnCompleted;
  }

  public void setReturnCompleted(boolean returnCompleted) {
    this.returnCompleted = returnCompleted;
  }
}
