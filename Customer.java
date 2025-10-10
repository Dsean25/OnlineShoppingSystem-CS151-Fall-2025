public class Customer extends User {
  private ShoppingCart cart;
  private String paymentMethod;


  public Customer(String userID, String name,  int phoneNumber, String address){
    super(userID, name, phoneNumber, address);
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

  public void setCart(ShoppingCart cart){
    this.cart = cart;
  }

  public void placeOrder() {


  }

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
