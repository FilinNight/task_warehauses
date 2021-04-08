package com.company.logic.comparator;

import com.company.products.Product;

import java.util.Comparator;

public class SortByPriceProductCompare implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return (int) ((int) o2.getPrice() - o1.getPrice());
    }
}
