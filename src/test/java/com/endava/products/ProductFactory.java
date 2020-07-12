package com.endava.products;

import org.openqa.selenium.InvalidArgumentException;

public class ProductFactory {

    // THIS IS A MOCK. Later data will be fetched from the DB
    public static Product getProductByName(String productName) {

        productName = productName.toUpperCase();

        if (productName.contains("DRESS")) {
            return new Dress(productName, 20.50, 20, true, "yellow", 's');
        } else if (productName.contains("SHIRT")) {
            return new TShirt(productName, 16.51, 0, true, "orange", 's');
        } else throw new InvalidArgumentException("Cannot create product: " + productName);
    }


}

