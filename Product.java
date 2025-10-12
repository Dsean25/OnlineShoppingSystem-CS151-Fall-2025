/*
 Class: Product.java

 Purpose:
 - Represents a product available in the online shopping system
 - Stores and updates product details
 - Implements Discountable interface to handle product discounts
*/

import java.time.LocalDate;

public class Product implements Discountable {
  private String productId;
  private String name;
  private String category;
  private int stock;
  private double price;
  private double discountPercent;
  private boolean isAvailable;
  private boolean discountAvailable;
  private String lastUpdated;

  // Constructor
  public Product(String productId, String name, String category, int stock, double price)
      throws InvalidPriceException, InvalidStockException {
    if (price < 0) {
      throw new InvalidPriceException("Price cannot be negative.");
    }
    if (stock < 0) {
      throw new InvalidStockException("Stock cannot be negative.");
    }

    this.productId = productId;
    this.name = name;
    this.category = category;
    this.stock = stock;
    this.price = price;
    this.discountPercent = 0;
    this.discountAvailable = false;
    this.isAvailable = stock > 0;
    this.lastUpdated = LocalDate.now().toString();
  }

  // Getters and setters
  public double getCurrentPrice() {
    return price * (1 - discountPercent / 100);
  }

  public String getProductId() {
    return productId;
  }

  public String getName() {
    return name;
  }

  public String getCategory() {
    return category;
  }

  public int getStock() {
    return stock;
  }

  public double getPrice() {
    return price;
  }

  public boolean isAvailable() {
    return isAvailable;
  }

  public boolean isDiscountAvailable() {
    return discountAvailable;
  }

  public String getLastUpdated() {
    return lastUpdated;
  }

  public void setPrice(double price) throws InvalidPriceException {
    if (price < 0) {
      throw new InvalidPriceException("Price cannot be negative.");
    }
    this.price = price;
    updateLastUpdated();
  }

  public void setStock(int stock) throws InvalidStockException {
    if (stock < 0) {
      throw new InvalidStockException("Stock cannot be negative.");
    }
    this.stock = stock;
    isAvailable = stock > 0;
    updateLastUpdated();
  }

  // Discount-related methods
  @Override
  public void applyDiscount(double percent) {
    if (percent < 0 || percent > 100) {
      throw new IllegalArgumentException("Discount must be between 0 and 100.");
    }
    discountPercent = percent;
    discountAvailable = true;
    updateLastUpdated();
  }

  @Override
  public void clearDiscount() {
    discountPercent = 0;
    discountAvailable = false;
    updateLastUpdated();
  }

  @Override
  public double getDiscountPercent() {
    return discountPercent;
  }

  // Product-related methods
  public void restock(int amount) throws InvalidStockException {
    if (amount <= 0) {
      throw new InvalidStockException("Restock amount must be positive.");
    }
    stock += amount;
    isAvailable = true;
    updateLastUpdated();
  }

  public void reduceStock(int amount) throws InvalidStockException {
    if (amount <= 0) {
      throw new InvalidStockException("Amount must be positive.");
    }
    if (amount > stock) {
      throw new InvalidStockException("Not enough stock available.");
    }
    stock -= amount;
    isAvailable = stock > 0;
    updateLastUpdated();
  }

  public void deleteProduct() {
    stock = 0;
    isAvailable = false;
    updateLastUpdated();
  }

  private void updateLastUpdated() {
    lastUpdated = LocalDate.now().toString();
  }

  // toString()
  @Override
  public String toString() {
    return name
        + " [ID: "
        + productId
        + ", $"
        + String.format("%.2f", getCurrentPrice())
        + ", Stock: "
        + stock
        + ", Discount: "
        + discountPercent
        + "%]";
  }
}
