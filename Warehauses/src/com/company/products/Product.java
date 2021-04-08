package com.company.products;

public class Product implements I_item{

    private String name;
    private String warehouse;
    private double price;
    private int coll;


    public Product() {
    }

    public Product(String name, String warehouse, double price, int coll) {
        this.name = name;
        this.warehouse = warehouse;
        this.price = price;
        this.coll = coll;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getWarehouse() {
        return warehouse;
    }
    public void setWarehouse(String sklad) {
        this.warehouse = sklad;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getColl() {
        return coll;
    }
    public void setColl(int coll) {
        this.coll = coll;
    }

    @Override
    public String toString() {
        return
                "Name product = " + name +
                ", warehouse = " + warehouse +
                ", price = " + price +
                ", coll = " + coll;
    }
}
