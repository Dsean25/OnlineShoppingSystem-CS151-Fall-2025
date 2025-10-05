public class Customer extends User{
    private int customerID;
    private ShoppingCart cart;
    private String paymentMethod;

    public Customer(int customerID){
        this.customerID = customerID;
    }
    
    //setters
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    //getters
    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }


    public void placeOrder(){
    
    }

    public void changePaymentMethod(){

    }

       /*+ pending methods, according to UML: 
    addProductToCart(Product p):void
    removeProductFromCart(Product p): void
    changeProductQuantity(Product p, int qty):void
    changePaymentMethod(): void
    */





}
