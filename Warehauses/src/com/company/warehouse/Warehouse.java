package com.company.warehouse;

import com.company.products.Product;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements IWarehouse{

    private String name;
    private List<Product> listProducts = new ArrayList<>();

    public Warehouse(String name) {
        this.name = name;

    }

    public double AllPrice () {
        double sum = 0;

        for (Product product : listProducts) {
            sum += product.getPrice();
        }

        return sum;
    }
    public int AllCollItems () {
        return listProducts.size();

    }
    public void addItem (Product product) {
        listProducts.add(product);

    }
    public void addItems (List<Product> products) {
        listProducts.addAll(products);

    }

    public String getName() {
        return name;
    }
    public List<Product> getListProducts() {
        return listProducts;
    }
}
