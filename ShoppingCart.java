import java.time.LocalDateTime;
import java.util.HashMap;
  public class ShoppingCart implements Operations { // only want to be able to make one instance per customer

  private String customerId;
  private HashMap<Product, Integer> productsList;

  public ShoppingCart(String customerId) {
    this.customerId = customerId;
    productsList = new HashMap<>();
  }

  //getters
  public String getcustomerId() {
    return customerId;
  }

  public HashMap<Product, Integer> getproductsList() { 
    return productsList;

  }

  //setters
  public void setcustomerId(String customerId) {
    this.customerId = customerId;
  }

  public void setproductsList(HashMap<Product, Integer> productsList) {
    this.productsList = productsList;
  }

  /*
   questions: 
   - how do i set products except adding and subtracting
   - do getters and setters and methods have to be static
    */

  @Override
  public void addProduct(Product p, int quantity){
    boolean hasproduct = productsList.containsKey(p);
    if(hasproduct){
      int currQty = productsList.get(p);
      productsList.put(p, currQty + quantity);
      System.out.println("This product is already in cart and the quantity has been update to " + productsList.get(p));
    }
    else{
      productsList.put(p, quantity);
      System.out.println("Product added to cart! Quantity: " + productsList.get(p));
    }

  }
  @Override
  public void removeProduct(Product p, int quantity){
    //should we add smt for when product is not in cart? or is the user simply selecting  based off what they see
    
    if (quantity > productsList.get(p)){
      //throw exception?
      System.out.println("The amount you want to remove greater than the amount of this product in cart");
    }
    else if (productsList.get(p) == quantity){
      productsList.remove(p);
      System.out.println("Item removed from cart");
    }
    else{
      productsList.put(p, productsList.get(p) - quantity);
    }
  }
  @Override
  public void calculateTotal() {
    double sum = 0;
    for (HashMap.Entry<Product, Integer> entry : productsList.entrySet()) {
      Product product = entry.getKey();
      int quantity = entry.getValue();
      sum += product.getPrice() * quantity;
    }
    System.out.println("Total: $" + sum);
  }

  public void viewProducts() {
    if (productsList.isEmpty()) {
      System.out.println("Your cart is empty.");
    } else {
      System.out.println("Cart for Customer ID: " + customerId);
      for (HashMap.Entry<Product, Integer> entry : productsList.entrySet()) {
        System.out.println(entry.getKey() + " x " + entry.getValue());
      }
    }
  }

  public void clearCart() {
    this.productsList.clear();
  }

}
