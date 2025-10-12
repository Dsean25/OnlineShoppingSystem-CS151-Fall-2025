/*
 Class: Seller.java

 Purpose:
 - Represents a seller the online shopping system
 - Manages the store's product and operations
 - Extends User class for seller-specific attributes
*/

import java.util.HashMap;

public class Seller extends User {
  private static final int MAX_INSTANCES = 100;
  private static int instanceCount = 0;
  private String storeName;
  private HashMap<String, Product> productMap;

  // Constructor
  public Seller(
      String userID,
      String name,
      String phoneNumber,
      String address,
      String storeName,
      String password) {
    super(userID, name, phoneNumber, address, password);

    if (instanceCount >= MAX_INSTANCES) {
      throw new IllegalStateException("Reached max Seller limit " + MAX_INSTANCES);
    }

    this.storeName = storeName;
    this.productMap = new HashMap<>();

    instanceCount++;
  }

  // Getters and setters
  public static int getMaxInstances() {
    return MAX_INSTANCES;
  }

  public static int getInstanceCount() {
    return instanceCount;
  }

  public String getStoreName() {
    return this.storeName;
  }

  public HashMap<String, Product> getproductMap() {
    return this.productMap;
  }

  public void setStoreName(String storeName) {
    this.storeName = storeName;
  }

  public void setproductMap(HashMap<String, Product> productMap) {
    this.productMap = productMap;
  }

  // Product-related methods
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

  @Override
  public HashMap<String, Product> searchProducts(
      String category,
      double minPrice,
      double maxPrice,
      boolean isAvailable,
      boolean discountAvailable) {
    HashMap<String, Product> results = new HashMap<>();
    for (Product product : productMap.values()) {
      if (category != null && !category.equalsIgnoreCase(product.getCategory())) {
        continue;
      }
      if (product.getPrice() < minPrice || product.getPrice() > maxPrice) {
        continue;
      }
      if (isAvailable && !product.isAvailable()) {
        continue;
      }
      if (discountAvailable && !product.isDiscountAvailable()) {
        continue;
      }
      results.put(product.getProductId(), product);
    }
    return results;
  }
}
