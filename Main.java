/*
Class: Main.java
Purpose:
- Provides a UI for the online shopping system
- Provides console menus for product, customer, and seller via Scanner
- Initializes sample data
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

  private static final ArrayList<Product> products = new ArrayList<>();
  private static Scanner scanner = new Scanner(System.in);
  private static HashMap<String, Seller> sellers = new HashMap<>();
  private static Seller seller2;
  private static HashMap<String, Customer> customers = new HashMap<>();

  public static void main(String[] args) {
    boolean exit = false;
    System.out.println("Welcome to the Online Grocery Shop!");

    // Set up a default grocery shop
    initialSetup();

    while (!exit) {
      // Main menu
      displaymainMenu();

      // Read user input to proceed
      int input = scanner.nextInt();
      scanner.nextLine();

      switch (input) {
        case 1: // Product menu
          System.out.println("Welcome to Product Menu!");
          productMenu();
          break;
        case 2: // Customer menu
          customerMenu();
          break;
        case 3: // Seller menu
          sellerMenu();
          break;
        case 4: // Exit
          exit = true;
          break;
      }
    }

    scanner.close();
    return;
  }

  // displaymainMenu():
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

  // productMenu():
  // Display a table of products
  private static void productMenu() {
    System.out.printf("%-5s %-25s %-10s %-10s%n", "ID", "Name", "Price($)", "Stock");
    System.out.println("----------------------------------------------------");

    for (Product product : products) {
      System.out.printf(
          "%-5s %-25s %-10.2f %-10d%n",
          product.getProductId(), product.getName(), product.getCurrentPrice(), product.getStock());
    }

    System.out.println("----------------------------------------------------");
  }

  // customerMenu():
  // Display a list of customer actions
  private static void customerMenu() {
    boolean exit = false;
    System.out.println("Welcome to Customer Menu!");
    System.out.print("What is your user ID? ");
    String customerID = scanner.nextLine();

    // Allow customer to create new profile, if they don't have an existing profile
    while (customerID == null || !customers.containsKey(customerID)) {
      System.out.print("Customer does not exist. Do you have an account? (y/n) : ");
      String accountExists = scanner.nextLine().toLowerCase();
      if (accountExists.startsWith("y")) {
        System.out.print("Enter your user ID again: ");
        customerID = scanner.nextLine();
        continue;
      } else {
        System.out.println("Please sign up by entering the following:");

        System.out.print("Choose a user ID: ");
        customerID = scanner.nextLine();
        while (customerID.isBlank() || customers.containsKey(customerID)) {
          System.out.print("That user ID is invalid or already taken. Choose another: ");
          customerID = scanner.nextLine();
        }

        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Address: ");
        String address = scanner.nextLine();
        System.out.print("Payment method (16-digit credit card number): ");
        String paymentMethod = scanner.nextLine();

        Customer customer = new Customer(customerID, name, phoneNumber, address, "placeholder");
        customer.setPaymentMethod(paymentMethod);

        System.out.print("Almost there! Now, make a password with at least 10 characters: ");
        String password = scanner.nextLine();

        while (!customer.PasswordStrengthTest(password)) {
          System.out.print("Try again: ");
          password = scanner.nextLine();
        }
        customer.setPassword(password);
        customers.put(customerID, customer);

        System.out.println(
            "Account created! Your user ID is "
                + customerID
                + " and your current password is "
                + password
                + ". Please log in.");
      }
    }

    // Perform login
    System.out.print("Enter your password: ");
    String password = scanner.nextLine();

    if (customers.get(customerID).login(customerID, password)) {
      System.out.println("Login successful!");
    } else {
      System.out.println("Login failed. Please try again.");
      return;
    }

    // Get customer information before allowing them to perform actions
    Customer customer = customers.get(customerID);

    while (!exit) {
      // Display customer menu
      System.out.println("Welcome, " + customer.getName() + "!");
      System.out.println("1. View products");
      System.out.println("2. View shopping cart");
      System.out.println("3. Add to cart");
      System.out.println("4. Remove from cart");
      System.out.println("5. View payment method");
      System.out.println("6. Update payment method");
      System.out.println("7. Place an order");
      System.out.println("8. Confirm order delivery");
      System.out.println("9. View my orders");
      System.out.println("10. Cancel an order");
      System.out.println("11. Create a return");
      System.out.println("12. Complete a return");
      System.out.println("13. Check refund status");
      System.out.println("14. Logout");
      System.out.println("15. Exit");
      System.out.print("Enter a choice (1 - 15): ");
      // Scan user choice input
      int choice = scanner.nextInt();
      scanner.nextLine();

      switch (choice) {
        case 1: // View products
          System.out.println("Here are the list of products: ");
          productMenu();
          break;
        case 2: // View shopping cart
          // Return shopping cart
          customer.getCart().viewProducts();
          // Display estimated total cost
          customer.getCart().calculateTotal();
          break;
        case 3: // Add to cart
          // Get product ID
          System.out.print("What is the product ID? ");
          String productAdd = scanner.nextLine();
          Product pAdd = seller2.getProductById(productAdd);

          // Get quantity
          System.out.print("What is the quantity? ");
          int addQty = scanner.nextInt();
          scanner.nextLine();

          // Add Product to cart
          customer.addProduct(pAdd, addQty);
          break;
        case 4: // Remove from cart
          // Get product ID
          System.out.print("What is the product ID? ");
          String productRemove = scanner.nextLine();
          Product pRemove = seller2.getProductById(productRemove);

          // Get quantity
          System.out.print("What is the quantity? ");
          int removeQty = scanner.nextInt();
          scanner.nextLine();

          // Remove Product from cart
          customer.removeProduct(pRemove, removeQty);
          break;
        case 5: // View payment method
          String cardNo = customer.getPaymentMethod();
          if (cardNo == null) {
            System.out.println("No credit card on file.");
            break;
          }
          String last4 = cardNo.substring(cardNo.length() - 4);
          System.out.printf("Current credit card number: **** **** **** %s%n", last4);
          break;
        case 6: // Update payment method
          String currentCardNo = customer.getPaymentMethod();
          if (currentCardNo == null) {
            System.out.println("No credit card on file.");
          } else {
            String lastFour = currentCardNo.substring(currentCardNo.length() - 4);
            System.out.printf("Current credit card number: **** **** **** %s%n", lastFour);
          }
          System.out.print("What is your new credit card number? ");
          String newCardNo = scanner.nextLine();
          while (!newCardNo.matches("\\d{16}")) {
            System.out.print("Must be 16 digit long and digits only. Enter card number again: ");
            newCardNo = scanner.nextLine();
          }
          customer.changePaymentMethod(newCardNo);
          break;
        case 7: // Place an order
          customer.placeOrder();
          break;
        case 8: // Confirm order delivery
          System.out.print("What is the order ID? ");
          String orderID = scanner.nextLine();
          customer.confirmDelivery(orderID);
          break;
        case 9: // View my orders
          System.out.println("Your order history:");
          customer.getOrders();
          break;
        case 10: // Cancel an order
          System.out.print("What is the order ID? ");
          String cancelOrderID = scanner.nextLine();
          customer.cancelOrder(cancelOrderID);
          break;
        case 11: // Create a return
          System.out.print("What is the order ID? ");
          String returnOrderID = scanner.nextLine();
          customer.returnOrder(returnOrderID);
          break;
        case 12: // Complete a return
          System.out.print("What is the order ID? ");
          String completeReturnID = scanner.nextLine();
          customer.completeReturn(completeReturnID);
          break;
        case 13: // Check refund status
          System.out.print("What is the order ID? ");
          String refundID = scanner.nextLine();
          customer.checkRefund(refundID);
          break;
        case 14: // Logout
          customer.logout();
          exit = true;
          break;
        case 15: // Exit
          exit = true;
          break;
      }
    }
  }

  // sellerMenu():
  // Display a list of seller actions
  private static void sellerMenu() {
    boolean exit = false;

    System.out.println("Welcome to Seller Menu!");
    System.out.print("What is your seller ID? ");
    String sellerID = scanner.nextLine();

    // Allow seller to create new profile, if they don't have an existing profile
    while (sellerID == null || !sellers.containsKey(sellerID)) {
      System.out.print("Customer does not exist. Do you have an account? (y/n) : ");
      String sellerAccountExists = scanner.nextLine().toLowerCase();
      if (sellerAccountExists.startsWith("y")) {
        System.out.print("Enter your user ID again: ");
        sellerID = scanner.nextLine();
        continue;
      } else {
        System.out.println("Please sign up by entering the following:");

        System.out.print("Choose a user ID: ");
        sellerID = scanner.nextLine();
        while (sellerID.isBlank() || sellers.containsKey(sellerID)) {
          System.out.print("That user ID is invalid or already taken. Choose another: ");
          sellerID = scanner.nextLine();
        }

        System.out.print("Name: ");
        String sellerName = scanner.nextLine();
        System.out.print("Phone number: ");
        String sellerPhoneNumber = scanner.nextLine();
        System.out.print("Address: ");
        String sellerAddress = scanner.nextLine();
        System.out.print("Store Name: ");
        String storeName = scanner.nextLine();
        Seller seller =
            new Seller(
                sellerID, sellerName, sellerPhoneNumber, sellerAddress, storeName, "placeholder");

        System.out.print("Almost there! Now, make a password with at least 10 characters: ");
        String sellerPassword = scanner.nextLine();

        while (!seller.PasswordStrengthTest(sellerPassword)) {
          System.out.print("Try again: ");
          sellerPassword = scanner.nextLine();
        }

        seller.setPassword(sellerPassword);

        sellers.put(sellerID, seller);

        System.out.println(
            "Account created! Your user ID is "
                + sellerID
                + " and your current password is "
                + sellerPassword
                + ". Please log in.");
      }
    }

    // Perform login
    System.out.print("Enter your password: ");
    String sellerPassword = scanner.nextLine();

    if (sellers.get(sellerID).login(sellerID, sellerPassword)) {
      System.out.println("Login successful!");
    } else {
      System.out.println("Login failed. Please try again.");
      return;
    }

    while (!exit) {
      // Display seller menu
      System.out.println("Welcome to store " + sellers.get(sellerID).getStoreName() + "!");
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
      System.out.println("12. Logout");
      System.out.println("13. Exit");
      System.out.print("Enter a choice (1 - 13): ");
      int choice = scanner.nextInt();
      scanner.nextLine();

      switch (choice) {
        case 1: // View products
          sellers.get(sellerID).viewProducts();
          break;
        case 2: // View single product
          System.out.print("What is the productID? ");
          String viewSingleProd = scanner.nextLine();
          if (!sellers.get(sellerID).hasProduct(viewSingleProd)) {
            System.out.println("Product does not exist. Please try again.");
          } else {
            System.out.println(sellers.get(sellerID).getProductById(viewSingleProd));
          }
          break;
        case 3: // Change product price
          System.out.print("What is the productID? ");
          String idPriceChange = scanner.nextLine();
          if (!sellers.get(sellerID).hasProduct(idPriceChange)) {
            System.out.println("Product does not exist. Please try again.");
          } else {
            System.out.print("What is the new price? ");
            double newPrice = scanner.nextDouble();
            scanner.nextLine();
            try {
              sellers.get(sellerID).changeProductPrice(idPriceChange, newPrice);
            } catch (InvalidPriceException e) {
              System.out.println("Error changing product price: " + e.getMessage());
            }
          }
          break;
        case 4: // Check product discount
          System.out.print("What is the productID? ");
          String idDiscountCheck = scanner.nextLine();
          if (!sellers.get(sellerID).hasProduct(idDiscountCheck)) {
            System.out.println("Product does not exist. Please try again.");
          } else {
            Product p = sellers.get(sellerID).getProductById(idDiscountCheck);
            System.out.printf(
                "Product: %s (ID: %s) %nCurrent Discount: %.1f%%, Current Price: $%.2f%n",
                p.getName(), p.getProductId(), p.getDiscountPercent(), p.getCurrentPrice());
          }
          break;
        case 5: // Apply product discount
          System.out.print("What is the productID? ");
          String idDiscount = scanner.nextLine();
          if (!sellers.get(sellerID).hasProduct(idDiscount)) {
            System.out.println("Product does not exist. Please try again.");
          } else {
            Product p = sellers.get(sellerID).getProductById(idDiscount);
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
          if (!sellers.get(sellerID).hasProduct(idClearDiscount)) {
            System.out.println("Product does not exist. Please try again.");
          } else {
            Product p = sellers.get(sellerID).getProductById(idClearDiscount);
            p.clearDiscount();
            System.out.printf("Discount cleared. New price: $%.2f%n", p.getCurrentPrice());
          }
          break;
        case 7: // Add product
          System.out.print("What is the productID? ");
          String idAdd = scanner.nextLine();
          if (sellers.get(sellerID).hasProduct(idAdd)) {
            System.out.println("Product already exists. Please try again.");
            break;
          }
          System.out.print("What is the product name? ");
          String nameAdd = scanner.nextLine();
          System.out.print("What is the product category? ");
          String categoryAdd = scanner.nextLine();
          System.out.print("What is the initial stock? ");
          int stockAdd = scanner.nextInt();
          scanner.nextLine();
          System.out.print("What is the product price? ");
          double priceAdd = scanner.nextDouble();
          scanner.nextLine();
          try {
            Product newProduct = new Product(idAdd, nameAdd, categoryAdd, stockAdd, priceAdd);
            sellers.get(sellerID).addProduct(newProduct);
            products.add(newProduct);
            System.out.println(
                "Product "
                    + newProduct.getName()
                    + " added to "
                    + sellers.get(sellerID).getStoreName());
          } catch (InvalidPriceException | InvalidStockException e) {
            System.out.println("Error adding product: " + e.getMessage());
          }
          break;
        case 8: // Remove product
          System.out.print("What is the productID? ");
          String idRemove = scanner.nextLine();
          sellers.get(sellerID).removeProduct(idRemove);
          products.removeIf(p -> p.getProductId().equals(idRemove));
          break;
        case 9: // Add stock
          System.out.print("What is the productID? ");
          String idAddStock = scanner.nextLine();
          if (!sellers.get(sellerID).hasProduct(idAddStock)) {
            System.out.println("Product does not exist. Please try again.");
          } else {
            Product p = sellers.get(sellerID).getProductById(idAddStock);
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
          if (!sellers.get(sellerID).hasProduct(idReduceStock)) {
            System.out.println("Product does not exist. Please try again.");
          } else {
            Product p = sellers.get(sellerID).getProductById(idReduceStock);
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
        case 12: // Logout
          sellers.get(sellerID).logout();
          exit = true;
          break;
        case 13: // Exit
          exit = true;
          break;
      }
    }
  }

  private static void initialSetup() {

    // Set up Products
    try {
      // Fruits
      Product apple = new Product("P001", "Apple", "Fruit", 50, 0.99);
      Product banana = new Product("P002", "Banana", "Fruit", 50, 0.50);
      Product orange = new Product("P003", "Orange", "Fruit", 50, 0.50);
      Product blueberries = new Product("P004", "Blueberries", "Fruit", 50, 5.99);
      Product grapes = new Product("P005", "Grapes", "Fruit", 50, 2.49);

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

      // Assign Products to Seller
      Seller seller1 =
          new Seller(
              "S001",
              "Bush Nguyen ",
              "111-222-3344",
              "1 Washington Sq, San Jose, CA",
              "FreshMart",
              "password123");
      String sellerID = seller1.getUserID();
      sellers.put(sellerID, seller1);

      for (Product product : products) {
        sellers.get(sellerID).addProduct(product);
      }

      // Set up default customers
      Customer c1 =
          new Customer(
              "C001", "Toey Lui", "406-123-1111", "1 Washington Sq, San Jose, CA", "password123");
      Customer c2 =
          new Customer(
              "C002",
              "Sweksha Shaw",
              "406-123-2222",
              "1 Washington Sq, San Jose, CA",
              "password123");
      Customer c3 =
          new Customer(
              "C003",
              "Matthew Yeh",
              "406-123-3333",
              "1 Washington Sq, San Jose, CA",
              "password123");

      customers.put(c1.getUserID(), c1);
      customers.put(c2.getUserID(), c2);
      customers.put(c3.getUserID(), c3);

    } catch (InvalidPriceException | InvalidStockException e) {
      System.out.println("Error creating product: " + e.getMessage());
    }
  }
}
