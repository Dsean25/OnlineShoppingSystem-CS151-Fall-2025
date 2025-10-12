import java.util.HashMap;

public abstract class User {
  private String userID;
  private String name;
  private String phoneNumber;
  private String address;
  private String password;
  private boolean loggedIn;

  public User(String userID, String name, String phoneNumber, String address, String password) {
    this.userID = userID;
    this.name = name;

    this.phoneNumber = phoneNumber;
    this.address = address;
    this.password = password;
  }

  public String getUserID() {
    return userID;
  }

  public String getName() {
    return name;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getAddress() {
    return address;
  }

  public void setPhoneNumber(String newPhoneNumber) {
    this.phoneNumber = newPhoneNumber;
  }

  public void setAddress(String newAddress) {
    this.address = newAddress;
  }

  public void setName(String newName) {
    this.name = newName;
  }

  public void setUserID(String userID) {
    this.userID = userID;
  }

  public void setPassword(String password) {
    if (PasswordStrengthTest(password)) {
      this.password = password;
    }
  }

  public boolean isLoggedIn() {
    return loggedIn;
  }

  public boolean PasswordStrengthTest(String password) {
    if (password.length() < 10) {
      System.out.println("Password must be at least 10 characters long.");
      return false;
    }
    return true;
  }

  public boolean login(String userID, String password) {
    if (this.userID.equals(userID) && this.password.equals(password)) {
      loggedIn = true;
    }
    return loggedIn;
  }

  public void logout() {
    if (loggedIn) {
      System.out.println("User " + userID + " logged out.");
      loggedIn = false;
    }
  }

  public abstract HashMap<String, Product> searchProducts(
      String category,
      double minPrice,
      double maxPrice,
      boolean isAvailable,
      boolean discountAvailable);
}
