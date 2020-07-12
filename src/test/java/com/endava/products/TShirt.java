package com.endava.products;

public class TShirt implements Product {

    private String productName;
    private double price;
    private double discount = 0;
    private boolean inStock;
    private String productType = "T-SHIRT";
    private String color;
    private char size;

    public TShirt(String productName, double price, double discount, boolean inStock, String color, char size) {
        this.productName = productName;
        this.discount = discount;
        this.inStock = inStock;
        this.color = color;
        this.size = size;

        this.price = (discount > 0) ? (price - price * discount / 100) : price;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSize(char size) {
        this.size = size;
    }

    @Override
    public String getProductType() {
        return productType;
    }

    @Override
    public String getProductName() {
        return productName;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public double getDiscount() {
        return discount;
    }

    @Override
    public boolean getInStock() {
        return inStock;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public char getSize() {
        return size;
    }

    @Override
    public Product getInstance() {
        return this;
    }

}
