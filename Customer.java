public class Customer extends User{
    private String name;
    private String customerId;
    private ShoppingCart cart;
    private String paymentMethod;

    public Customer(String name, String customerId){
        //need to call on super();
        this.name = name;
        this.customerId = customerId;
        cart = new ShoppingCart(customerId);
    }
    
    //setters
    public void setCustomerID(String customerId) {
        this.customerId = customerId;
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

    public void addProductToCart(Product p, int qty){
        cart.addToCart(p, qty);
        //print statements are in addtocart
    }

    public void removeProductFromCart(Product p, int qty){
        cart.removeFromCart(p, qty);
    }

    public void placeOrder(){
    
    }

    public void changePaymentMethod(String paymentmethod){
        setPaymentMethod(paymentMethod);
        System.out.printf("Your payment method was changed to %s!%n", paymentmethod);
    }

       /*+ pending methods, according to UML: 
    removeProductFromCart(Product p): void
    changeProductQuantity(Product p, int qty):void
    */





}
