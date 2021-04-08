package com.company;

import com.company.logic.Logical;
import com.company.warehouse.Warehouse;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Warehouse> listWarehouse = new ArrayList<>();

        listWarehouse.add(new Warehouse("ST1"));
        listWarehouse.add(new Warehouse("ST2"));
        listWarehouse.add(new Warehouse("ST3"));
        listWarehouse.add(new Warehouse("ST4"));

        //data.txt - списов всех поступающих товаров
        Logical logical = new Logical("data.txt");

        //сортирует товары, и направляет их в нужные склады
        logical.ItemSortWarehouse(listWarehouse);

        //запись в (файл st-total.txt) все склады в порядке убывания общей цены всех товаров
        logical.WriteWarehouses(listWarehouse, false);

        //запись в (файл product-report.txt) всех товаров по убыванию (по имени товара и общей стоимости на складе)
        logical.WriteProductsSort(listWarehouse);


    }
}
