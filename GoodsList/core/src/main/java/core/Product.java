package core;

public class Product {
    private int price;
    private String condition;

    public Product(int price, String condition) {
        this.price = price;
        this.condition = condition;
    }

    public String getCondition() {
        return this.condition;
    }

    public int getPrice() {
        return this.price;
    }
}
