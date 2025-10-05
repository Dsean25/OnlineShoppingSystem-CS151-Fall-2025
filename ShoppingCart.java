import java.time.LocalDateTime;

public class ShoppingCart {
  String customerId;
  Product product;
  int productCount;
  public double lastUpdated; // difference between public vs not writing anything
  int instanceCount;

  // constructor
  public ShoppingCart(String customerId, Product product, int productCount, double lastUpdated) {
    this.customerId = customerId;
    this.product = null;
    this.productCount = 0;
    this.lastUpdated = LocalDateTime.now();
    this.instanceCount++; // counts instances
  }

  // getters
  public String getcustomerId() {
    return this.customerId;
  }

  public Product getproduct() {
    return this.product;
  }

  public int productCount() {
    return this.productCount;
  }

  public double lastUpdated() {
    return this.lastUpdated;
  }

  public int getinstances() {
    return this.instanceCount;
  }

  // end of getters

  // setters
  public void setcustomerId(String customerID) {
    this.customerID = customerID;
  }

  public void setproduct(String product) {
    this.product = product;
  }

  public void setproductCount(String productCount) {
    this.productCount = productCount;
  }

  public void setlastUpdated(String lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  // end of setters, did not set instanceCount

  public static void removeFromCart(Product P) {}

  public static void addProductToCart(Product P) {}

  public static double calculateTotal() {}

  public static void clearCart() {}
}
