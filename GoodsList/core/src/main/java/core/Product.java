package core;

public class Product {
    protected int price;
    protected String condition;
    protected String productTitle;

    public Product(int price, String condition, String productTitle) {
        this.price = price;
        this.condition = condition;
        this.productTitle = productTitle;
    }
}
