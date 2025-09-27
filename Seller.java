import java.util.HashMap;

public class Seller extends User {
    private String storeName;
    private HashMap<String, Product> productMap;

    public Seller(String userID, String firstName, String lastName, String email, String phoneNumber, String address, String storeName) {
        super(userID, firstName, lastName, email, phoneNumber, address);
        this.storeName = storeName;
        this.productMap = new HashMap<>();
    }
  
     public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
  
    public void viewProducts() {
        for (Product product : productMap.values()) {
            System.out.println(product);
        }
    }
}
