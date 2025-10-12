public class Customer extends User implements Operations {
  private ShoppingCart cart;
  private String paymentMethod;

  public Customer(String userID, String name, String phoneNumber, String address) {
    super(userID, name, phoneNumber, address);
    this.cart = new ShoppingCart(userID);
  }

  // getters and setters
  public ShoppingCart getCart() {
    return cart;
  }

  public String getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public void setCart(ShoppingCart cart) {
    this.cart = cart;
  }

  public void placeOrder() {}

  @Override
  public void addProduct(Product p, int qty) {
    cart.addProduct(p, qty);
    // print statements are in addtocart
  }

  @Override
  public void removeProduct(Product p, int qty) {
    cart.removeProduct(p, qty);
  }

  public void changePaymentMethod(String paymentMethod) {
    setPaymentMethod(paymentMethod);
    System.out.printf("Your payment method was changed to %s!%n", paymentMethod);
  }
}
