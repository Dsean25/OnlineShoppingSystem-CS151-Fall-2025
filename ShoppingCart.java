import java.time.LocalDateTime;

public class ShoppingCart {
  private String customerId;
  private int productCount;
  private double lastUpdated;
  private int instanceCount;

  // constructor
  public ShoppingCart(String customerId) {
    this.customerId = customerId;
    this.product = null;
    this.productCount = 0;
    // this.lastUpdated = LocalDateTime.now();
    this.instanceCount++; // counts instances
  }

  // getters
  public String getcustomerId() {
    return this.customerId;
  }

  public int getproductCount(Product product) {
    // return product count from looking through hashmap for that product
  }

  public double lastUpdated() {
    return this.lastUpdated;
  }

  public int getinstances() {
    return this.instanceCount;
  }

  // end of getters

  // setters
  public void setcustomerId(String customerId) {
    this.customerId = customerId;
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

  public static void removeAllFromCart(Product P) {

  }

  public static void addProductToCart(Product P) {

  }

  public static double calculateTotal() {

  }

  public static void clearCart() {

  }
}

public class ShoppingCart { // only want to be able to make one instance per customer
  // uses hashmap to keep track of products and product count. Hashmap first
  // value: Instances of the product class. 2nd: #amount in cart
  private String customerId;
  private HashMap<Product, Integer> productsList;

  public ShoppingCart(String customerId) {
    this.customerId = customerId;
    // add a hashmap thing
    productsList = new HashMap<>();
  }

  // getters
  public String getcustomerId() {
    return customerId;
  }

  public Map<Product, Integer> getproductsList() { // map so i can freely change the field type
    return productsList;
  }

  // setters
  public void setcustomerId(String customerId) {
    this.customerId = customerId;
  }

  /*
   * TODO: public void setProducts() { //map so i can freely change the field type
   * return products;
   * }
   */ // how do i set products except adding and subtracting

  /* TODO do getters and setters and methods have to be static */

  public static void calculateTotalInCart() {
    double sum = 0;
    for (Map.Entry<Product, Integer> entry : productsList.entrySet()) {
      Product product = entry.getKey();
      int quantity = entry.getValue();
      sum += product.getPrice() * quantity;
    }

    System.out.println("Total: $" + sum);
  }

  public void viewCart() {
    if (productsList.isEmpty()) {
      System.out.println("Your cart is empty.");
    } else {
      System.out.println("Cart for Customer ID: " + customerId);
      for (Map.Entry<Product, Integer> entry : productsList.entrySet()) {
        System.out.println(entry.getKey() + " x " + entry.getValue());
      }
    }
  }

  public static void clearCart() {
    this.productsList.clear();
  }

}