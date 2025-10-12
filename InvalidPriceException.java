/*
 Class: InvalidPriceException.java
 Purpose: A custom exception thrown when a product is assigned an invalid price
*/

public class InvalidPriceException extends Exception {
  public InvalidPriceException(String message) {
    super(message);
  }
}
