public class Customer extends User implements Operations{
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

    @Override
    public void addProduct(Product p, int qty){
        cart.addProduct(p, qty);
        //print statements are in addtocart
    }
    @Override
    public void removeProduct(Product p, int qty){
        cart.removeProduct(p, qty);
    }

    public void placeOrder(){
    
    }

    @Override
    public void calculateTotal(){
        //in cart AND checkout??
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
