public class TestProduct {
    public static void main(String[] args) {
        //Create new product
        Product apple = new Product("P001", "Apple", "Fruit", 50, 0.99);
        System.out.println("Created product: " + apple);

        //Apply discount
        apple.applyDiscount(10);
        System.out.println("After discount: " + apple);

        //Reduce stock 
        apple.reduceStock(5);
        System.out.println("After selling 5 apples: " + apple);

        //Restock
        apple.restock(20);
        System.out.println("After restocking: " + apple);

        //Delete product
        apple.deleteProduct();
        System.out.println("After deletion: " + apple);

        //Try invalid actions 
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
