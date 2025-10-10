public interface Discountable {
  void applyDiscount(double percent);

  void clearDiscount();

  double getDiscountPercent();
}
