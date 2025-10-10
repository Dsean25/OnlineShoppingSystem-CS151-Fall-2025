import java.util.HashMap;

public class Seller extends User {
  private String storeName;
  private HashMap<String, Product> productMap;

  public Seller(
      String userID,
      String firstName,
      String lastName,
      String email,
      String phoneNumber,
      String address,
      String storeName) {
    super(userID, firstName, lastName, email, phoneNumber, address);
    this.storeName = storeName;
    this.productMap = new HashMap<>();
  }

  public String getStoreName() {
    return storeName;
  }

  public void setStoreName(String storeName) {
    this.storeName = storeName;
  }

  //add getters and setters from user into seller

  public void viewProducts() {
    for (Product product : productMap.values()) {
      System.out.println(product);
    }
  }

  public boolean hasProduct(String productId) {
    return productMap.containsKey(productId);
  }

  public void addProduct(Product product) {
    productMap.put(product.getProductId(), product);
    System.out.println("Product " + product.getName() + " added to " + storeName);
  }

  public void removeProduct(String productId) {
    if (hasProduct(productId)) {
      Product removedProduct = productMap.remove(productId);
      System.out.println("Product " + removedProduct.getName() + " removed from " + storeName);
    } else {
      System.out.println("Product with ID " + productId + " not found in " + storeName);
    }
  }

  public void changeProductPrice(String productId, double newPrice) {
    if (newPrice < 0) {
      throw new IllegalArgumentException("The new price must be >= 0");
    }
    if (!hasProduct(productId)) {
      System.out.println("Product with ID " + productId + " is not found in " + storeName);
      return;
    }
    Product p = productMap.get(productId);
    p.setPrice(newPrice);
  }
}
