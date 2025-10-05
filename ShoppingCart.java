import java.time.LocalDateTime;
import java.util.HashMap;
  
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

  public HashMap<Product, Integer> getproductsList() { 
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

  
  public void calculateTotalInCart() {
    double sum = 0;
    for (HashMap.Entry<Product, Integer> entry : productsList.entrySet()) {
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
      for (HashMap.Entry<Product, Integer> entry : productsList.entrySet()) {
        System.out.println(entry.getKey() + " x " + entry.getValue());
      }
    }
  }

  public void clearCart() {
    this.productsList.clear();
  }

}
