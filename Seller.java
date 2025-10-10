import java.util.HashMap;

public class Seller extends User {
  private String storeName;
  private HashMap<String, Product> productMap;

  public Seller(
      String userID,
      String name,
      String phoneNumber,
      String address,
      String storeName,
      String password) {
    super(userID, name, phoneNumber, address, password);
    this.storeName = storeName;
    this.productMap = new HashMap<>();
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

  public void changeProductPrice(String productId, double newPrice) throws InvalidPriceException {
    if (newPrice < 0) {
      throw new InvalidPriceException("The new price must be >= 0");
    }
    if (!hasProduct(productId)) {
      System.out.println("Product with ID " + productId + " is not found in " + storeName);
      return;
    }
    Product p = productMap.get(productId);
    p.setPrice(newPrice);
  }

  @Override
  public HashMap<String, Product> searchProducts(
      String category,
      double minPrice,
      double maxPrice,
      boolean isAvailable,
      boolean discountAvailable) {
    HashMap<String, Product> results = new HashMap<>();

    for (Product product : productMap.values()) {
      if (product.getCategory().equals(category)
          && product.getPrice() >= minPrice
          && product.getPrice() <= maxPrice
          && product.isAvailable() == isAvailable
          && product.isDiscountAvailable() == discountAvailable) {
        results.put(product.getProductId(), product);
      }
    }
    return results;
  }

  @Override
  public Order viewOrder(String orderId) {
    return null;
  }
}
