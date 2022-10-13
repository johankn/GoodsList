package core;

public class Product {
    private int price;
    private String condition;
    private String productTitle;

    public Product(int price, String condition, String productTitle) {
        this.price = price;
        this.condition = condition;
        this.productTitle = productTitle;
    }

    public String getCondition() {
        return this.condition;
    }

    public String getProductTitle() {
        return this.productTitle;
    }

    public int getPrice() {
        return this.price;
    }
}
