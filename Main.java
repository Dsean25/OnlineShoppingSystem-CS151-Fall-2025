import java.util.Scanner;
import java.util.ArrayList;

public class Main {

  private static ArrayList<Product> products = new ArrayList<>();
  private static Scanner scanner = new Scanner(System.in);
  private static Seller seller1;

  public static void main(String[] args) {
    boolean exit = false;
    System.out.println("Welcome to the Online Grocery Shop!");

    // Set up a default grocery shop
    initialProductSetup();

    while (!exit) {
      // Main menu
      displaymainMenu();

      // Read user input
      int input = scanner.nextInt();
      scanner.nextLine();
      System.out.println(input);

      switch (input) {
        case 1:
          productMenu();
          break;
        case 2:
          customerMenu();
          break;
        case 3:
          sellerMenu();
          break;
        case 4:
          exit = true;
          break;
      }
    }

    scanner.close();
    return;
  }

  // displaymainMenu() Method:
  // Display the main menu
  private static void displaymainMenu() {
    System.out.println("Main Menu");
    System.out.println("-----------------");
    System.out.println("1. Product Menu");
    System.out.println("2. Customer Menu");
    System.out.println("3. Seller Menu");
    System.out.println("4. Exit");
    System.out.print("Enter a choice (1 - 4): ");
  }

  // productMenu() Method:
  // Display a table of products
  private static void productMenu() {
    System.out.println("Welcome to Product Menu!");
    System.out.printf("%-5s %-25s %-10s %-10s%n", "ID", "Name", "Price($)", "Stock");
    System.out.println("----------------------------------------------------");

    for (Product product : products) {
      System.out.printf(
          "%-5s %-25s %-10.2f %-10d%n",
          product.getProductId(), product.getName(), product.getCurrentPrice(), product.getStock());
    }

    System.out.println("----------------------------------------------------");
    System.out.println("To place an order, ....");
  }

  // customerMenu() Method:
  // Customer can perform actions including ...
  private static void customerMenu() {
    System.out.println("Welcome to Customer Menu!");
    System.out.print("What is your user ID?");
    String customerID = scanner.nextLine();

    // If they haven't created their profile, create one.
    if (customerID == null) {
      System.out.println("Customer does not exist. Please try again.");
      return;
    }

    // Access shopping cart

    // Place an order

    // Go back
  }

  // sellerMenu() Method:
  // Seller can perform actions including:
  // 1) View products
  // 2) Change product price
  // 3) Add product
  // 4) Remove product
  private static void sellerMenu() {
    boolean exit = false;

    System.out.println("Welcome to Seller Menu!");
    System.out.print("What is your seller ID? ");
    String sellerID = scanner.nextLine();
    System.out.println();

    // check valid seller ID before allowing them to make changes
    if (sellerID == null || !sellerID.equals(seller1.getUserID())) {
      System.out.println("Seller does not exist. Please try again.");
      return;
    }

    while (!exit) {
      System.out.println("Welcome to store " + seller1.getStoreName() + "!");
      System.out.println("1. View all my product");
      System.out.println("2. View single product");
      System.out.println("3. Change product price");
      System.out.println("4. Check product discount");
      System.out.println("5. Apply product discount");
      System.out.println("6. Clear product discount");
      System.out.println("7. Add product");
      System.out.println("8. Remove product");
      System.out.println("9. Restock");
      System.out.println("10. Reduce stock");
      System.out.println("11. Exit");
      System.out.print("Enter a choice (1 - 11): ");
      int choice = scanner.nextInt();
      scanner.nextLine();

      switch (choice) {
        case 1: // View products
          seller1.viewProducts();
          break;
        case 2: // View single product
          System.out.print("What is the productID? ");
          String viewSingleProd = scanner.nextLine();
          if (!seller1.hasProduct(viewSingleProd)) {
            System.out.println("Product does not exist. Please try again.");
          } else {
            System.out.println(seller1.getProductById(viewSingleProd));
          }
          break;
        case 3: // Change product price
          System.out.print("What is the productID? ");
          String idPriceChange = scanner.nextLine();
          if (!seller1.hasProduct(idPriceChange)) {
            System.out.println("Product does not exist. Please try again.");
          } else {
            System.out.print("What is the new price? ");
            double newPrice = scanner.nextDouble();
            scanner.nextLine();
            seller1.changeProductPrice(idPriceChange, newPrice);
          }
          break;
        case 4: // Check product discount
          System.out.print("What is the productID? ");
          String idDiscountCheck = scanner.nextLine();
          if (!seller1.hasProduct(idDiscountCheck)) {
            System.out.println("Product does not exist. Please try again.");
          } else {
            Product p = seller1.getProductById(idDiscountCheck);
            System.out.printf(
                "Product: %s (ID: %s) %nCurrent Discount: %.1f%%, Current Price: $%.2f%n",
                p.getName(), p.getProductId(), p.getDiscountPercent(), p.getCurrentPrice());
          }
          break;
        case 5: // Apply product discount
          System.out.print("What is the productID? ");
          String idDiscount = scanner.nextLine();
          if (!seller1.hasProduct(idDiscount)) {
            System.out.println("Product does not exist. Please try again.");
          } else {
            Product p = seller1.getProductById(idDiscount);
            System.out.print("What is the discount %? ");
            double discountPercent = scanner.nextDouble();
            scanner.nextLine();
            p.applyDiscount(discountPercent);
            System.out.printf(
                "Applied %.1f%% discount to %s. New price: $%.2f%n",
                discountPercent, p.getName(), p.getCurrentPrice());
          }
          break;
        case 6: // Clear discount
          System.out.print("What is the productID? ");
          String idClearDiscount = scanner.nextLine();
          if (!seller1.hasProduct(idClearDiscount)) {
            System.out.println("Product does not exist. Please try again.");
          } else {
            Product p = seller1.getProductById(idClearDiscount);
            p.clearDiscount();
            System.out.printf("Discount cleared. New price: $%.2f%n", p.getCurrentPrice());
          }
          break;
        case 7: // Add product
        // Need to add
        case 8: // Remove product
          System.out.print("What is the productID? ");
          String idRemove = scanner.nextLine();
          seller1.removeProduct(idRemove);
          break;
        case 9: // Add stock
          System.out.print("What is the productID? ");
          String idAddStock = scanner.nextLine();
          if (!seller1.hasProduct(idAddStock)) {
            System.out.println("Product does not exist. Please try again.");
          } else {
            Product p = seller1.getProductById(idAddStock);
            System.out.printf(
                "Current stock of %s (ID: %s) is %d. Add how many? ",
                p.getName(), p.getProductId(), p.getStock());
            int newStock = scanner.nextInt();
            scanner.nextLine();
            try {
              p.restock(newStock);
              System.out.printf("New stock is %d.%n", p.getStock());
            } catch (InvalidStockException e) {
              System.out.println(e.getMessage());
            }
          }
          break;
        case 10: // Reduce stock
          System.out.print("What is the productID? ");
          String idReduceStock = scanner.nextLine();
          if (!seller1.hasProduct(idReduceStock)) {
            System.out.println("Product does not exist. Please try again.");
          } else {
            Product p = seller1.getProductById(idReduceStock);
            System.out.printf(
                "Current stock of %s (ID: %s) is %d. Reduce how many? ",
                p.getName(), p.getProductId(), p.getStock());
            int newStock = scanner.nextInt();
            scanner.nextLine();
            try {
              p.reduceStock(newStock);
              System.out.printf("New stock is %d.%n", p.getStock());
            } catch (InvalidStockException e) {
              System.out.println(e.getMessage());
            }
          }
          break;
        case 11: // Exit
          exit = true;
          break;
      }
    }
  }

