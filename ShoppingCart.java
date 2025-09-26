public class ShoppingCart {
    String customerId;
    Product product;
    int productCount;
    public double lastUpdated; // difference between public vs not writing anything

    public ShoppingCart(String customerId) {
        this.customerId = customerId;
        this.product = null;
        this.productCount = 0;
        this.lastUpdated = LocalDateTime.now();
    }

    public static void removeFromCart(Product P) {
    }

    public static void addProductToCart(Product P) {
    }

    public static double calculateTotal() {
    }

    public static void clearCart() {
    }
}
