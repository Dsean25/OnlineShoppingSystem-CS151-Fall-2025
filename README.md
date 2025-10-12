# Online Grocery Shopping System

**Course:** CS151 Object-Oriented Design

**Instructor:** Professor Zhong

**Semester:** Fall 2025

**Team Members:** Toey Lui, Bush Nguyen, , Sweksha Shaw, Matthew Yeh

## Overview
This online grocery shopping system is a Java-based console application that simulates an e-commerce grocery platform.
Customers can create accounts, browse and purchase products, have their orders delivered, and request cancellations or returns when necessary.
Sellers, on the other hand, can manage their product listings.
We aim to implement object-oriented programming principles learned in this class
to create a functional and interactive system that models real-life online shopping experience. 

## Design
**Classes:**
1. `Customer`:
2. `Seller`: 
3. `Product`: 
4. `ShoppingCart`:
5. `Order`: 

**Abstract Class:**
1. `User`

**Interface:**
1. `Discountable`
2. `Operations`

## Installation Instructions
1. Clone the repository
2. Java Linter: [Google Java Format](https://google.github.io/styleguide/javaguide.html)
   - IntelliJ Setup:
       - Settings → Plugins → Google Java Format → Install → restart IDE → enable → choose Default Google Java Style
       - Optional: Help → Edit Custom VM Options → add [IntelliJ JRE Config](https://github.com/google/google-java-format/blob/master/README.md#intellij-jre-config)
       - To format your code, press Ctrl + Alt + L
    - VS Code Setup:
       - Extensions → Google Java Format → Install → Settings
       - Add this to the setting:
         ```
           {
             "editor.defaultFormatter": "google.google-java-format",
             "editor.formatOnSave": true
           }
       - Save settings and reload VS Code
3. Run `Main.java`

## Usage

We have created some instances for Users, including Seller and Customer for sample use case.
You can use these to test out the system.
- Seller ID: `S001`, password: `password123`
- Customer ID: `C001`, password: ``
- Customer ID: `C002`, password: ``
- Customer ID: `C003`, password: ``

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
- **11. Exit:** Exit Seller Menu

**3. User Menu:**
- **Goal:** Display actions for users to take
- **1. View products:** View all products available to be ordered
- **2. View shopping cart:** View current state of the shopping cart - 1) What's in the shopping cart? 2) Estimated cost before tax?
- **3. Add to cart:** Add a product to a shopping cart
- **4. Remove from cart: **Remove a product from the shopping cart
- **5. View payment method:** View current credit card saved on file
- **6. Update payment method:** Update/Add credit card saved on file
- **7. Place an order:** Place an order based on the latest shopping cart with a final after-tax cost displayed
- **8. Confirm order delivery:** A customer manually confirm that order has been received
- **9. View my orders:** View a list of order history
- **10. Cancel an order:** Cancel an existing order
- **11. Create a return:** Create a return for an order
- **12. Complete a return:** Complete a return for an order
- **13. Exit:** Exit User Menu

**4. Exit**
- **Goal:** Allow user to safely exit the system

## Contributions
**Toey Lui (`toeyldev`):**
- Initialized GitHub repo
- Classes: Worked on Order and Main, updated Customer, Seller, User, Shopping Cart, Main
- Interface: Updated Discountable and Operations implementation
- Created and worked on UI/UX structure with functional menus
- Co-authored README
- Fixed compilation, syntax, and logical errors

**Bush Nguyen (`bush-nguyen`):**
- Classes: Created and Worked on Product
- Interface: Discountable
- Added exception handling
- Created tests to test class

**Sweksha Shaw (`sweksha-cloud`):**
- Classes: Created and worked on Shopping Cart and Customer, updated User 
- Interface: Operations
- Co-authored README
- Ensured smooth UI/UX

**Matthew Yeh (`mattthewyeh`):**
- Classes: Created and worked on User and Seller, updated Main
- Abstract class: Created and updated User
- Ensured smooth UI/UX

