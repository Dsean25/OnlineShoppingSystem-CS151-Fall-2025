import java.util.Scanner;
import java.util.ArrayList;

public class Main {

  private static ArrayList<Product> products = new ArrayList<>();

  public static void main(String[] args) {
    boolean exit = false;
    Scanner scanner = new Scanner(System.in);
    System.out.println("Welcome to the Online Grocery Shop!");

    // Set up a default grocery shop
    initialProductSetup();

    while (!exit) {
      // Main menu
      displaymainMenu();

      // Read user input
      int input = scanner.nextInt();
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

  private static void displaymainMenu() {
    System.out.println("Main Menu");
    System.out.println("-----------------");
    System.out.println("1. Product Menu");
    System.out.println("2. Customer Menu");
    System.out.println("3. Seller Menu");
    System.out.println("4. Exit");
    System.out.print("Enter a choice (1 - 4):");
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

  private static void customerMenu() {
    System.out.println("Welcome to Customer Menu!");
    // If they haven't created their profile, create one.
    // Access shopping cart
    // Place an order
    // Go back
  }

  private static void sellerMenu() {
    System.out.println("Welcome to Seller Menu!");
    // Update product information
  }

  private static void initialProductSetup() {
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
  }
}
