/*
 Class: ShoppingCart.java

 Purpose:
 - Represents a customer's shopping cart the online shopping system
 - Stores products and their quantities
 - Implements Operations interface for add/remove products
 - Manages cart operations such as viewing, clearing, and calculating estimated cost
 - Extends User class for seller-specific attributes
*/

import java.util.HashMap;

public class ShoppingCart implements Operations {
  private static final int MAX_INSTANCES = 100;
  private static int instanceCount = 0;

  private String customerId;
  private HashMap<Product, Integer> productsList;

  public ShoppingCart(String customerId) {
    this.customerId = customerId;
    productsList = new HashMap<>();

    if (instanceCount >= MAX_INSTANCES) {
      throw new IllegalStateException("Reached max Customer limit " + MAX_INSTANCES);
    }

    instanceCount++;
  }

  // Getters and setters
  public String getcustomerId() {
    return this.customerId;
  }

  public HashMap<Product, Integer> getproductsList() {
    return this.productsList;
  }

  public void setcustomerId(String customerId) {
    this.customerId = customerId;
  }

  public void setproductsList(HashMap<Product, Integer> productsList) {
    this.productsList = productsList;
  }

  // Cart-related methods
  @Override
  public void addProduct(Product p, int quantity) {
    boolean hasproduct = productsList.containsKey(p);
    if (hasproduct) {
      int currQty = productsList.get(p);
      productsList.put(p, currQty + quantity);
      System.out.println(
          "This product is already in cart and the quantity has been update to "
              + productsList.get(p));
    } else {
      productsList.put(p, quantity);
      System.out.println("Product added to cart! Quantity: " + productsList.get(p));
    }
  }

  @Override
  public void removeProduct(Product p, int quantity) {

    if (quantity > productsList.get(p)) {
      System.out.println(
          "The amount you want to remove greater than the amount of this product in cart");
    } else if (productsList.get(p) == quantity) {
      productsList.remove(p);
      System.out.println("Item removed from cart");
    } else {
      productsList.put(p, productsList.get(p) - quantity);
    }
  }

  public void calculateTotal() {
    double sum = 0;
    for (HashMap.Entry<Product, Integer> entry : productsList.entrySet()) {
      Product product = entry.getKey();
      int quantity = entry.getValue();
      sum += product.getCurrentPrice() * quantity;
    }
    System.out.println("Total: $" + sum);
  }

  public void viewProducts() {
    if (productsList.isEmpty()) {
      System.out.println("Your cart is empty.");
    } else {
      System.out.println("Your cart:");
      for (HashMap.Entry<Product, Integer> entry : productsList.entrySet()) {
        System.out.println(entry.getKey() + " x " + entry.getValue());
      }
    }
  }

  public boolean hasProduct(Product p) {
    return productsList.containsKey(p);
  }

  public void clearCart() {
    this.productsList.clear();
  }

  public static int getInstanceCount() {
    return instanceCount;
  }
}
