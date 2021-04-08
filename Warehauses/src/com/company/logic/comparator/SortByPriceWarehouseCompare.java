package com.company.logic.comparator;

import com.company.warehouse.Warehouse;

import java.util.Comparator;

public class SortByPriceWarehouseCompare implements Comparator<Warehouse> {
    @Override
    public int compare(Warehouse o1, Warehouse o2) {
        return (int) (o2.AllPrice() - o1.AllPrice());
    }
}
