# Online Grocery Shopping System

**Course:** CS151 Object-Oriented Design

**Instructor:** Professor Zhong

**Semester:** Fall 2025

**Team Members:** Toey Lui, Bush Nguyen, Sweksha Shaw, Matthew Yeh

## Overview
This online grocery shopping system is a Java-based console application that simulates an e-commerce grocery platform.
Customers can create accounts, browse and purchase products, have their orders delivered, and request cancellations or returns when necessary.
Sellers, on the other hand, can manage their product listings.
We aim to implement object-oriented programming principles learned in this class
to create a functional and interactive system that models real-life online shopping experience. 

## Design
**Classes:**

1. `Customer`: Inherits from `User`, owns a `ShoppingCart` and a list of `Order`, and implements `Operations` to manage products.
2. `Seller`: Inherits from `User` and manages a list of `Product` objects for sale.
3. `Product`: Represents an item with a price and implements `Discountable` to apply discounts.
4. `ShoppingCart`: Holds `Product` items for a `Customer` and implements `Operations` for cart management.
5. `Order`: Records purchased `Product`s, the `Customer`, and the total amount after checkout.

**Abstract Class:**
1. `User`: Defines shared attributes and behaviors for `Customer` and `Seller`.

**Interfaces:**
1. `Discountable`: Specifies a method to apply discounts to prices.
2. `Operations`: Defines methods to add and remove product.


## Installation Instructions
1. Clone the repository
2. Java Linter: [Google Java Format](https://google.github.io/styleguide/javaguide.html)
   - IntelliJ Setup:
       - Settings → Plugins → Google Java Format → Install → restart IDE → enable → choose Default Google Java Style
       - Optional: Help → Edit Custom VM Options → add [IntelliJ JRE Config](https://github.com/google/google-java-format/blob/master/README.md#intellij-jre-config)
       - To format your code, press Ctrl + Alt + L
    - VS Code Setup:
       - Extensions → Google Java Format → Install → Settings
       - Save settings and reload VS Code
3. Run `Main.java`
4. Run unit test `UnitTestSeller.java`: set up JUnit Jupiter
    - VS Code Setup:
      - Testing → Add Java Test → Select JUnit Jupiter
    - IntelliJ Setup:
      - File → Project Structure → Libraries → + From Maven → Add ``
      - File → Project Structure → Project → Modules → Dependencies → ✓ junit.jupiter
## Usage

Additionally, we have created some instances for Users, including Seller and Customer for sample use case.
You can use these to test out the system.
- Seller ID: `S001`, password: `password123`
- Customer ID: `C001`, password: `password123`
- Customer ID: `C002`, password: `password123`
- Customer ID: `C003`, password: `password123`

**Main Menu:**
- **Goal:** Display a list of submenus

**Product Menu:**
- **Goal:** Display a list of available products with name, price, and stock for quick overview.

**Seller Menu:** 
- **Goal:** Display actions for sellers to take
- **1. View all my product:** View all product available in the seller's shop
- **2. View single product:** View details of a single product
- **3. Change product price:** Change the price of a single product
- **4. Check product discount:** Check discount percentage of a single product
- **5. Apply product discount:** Apply discount percentage of a single product
- **6. Clear product discount:** Clear discount percentage of a single product
- **7. Add product:** Add a new product
- **8. Remove product:** Remove an existing product in the seller's shop
- **9. Restock:** Restock a product
- **10. Reduce stock:** Reduce stock of a product
- **11. Search Inventory** Searches through list of products in store's inventory
- **12. Logout** Exits Seller Menu & goes back to main menu

**3. Customer Menu:**
- **Goal:** Display actions for users to take
- **1. View products:** View all products available to be ordered
- **2. View shopping cart:** View current state of the shopping cart - 1) What's in the shopping cart? 2) Estimated cost before tax?
- **3. Add to cart:** Add a product to a shopping cart
- **4. Remove from cart:** Remove a product from the shopping cart
- **5. Clear cart:** Takes out all items from cart
- **6. View payment method:** View current credit card saved on file
- **7. Update payment method:** Update/Add credit card saved on file
- **8. Place an order:** Place an order based on the latest shopping cart with a final after-tax cost displayed
- **9. Confirm order delivery:** A customer manually confirm that order has been received
- **10. View my orders:** View a list of order history
- **11. Cancel an order:** Cancel an existing order
- **12. Create a return:** Create a return for an order
- **13. Complete a return:** Complete a return for an order
- **14. Check refund status:** Verifies if the refund has been completed
- **15. Search Products:** Searches through list of products in store's inventory
- **16. Logout:** Exits Customer Menu & goes back to main menu

**4. Exit**
- **Goal:** Allow user to safely exit the system

## Contributions
**Toey Lui (`toeyldev`):**
- Initialized GitHub repo
- Classes: Worked on Order and Main, updated Customer, User, Shopping Cart, Main
- Interface: Updated Discountable and Operations implementation
- Created and worked on UI/UX structure with functional menus
- Co-authored README
- Fixed compilation, syntax, and logical errors

**Bush Nguyen (`bush-nguyen`):**
- Classes: Created and Worked on Product
- Interface: Discountable
- Exception handling
- MAX_INSTANCES

**Sweksha Shaw (`sweksha-cloud`):**
- Classes: Created and worked on Shopping Cart and Customer, updated User and Main 
- Interface: Operations
- Co-authored README
- Ensured smooth UI/UX, implemented searching in UI/UX

**Matthew Yeh (`mattthewyeh`):**
- Classes: Created and worked on Abstract class User and Seller, updated Main
- Created Unit Tests
- Ensured smooth UI/UX, implemented login, logout, and account creation in UI/UX
