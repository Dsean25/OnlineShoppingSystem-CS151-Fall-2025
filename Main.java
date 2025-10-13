/*
Class: Main.java
Purpose:
- Provides a UI for the online shopping system
- Provides console menus for product, customer, and seller via Scanner
- Initializes sample data
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

  public static final ArrayList<Product> products = new ArrayList<>();
  private static Scanner scanner = new Scanner(System.in);
  private static HashMap<String, Seller> sellers = new HashMap<>();
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
      int input = -1;
      try {
        input = scanner.nextInt();
        scanner.nextLine();
      } catch (InputMismatchException e) {
        exit();
      }

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
        default:
          System.out.println("Invalid choice. Try again.");
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
    checkExit(customerID);

    // Allow customer to create new profile, if they don't have an existing profile
    while (customerID == null || !customers.containsKey(customerID)) {
      System.out.print("Customer does not exist. Do you have an account? (y/n) : ");
      String accountExists = scanner.nextLine().toLowerCase();
      checkExit(accountExists);
      if (accountExists.startsWith("y")) {
        System.out.print("Enter your user ID again: ");
        customerID = scanner.nextLine();
        checkExit(customerID);
        continue;
      } else {
        System.out.println("Please sign up by entering the following:");

        System.out.print("Choose a user ID: ");
        customerID = scanner.nextLine();
        checkExit(customerID);
        while (customerID.isBlank() || customers.containsKey(customerID)) {
          System.out.print("That user ID is invalid or already taken. Choose another: ");
          customerID = scanner.nextLine();
          checkExit(customerID);
        }

        System.out.print("Name: ");
        String name = scanner.nextLine();
        checkExit(name);
        System.out.print("Phone number: ");
        String phoneNumber = scanner.nextLine();
        checkExit(phoneNumber);
        System.out.print("Address: ");
        String address = scanner.nextLine();
        checkExit(address);
        System.out.print("Payment method (16-digit credit card number): ");
        String paymentMethod = scanner.nextLine();
        checkExit(paymentMethod);
        while (!paymentMethod.matches("\\d{16}")) {
          System.out.print("Must be 16 digit long and digits only. Enter card number again: ");
          paymentMethod = scanner.nextLine();
          checkExit(paymentMethod);
        }

        Customer customer = new Customer(customerID, name, phoneNumber, address, "placeholder");
        customer.setPaymentMethod(paymentMethod);

        System.out.print("Almost there! Now, make a password with at least 10 characters: ");
        String password = scanner.nextLine();
        checkExit(password);

        while (!customer.PasswordStrengthTest(password)) {
          System.out.print("Try again: ");
          password = scanner.nextLine();
          checkExit(password);
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
    checkExit(password);

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
      System.out.println("5. Clear cart");
      System.out.println("6. View payment method");
      System.out.println("7. Update payment method");
      System.out.println("8. Place an order");
      System.out.println("9. Confirm order delivery");
      System.out.println("10. View my orders");
      System.out.println("11. Cancel an order");
      System.out.println("12. Create a return");
      System.out.println("13. Complete a return");
      System.out.println("14. Check refund status");
      System.out.println("15. Search products");
      System.out.println("16. Logout");
      System.out.println("17. Exit");
      System.out.print("Enter a choice (1 - 17): ");
      // Scan user choice input
      int choice = -1;
      try {
        choice = scanner.nextInt();
        scanner.nextLine();
      } catch (InputMismatchException e) {
        exit();
      }
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
          checkExit(productAdd);
          Product pAdd = sellers.get("S001").getProductById(productAdd);

          // Get quantity
          System.out.print("What is the quantity? ");
          int addQty = -1;
          try {
            addQty = scanner.nextInt();
            scanner.nextLine();
          } catch (InputMismatchException e) {
            exit();
          }

          if (addQty <= 0) {
            System.out.println("Quantity must be greater than 0.");
            break;
          }

          int availableStock = pAdd.getStock();
          if (addQty > availableStock) {
            System.out.printf(
                "Only %d %s are available. Please enter a lower quantity.%n",
                availableStock, pAdd.getName());
          }

          // Add Product to cart
          customer.addProduct(pAdd, addQty);
          break;
        case 4: // Remove from cart
          // Get product ID
          System.out.print("What is the product ID? ");
          String productRemove = scanner.nextLine();
          checkExit(password);
          Product pRemove = sellers.get("S001").getProductById(productRemove);

          // Get quantity
          System.out.print("What is the quantity? ");
          int removeQty = -1;
          try {
            removeQty = scanner.nextInt();
            scanner.nextLine();
          } catch (InputMismatchException e) {
            exit();
          }

          // Remove Product from cart
          customer.removeProduct(pRemove, removeQty);
          System.out.printf(
              "%d %s has been removed from cart successfully.%n", removeQty, pRemove.getName());
          break;
        case 5: // Clear cart
          customer.getCart().clearCart();
          System.out.println("Cart has been cleared successfully.");
          break;
        case 6: // View payment method
          String cardNo = customer.getPaymentMethod();
          if (cardNo == null) {
            System.out.println("No credit card on file.");
            break;
          }
          String last4 = cardNo.substring(cardNo.length() - 4);
          System.out.printf("Current credit card number: **** **** **** %s%n", last4);
          break;
        case 7: // Update payment method
          String currentCardNo = customer.getPaymentMethod();
          if (currentCardNo == null) {
            System.out.println("No credit card on file.");
          } else {
            String lastFour = currentCardNo.substring(currentCardNo.length() - 4);
            System.out.printf("Current credit card number: **** **** **** %s%n", lastFour);
          }
          System.out.print("What is your new credit card number? ");
          String newCardNo = scanner.nextLine();
          checkExit(newCardNo);
          while (!newCardNo.matches("\\d{16}")) {
            System.out.print("Must be 16 digit long and digits only. Enter card number again: ");
            newCardNo = scanner.nextLine();
            checkExit(newCardNo);
          }
          customer.changePaymentMethod(newCardNo);
          break;
        case 8: // Place an order
          customer.placeOrder();
          break;
        case 9: // Confirm order delivery
          System.out.print("What is the order ID? (Include#): ");
          String orderID = scanner.nextLine();
          checkExit(orderID);
          customer.confirmDelivery(orderID);
          break;
        case 10: // View my orders
          System.out.println("Your order history:");
          customer.displayOrders();
          break;
        case 11: // Cancel an order
          System.out.print("What is the order ID? (Include#): ");
          String cancelOrderID = scanner.nextLine();
          checkExit(cancelOrderID);
          customer.cancelOrder(cancelOrderID);
          break;
        case 12: // Create a return
          System.out.print("What is the order ID? (Include#): ");
          String returnOrderID = scanner.nextLine();
          checkExit(returnOrderID);
          customer.returnOrder(returnOrderID);
          break;
        case 13: // Complete a return
          System.out.print("What is the order ID? (Include#): ");
          String completeReturnID = scanner.nextLine();
          checkExit(completeReturnID);
          customer.completeReturn(completeReturnID);
          break;
        case 14: // Check refund status
          System.out.print("What is the order ID? (Include#): ");
          String refundID = scanner.nextLine();
          checkExit(refundID);
          customer.checkRefund(refundID);
          break;
        case 15:
          System.out.print("Enter category to search (or leave blank for all): ");
          String category = scanner.nextLine();
          if (category.isBlank()) category = null;

          System.out.print("Enter minimum price: ");
          double minPrice = -1;
          try {
            minPrice = scanner.nextDouble();
          } catch (InputMismatchException e) {
            exit();
          }
          System.out.print("Enter maximum price: ");
          double maxPrice = -1;
          try {
            maxPrice = scanner.nextDouble();
            scanner.nextLine();
          } catch (InputMismatchException e) {
            exit();
          }

          System.out.print("Only show available products? (yes/no): ");
          boolean isAvailable = scanner.nextLine().equalsIgnoreCase("yes");

          System.out.print("Only show products with discount? (yes/no): ");
          boolean discountAvailable = scanner.nextLine().equalsIgnoreCase("yes");

          HashMap<String, Product> results =
              customer.searchProducts(category, minPrice, maxPrice, isAvailable, discountAvailable);
          if (results.isEmpty()) {
            System.out.println("No products match your search criteria.");
          } else {
            System.out.printf(
                "%-5s %-25s %-10s %-10s %-10s%n", "ID", "Name", "Price($)", "Stock", "Discount");
            System.out.println("---------------------------------------------------------------");
            for (Product p : results.values()) {
              System.out.printf(
                  "%-5s %-25s %-10.2f %-10d %-10.1f%%%n",
                  p.getProductId(),
                  p.getName(),
                  p.getCurrentPrice(),
                  p.getStock(),
                  p.getDiscountPercent());
            }
          }
          break;
        case 16: // Logout
          customer.logout();
          exit = true;
          break;
        case 17: // Exit
          exit = true;
          break;
        default:
          System.out.println("Invalid choice. Try again.");
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
    checkExit(sellerID);

    // Allow seller to create new profile, if they don't have an existing profile
    while (sellerID == null || !sellers.containsKey(sellerID)) {
      System.out.print("Customer does not exist. Do you have an account? (y/n) : ");
      String sellerAccountExists = scanner.nextLine().toLowerCase();
      checkExit(sellerAccountExists);
      if (sellerAccountExists.startsWith("y")) {
        System.out.print("Enter your user ID again: ");
        sellerID = scanner.nextLine();
        checkExit(sellerID);
        continue;
      } else {
        System.out.println("Please sign up by entering the following:");

        System.out.print("Choose a user ID: ");
        sellerID = scanner.nextLine();
        checkExit(sellerID);
        while (sellerID.isBlank() || sellers.containsKey(sellerID)) {
          System.out.print("That user ID is invalid or already taken. Choose another: ");
          sellerID = scanner.nextLine();
          checkExit(sellerID);
        }

        System.out.print("Name: ");
        String sellerName = scanner.nextLine();
        checkExit(sellerName);
        System.out.print("Phone number: ");
        String sellerPhoneNumber = scanner.nextLine();
        checkExit(sellerPhoneNumber);
        System.out.print("Address: ");
        String sellerAddress = scanner.nextLine();
        checkExit(sellerAddress);
        System.out.print("Store Name: ");
        String storeName = scanner.nextLine();
        checkExit(storeName);
        Seller seller =
            new Seller(
                sellerID, sellerName, sellerPhoneNumber, sellerAddress, storeName, "placeholder");

        System.out.print("Almost there! Now, make a password with at least 10 characters: ");
        String sellerPassword = scanner.nextLine();
        checkExit(sellerPassword);

        while (!seller.PasswordStrengthTest(sellerPassword)) {
          System.out.print("Try again: ");
          sellerPassword = scanner.nextLine();
          checkExit(sellerPassword);
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
    checkExit(sellerPassword);

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
      System.out.println("11. Search Inventory");
      System.out.println("12. Logout");
      System.out.println("13. Exit");
      System.out.print("Enter a choice (1 - 13): ");
      int choice = -1;
      try {
        choice = scanner.nextInt();
        scanner.nextLine();
      } catch (InputMismatchException e) {
        exit();
      }

      switch (choice) {
        case 1: // View products
          sellers.get(sellerID).viewProducts();
          break;
        case 2: // View single product
          System.out.print("What is the productID? ");
          String viewSingleProd = scanner.nextLine();
          checkExit(viewSingleProd);
          if (!sellers.get(sellerID).hasProduct(viewSingleProd)) {
            System.out.println("Product does not exist. Please try again.");
          } else {
            System.out.println(sellers.get(sellerID).getProductById(viewSingleProd));
          }
          break;
        case 3: // Change product price
          System.out.print("What is the productID? ");
          String idPriceChange = scanner.nextLine();
          checkExit(idPriceChange);
          if (!sellers.get(sellerID).hasProduct(idPriceChange)) {
            System.out.println("Product does not exist. Please try again.");
          } else {
            System.out.print("What is the new price? ");
            double newPrice = -1.0;
            try {
              newPrice = scanner.nextDouble();
              scanner.nextLine();
            } catch (InputMismatchException e) {
              exit();
            }
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
          checkExit(idDiscountCheck);
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
          checkExit(idDiscount);
          if (!sellers.get(sellerID).hasProduct(idDiscount)) {
            System.out.println("Product does not exist. Please try again.");
          } else {
            Product p = sellers.get(sellerID).getProductById(idDiscount);
            System.out.print("What is the discount %? ");
            double discountPercent = -1.0;
            try {
              discountPercent = scanner.nextDouble();
              scanner.nextLine();
            } catch (InputMismatchException e) {
              exit();
            }
            p.applyDiscount(discountPercent);
            System.out.printf(
                "Applied %.1f%% discount to %s. New price: $%.2f%n",
                discountPercent, p.getName(), p.getCurrentPrice());
          }
          break;
        case 6: // Clear discount
          System.out.print("What is the productID? ");
          String idClearDiscount = scanner.nextLine();
          checkExit(idClearDiscount);
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
          checkExit(idAdd);
          if (sellers.get(sellerID).hasProduct(idAdd)) {
            System.out.println("Product already exists. Please try again.");
            break;
          }
          System.out.print("What is the product name? ");
          String nameAdd = scanner.nextLine();
          checkExit(nameAdd);
          System.out.print("What is the product category? ");
          String categoryAdd = scanner.nextLine();
          checkExit(categoryAdd);
          System.out.print("What is the initial stock? ");
          int stockAdd = -1;
          try {
            stockAdd = scanner.nextInt();
            scanner.nextLine();
          } catch (InputMismatchException e) {
            exit();
          }
          System.out.print("What is the product price? ");
          double priceAdd = -1.0;
          try {
            priceAdd = scanner.nextDouble();
            // scanner.nextLine();
          } catch (InputMismatchException e) {
            exit();
          }
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
          checkExit(idRemove);
          sellers.get(sellerID).removeProduct(idRemove);
          products.removeIf(p -> p.getProductId().equals(idRemove));
          break;
        case 9: // Add stock
          System.out.print("What is the productID? ");
          String idAddStock = scanner.nextLine();
          checkExit(idAddStock);
          if (!sellers.get(sellerID).hasProduct(idAddStock)) {
            System.out.println("Product does not exist. Please try again.");
          } else {
            Product p = sellers.get(sellerID).getProductById(idAddStock);
            System.out.printf(
                "Current stock of %s (ID: %s) is %d. Add how many? ",
                p.getName(), p.getProductId(), p.getStock());
            int newStock = -1;
            try {
              newStock = scanner.nextInt();
              scanner.nextLine();
            } catch (InputMismatchException e) {
              exit();
            }
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
          checkExit(idReduceStock);
          if (!sellers.get(sellerID).hasProduct(idReduceStock)) {
            System.out.println("Product does not exist. Please try again.");
          } else {
            Product p = sellers.get(sellerID).getProductById(idReduceStock);
            System.out.printf(
                "Current stock of %s (ID: %s) is %d. Reduce how many? ",
                p.getName(), p.getProductId(), p.getStock());
            int newStock = -1;
            try {
              newStock = scanner.nextInt();
            } catch (InputMismatchException e) {
              exit();
            }
            scanner.nextLine();
            try {
              p.reduceStock(newStock);
              System.out.printf("New stock is %d.%n", p.getStock());
            } catch (InvalidStockException e) {
              System.out.println(e.getMessage());
            }
          }
          break;
        case 11: // Search Inventory
          System.out.print("Enter category to search (or leave blank for all): ");
          String searchCategory = scanner.nextLine();
          if (searchCategory.isBlank()) searchCategory = null;
          System.out.print("Enter minimum price: ");
          double searchMinPrice = -1;
          try {
            searchMinPrice = scanner.nextDouble();
          } catch (InputMismatchException e) {
            exit();
          }
          System.out.print("Enter maximum price: ");
          double searchMaxPrice = -1;
          try {
            searchMaxPrice = scanner.nextDouble();
            scanner.nextLine();
          } catch (InputMismatchException e) {
            exit();
          }
          System.out.print("Only show available products? (yes/no): ");
          boolean searchAvailable = scanner.nextLine().equalsIgnoreCase("yes");
          System.out.print("Only show products with discount? (yes/no): ");
          boolean searchDiscount = scanner.nextLine().equalsIgnoreCase("yes");
          HashMap<String, Product> searchResults =
              sellers
                  .get(sellerID)
                  .searchProducts(
                      searchCategory,
                      searchMinPrice,
                      searchMaxPrice,
                      searchAvailable,
                      searchDiscount);
          if (searchResults.isEmpty()) {
            System.out.println("No products match your search criteria.");
          } else {
            System.out.printf(
                "%-5s %-25s %-10s %-10s %-10s%n", "ID", "Name", "Price($)", "Stock", "Discount");
            System.out.println("---------------------------------------------------------------");
            for (Product p : searchResults.values()) {
              System.out.printf(
                  "%-5s %-25s %-10.2f %-10d %-10.1f%%%n",
                  p.getProductId(),
                  p.getName(),
                  p.getCurrentPrice(),
                  p.getStock(),
                  p.getDiscountPercent());
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
        default:
          System.out.println("Invalid choice. Try again.");
      }
    }
  }

  private static void checkExit(String input) {
    if (input != null && input.equalsIgnoreCase("exit")) {
      System.out.println("Exiting program...");
      scanner.close();
      System.exit(0);
    }
  }

  private static void exit() {
    System.out.println("Exiting program...");
    scanner.close();
    System.exit(0);
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
