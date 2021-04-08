package com.company.warehouse;

import com.company.products.Product;

import java.util.List;

public interface IWarehouse {

    public double AllPrice ();
    public int AllCollItems ();
    public void addItem (Product product);
    public void addItems (List<Product> products);
    public String getName();
    public List<Product> getListProducts();
}
