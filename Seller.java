import java.util.HashMap;

public class Seller extends User {
    private static final int MAX_INSTANCES = 100;
    private static int instanceCount = 0;
    private String storeName;
    private HashMap<String, Product> productMap;

    public Seller(String userID, String name, String phoneNumber, String address, String storeName, String password) {
        super(userID, name, phoneNumber, address, password);
        if (instanceCount >= MAX_INSTANCES) {
            throw new IllegalStateException("Reached max Seller limit " + MAX_INSTANCES);
        }
        this.storeName = storeName;
        this.productMap = new HashMap<>();
        instanceCount++;
    }

    public static int getMaxInstances() {
        return MAX_INSTANCES;
    }

    public static int getInstanceCount() {
        return instanceCount;
    }

    public String getStoreName() {
        return storeName;
    }

    public HashMap<String, Product> getProductMap() {
        return productMap;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setProductMap(HashMap<String, Product> productMap) {
        if (productMap != null) {
            this.productMap = productMap;
        }
    }

    public void viewProducts() {
        if (productMap.isEmpty()) {
            System.out.println("No products available in store: " + storeName);
            return;
        }
        for (Product product : productMap.values()) {
            System.out.println(product);
        }
    }

    public boolean hasProduct(String productId) {
        return productId != null && productMap.containsKey(productId);
    }

    public boolean addProduct(Product product) {
        if (product == null || product.getProductId() == null) {
            System.out.println("Invalid product.");
            return false;
        }
        if (productMap.containsKey(product.getProductId())) {
            System.out.println("Product already exists with ID: " + product.getProductId());
            return false;
        }
        productMap.put(product.getProductId(), product);
        System.out.println("Product added: " + product.getName());
        return true;
    }

    public boolean removeProduct(String productId) {
        if (!hasProduct(productId)) {
            System.out.println("Product with ID " + productId + " not found in " + storeName);
            return false;
        }
        Product removedProduct = productMap.remove(productId);
        System.out.println("Product " + removedProduct.getName() + " removed from " + storeName);
        return true;
    }

    public boolean changeProductPrice(String productId, double newPrice) {
        if (newPrice < 0) {
            System.out.println("Price must be >= 0");
            return false;
        }
        if (!hasProduct(productId)) {
            System.out.println("Product with ID " + productId + " is not found in " + storeName);
            return false;
        }
        Product product = productMap.get(productId);
        try {
            product.setPrice(newPrice);
            System.out.println("Price updated for product: " + product.getName());
            return true;
        } catch (InvalidPriceException e) {
            System.out.println("Error updating price: " + e.getMessage());
            return false;
        }
    }

    public Product getProductById(String productId) {
        return productMap.get(productId);
    }

    @Override
    public HashMap<String, Product> searchProducts(String category, double minPrice, double maxPrice, boolean isAvailable, boolean discountAvailable) {
        HashMap<String, Product> results = new HashMap<>();
        for (Product product : productMap.values()) {
            if (category != null && !category.equalsIgnoreCase(product.getCategory())) continue;
            if (product.getPrice() < minPrice || product.getPrice() > maxPrice) continue;
            if (isAvailable && !product.isAvailable()) continue;
            if (discountAvailable && !product.isDiscountAvailable()) continue;
            results.put(product.getProductId(), product);
        }
        return results;
    }
}
