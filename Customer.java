public class Customer extends User {
  private String name;
  private String customerId;
  private ShoppingCart cart;
  private String paymentMethod;

  // getters and setters
  public void setCustomerID(String customerId) {
    this.customerId = customerId;
  }

  public Customer(String name, String customerId) {
    // need to call on super();
    super(customerId, name, "", name.toLowerCase() + "@gmail.com", "", "");
    this.name = name;
    this.customerId = customerId;
  }

  public ShoppingCart getCart() {
    return cart;
  }

  public String getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public void placeOrder() {}

  public void addProduct(Product p, int qty) {
    cart.addProduct(p, qty);
    // print statements are in addtocart
  }

  public void removeProduct(Product p, int qty) {
    cart.removeProduct(p, qty);
  }

  public void changePaymentMethod(String paymentMethod) {
    setPaymentMethod(paymentMethod);
    System.out.printf("Your payment method was changed to %s!%n", paymentMethod);
  }
}
