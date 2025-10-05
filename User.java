public abstract class User {
  private String userID;
  private String firstName;
  private String lastName;
  private String email;
  private String phonenumber;
  private String address;

  public User(
      String userID,
      String firstName,
      String lastName,
      String email,
      String phonenumber,
      String address) {
    this.userID = userID;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
    this.address = address;
  }

  public String getUserID() {
    return userID;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getAddress() {
    return address;
  }

  public void updatePhoneNumber(String newPhoneNumber) {
    this.phonenumber = newPhoneNumber;
  }

  public void updateAddress(String newAddress) {
    this.address = newAddress;
  }

  public void updateEmail(String newEmail) {
    this.email = newEmail;
  }

  public void updateName(String newFirst, String newLast) {
    this.firstName = newFirst;
    this.lastName = newLast;
  }
}
