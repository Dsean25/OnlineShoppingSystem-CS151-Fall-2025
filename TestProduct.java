/*
 Class: TestProduct.java
 Purpose: Test functionality of Product.java
*/

public class TestProduct {
  public static void main(String[] args) {
    try {
      Product apple = new Product("P001", "Apple", "Fruit", 50, 0.99);
      Product milk = new Product("P002", "Milk", "Dairy", 25, 3.49);

      System.out.println("Initial products:");
      System.out.println(apple);
      System.out.println(milk);

      apple.applyDiscount(10);
      System.out.println("\nAfter applying 10% discount to apple:");
      System.out.println(apple);

      milk.setPrice(3.99);
      System.out.println("\nUpdated milk price:");
      System.out.println(milk);

      apple.reduceStock(5);
      System.out.println("\nReduced apple stock by 5:");
      System.out.println(apple);

      apple.restock(10);
      System.out.println("\nRestocked apple by 10:");
      System.out.println(apple);

      milk.deleteProduct();
      System.out.println("\nDeleted milk (set stock to 0):");
      System.out.println(milk);

    } catch (InvalidPriceException | InvalidStockException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
}
