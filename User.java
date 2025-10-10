public abstract class User {
  private String userID;
  private String name;
  private int phoneNumber;
  private String address;

  public User(String userID, String name, int phoneNumber, String address) {
    this.userID = userID;
    this.name = name;

    this.phoneNumber = phoneNumber;
    this.address = address;
  }

  public String getUserID() {
    return userID;
  }

  public String getName() {
    return name;
  }

  public int getPhoneNumber() {
    return phoneNumber;
  }

  public String getAddress() {
    return address;
  }

  public void setPhoneNumber(int newPhoneNumber) {
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
}