  private static void initialProductSetup() {
    // Products
    try {
      // Fruits
      Product apple = new Product("P001", "Apple", "Fruit", 50, 0.99);
      Product banana = new Product("P002", "Banana", "Fruit", 50, 0.50);
      Product orange = new Product("P003", "Orange", "Fruit", 50, 0.50);
      Product blueberries = new Product("P004", "Blueberries", "Fruit", 50, 5.99);
      Product grapes = new Product("P005", "Banana", "Fruit", 50, 2.49);

      // Dairy
      Product milk = new Product("P006", "Milk", "Dairy", 25, 3.49);
      Product cheese = new Product("P007", "Cheese", "Dairy", 15, 4.99);
      Product yogurt = new Product("P008", "Yogurt", "Dairy", 30, 1.29);
      Product eggs = new Product("P009", "Eggs", "Dairy", 40, 2.99);

      // Bakery
      Product whiteBread = new Product("P010", "White bread", "Bakery", 20, 2.49);
      Product bagel = new Product("P011", "Bagel", "Bakery", 25, 1.99);
      Product muffin = new Product("P012", "Muffin", "Bakery", 15, 2.29);

      // Beverages
      Product water = new Product("P013", "Bottled Water", "Beverage", 100, 0.99);
      Product appleJuice = new Product("P014", "Apple Juice", "Beverage", 30, 3.49);
      Product coke = new Product("P015", "Coke (can)", "Beverage", 60, 1.19);

      // Snacks
      Product chips = new Product("P016", "Potato Chips", "Snack", 40, 2.79);
      Product chocoCookies = new Product("P017", "Chocolate chip cookies", "Snack", 35, 3.29);
      Product vanillaCookies = new Product("P018", "Vanilla cookies", "Snack", 35, 3.29);
      Product candy = new Product("P019", "Gummy Bears", "Snack", 50, 1.49);
      Product proteinBar = new Product("P020", "Protein bar", "Snack", 35, 3.29);

      // Pantry
      Product rice = new Product("P021", "White rice", "Pantry", 80, 1.09);
      Product pasta = new Product("P022", "Spaghetti pasta", "Pantry", 60, 1.59);
      Product beans = new Product("P023", "Canned Beans", "Pantry", 40, 1.39);

      products.add(apple);
      products.add(banana);
      products.add(orange);
      products.add(blueberries);
      products.add(grapes);
      products.add(milk);
      products.add(cheese);
      products.add(yogurt);
      products.add(eggs);
      products.add(whiteBread);
      products.add(bagel);
      products.add(muffin);
      products.add(water);
      products.add(appleJuice);
      products.add(coke);
      products.add(chips);
      products.add(chocoCookies);
      products.add(vanillaCookies);
      products.add(candy);
      products.add(proteinBar);
      products.add(rice);
      products.add(pasta);
      products.add(beans);

      // Assign products to Seller
      seller1 =
          new Seller(
              "S001", "Bush Nguyen", "111-222-3344", "123 Main St, San Jose, CA", "FreshMart");

      for (Product product : products) {
        seller1.addProduct(product);
      }
    } catch (InvalidPriceException | InvalidStockException e) {
      System.out.println("Error creating product: " + e.getMessage());
    }
  }
}
