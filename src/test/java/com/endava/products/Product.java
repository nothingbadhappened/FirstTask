package com.endava.products;

public interface Product {

    public String getProductType();

    public String getProductName();

    public String getColor();

    public char getSize();

    public double getPrice();

    public double getDiscount();

    public boolean getInStock();

    public Product getInstance();
}