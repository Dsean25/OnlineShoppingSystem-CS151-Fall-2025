public class Customer extends User {
  private ShoppingCart cart;
  private String paymentMethod;

  public Customer(
      String userID,
      String firstName,
      String lastName,
      String email,
      String phoneNumber,
      String address,
      String storeName) {
    super(userID, firstName, lastName, email, phoneNumber, address);

    cart = new ShoppingCart(userID);
  }

  // setters

  public ShoppingCart getCart() {
    return cart;
  }

  public ShoppingCart getCart() {
    return cart;
  }

  public void setCart(ShoppingCart cart) {
    this.cart = cart;
  }

  public void setCart(ShoppingCart cart) {
    this.cart = cart;
  }

  // getters
  public String getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public void addProductToCart(Product p, int qty) {
    cart.addToCart(p, qty);
    // print statements are in addtocart
  }

  public void removeProductFromCart(Product p, int qty) {
    cart.removeFromCart(p, qty);
  }

  public void placeOrder() {}

  public void calculateTotal() {
    // in cart AND checkout??
  }

  public void changePaymentMethod(String paymentMethod) {
    setPaymentMethod(paymentMethod);
    System.out.printf("Your payment method was changed to %s!%n", paymentMethod);
  }

  /*+ pending methods, according to UML:
  removeProductFromCart(Product p): void
  changeProductQuantity(Product p, int qty):void
  */

}
