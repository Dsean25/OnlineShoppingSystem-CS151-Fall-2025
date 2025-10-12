/*
 Interface: Discountable.java
 Purpose: Defines discount-related behaviors
*/

public interface Discountable {
  void applyDiscount(double percent);

  void clearDiscount();

  double getDiscountPercent();
}
