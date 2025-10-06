public class TestProduct {
    public static void main(String[] args) {
        // 1️⃣ Create a new product
        Product apple = new Product("P001", "Apple", "Fruit", 50, 0.99);
        System.out.println("Created product: " + apple);

        // 2️⃣ Apply a discount
        apple.applyDiscount(10);
        System.out.println("After discount: " + apple);

        // 3️⃣ Reduce stock (simulate order)
        apple.reduceStock(5);
        System.out.println("After selling 5 apples: " + apple);

        // 4️⃣ Restock
        apple.restock(20);
        System.out.println("After restocking: " + apple);

        // 5️⃣ Delete product
        apple.deleteProduct();
        System.out.println("After deletion: " + apple);

        // 6️⃣ Try invalid actions (to see exceptions)
        try {
            apple.applyDiscount(120); // invalid
        } catch (Exception e) {
            System.out.println("Error caught: " + e.getMessage());
        }

        try {
            apple.reduceStock(999); // not enough stock
        } catch (Exception e) {
            System.out.println("Error caught: " + e.getMessage());
        }
    }
}
