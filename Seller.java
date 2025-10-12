import java.util.HashMap;

public class Seller extends User {

  private static final int MAX_INSTANCES = 100;
  private static int instanceCount = 0;

  private String storeName;
  private HashMap<String, Product> productMap;

  public Seller(String userID, String name, String phoneNumber, String address, String storeName) {
    super(userID, name, phoneNumber, address);

    if (instanceCount >= MAX_INSTANCES) {
      throw new IllegalStateException("Reached max Seller limit " + MAX_INSTANCES);
    }

    this.storeName = storeName;
    this.productMap = new HashMap<>();
    
    instanceCount++;
  }

  public static int getInstanceCount() {
    return instanceCount;
  }

  public String getStoreName() {
    return storeName;
  }

  public void setStoreName(String storeName) {
    this.storeName = storeName;
  }

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
    try {
      p.setPrice(newPrice);
      System.out.println("Price updated for product: " + p.getName());
    } catch (InvalidPriceException e) {
      System.out.println("Error updating price: " + e.getMessage());
    }
  }

  public Product getProductById(String productId) {
    return productMap.get(productId);
  }
}
