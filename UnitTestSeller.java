import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UnitTestSeller {
  private Seller seller;
  private Product product;

  @BeforeEach
  void SellersetUp() throws InvalidPriceException, InvalidStockException {
    seller =
        new Seller(
            "S001",
            "Matthew Yeh",
            "669-231-9366",
            "917 Tassasara Drive",
            "Matty's Melons",
            "password123");
    product = new Product("P001", "Watermelon", "Fruit", 25, 3.99);
  }

  @Test
  void testSellerGetters() {
    assertEquals("S001", seller.getUserID());
    assertEquals("Matthew Yeh", seller.getName());
    assertEquals("Matty's Melons", seller.getStoreName());
  }

  @Test
  void testAddProduct() {
    seller.addProduct(product);
    assertTrue(seller.getproductMap().containsKey("P001"));
    assertEquals(product, seller.getproductMap().get("P001"));
  }

  @Test
  void testRemoveProduct() {
    seller.addProduct(product);
    seller.removeProduct("P001");
    assertFalse(seller.getproductMap().containsKey("P001"));
  }

  @Test
  void testHasProduct() {
    seller.addProduct(product);
    assertTrue(seller.hasProduct("P001"));
    assertFalse(seller.hasProduct("P002"));
  }

  @Test
  void testChangeProductPrice() throws InvalidPriceException {
    seller.addProduct(product);
    seller.changeProductPrice("P001", 4.99);
    assertEquals(4.99, seller.getproductMap().get("P001").getPrice());
  }
}
