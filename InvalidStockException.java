/*
 Class: InvalidStockException.java
 Purpose: A custom exception thrown when a product is given an invalid stock amount
*/

public class InvalidStockException extends Exception {
  public InvalidStockException(String message) {
    super(message);
  }
}
