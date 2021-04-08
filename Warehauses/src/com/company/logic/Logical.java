package com.company.logic;

import com.company.logic.comparator.SortByPriceProductCompare;
import com.company.logic.comparator.SortByPriceWarehouseCompare;
import com.company.products.I_item;
import com.company.products.Product;
import com.company.warehouse.Warehouse;

import java.io.*;
import java.util.*;

public class Logical {

    List<I_item> productList = new ArrayList<>();

    public Logical(String path) {

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] arrLine = line.split("-");
                productList.add(new Product(arrLine[0].strip(), arrLine[1].strip(), Double.parseDouble(arrLine[2].strip()), Integer.parseInt(arrLine[3].strip())));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void WriteWarehouses(List<Warehouse> listWarehouses, boolean bShortLength)               //запись в (файл st-total.txt) все склады в порядке убывания общей цены всех товаров
    {
        Comparator<Warehouse> comparator = new SortByPriceWarehouseCompare();
        listWarehouses.sort(comparator);

        try (BufferedWriter writerTaskProduct = new BufferedWriter(new FileWriter("st-total.txt"))) {
            if (bShortLength) {
                writerTaskProduct.write("Склад:       общая сумма:\n");
                for (Warehouse warehouse : listWarehouses) {
                    writerTaskProduct.write(warehouse.getName() + "              " + (int) warehouse.AllPrice() + " \n");
                }
                writerTaskProduct.write("\n(sorting by total price)");
            } else {
                writerTaskProduct.write("Склад:       общая сумма:      кол-во товаров:\n");
                for (Warehouse warehouse : listWarehouses) {
                    writerTaskProduct.write(warehouse.getName() + "              " + (int) warehouse.AllPrice() + "                 " + warehouse.AllCollItems() + "\n");
                }
                writerTaskProduct.write("\n(sorting by total price)");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void WriteProductsSort(List<Warehouse> listWarehouse)                                    //запись в (файл product-report.txt) всех товаров по убыванию (по имени товара и общей стоимости на складе)
    {

        Set<String> unicalNames = unicalNameItems(listWarehouse);                  //сохраняем все уникальные товары из всех складов (имена товаров)
        Comparator comparator = new SortByPriceProductCompare();
        List<Product> arrProducts = new ArrayList<>();                             //создаем лист временных продуктов с однинаковым именем

        try (BufferedWriter writerTaskProduct = new BufferedWriter(new FileWriter("product-report.txt"))) {
            for (String unicum : unicalNames) {                                     //1) цикл -> проходимся по каждома уникальному имени

                for (Warehouse w : listWarehouse) {                                 //2) цикл -> проходимся по каждому складу

                    for (int i = 0; i < w.getListProducts().size(); i++) {         //3) цикл -> проходимся по каждому item (товару)
                        if (unicum.equals(w.getListProducts().get(i).getName()))    //4) если товар с именем с уникальным именем
                        {
                            arrProducts.add(w.getListProducts().get(i));           //5) добавляем все продукты с уникальным именем в наш массив
                        }
                    }
                }

                arrProducts.sort(comparator);                                      //сортируем наш список уникального товара по цене

                for (Product product : arrProducts) {                                //записываем все товары в .txt
                    writerTaskProduct.write(product.getName() + "    " + product.getWarehouse() + "    " + product.getPrice() + "\n");
                }

                arrProducts.clear();                                               //Очищаем наш список, что бы можно было записывать туда товары с новым уникальным имене
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ItemSortWarehouse(List<Warehouse> listWarehouse) {            //раскидывает все товары по нужным складам
        for (int i = 0; i < listWarehouse.size(); i++) {
            for (I_item item : productList) {
                if (listWarehouse.get(i).getName().equals(item.getWarehouse())) {
                    listWarehouse.get(i).addItem((Product) item);
                }
            }
        }

    }
    private Set<String> unicalNameItems(List<Warehouse> listWarehouse) {    //вспомогательный метод, отдает список уникальных товаров
        List<Product> listProduct = new ArrayList<>();
        for (Warehouse warehouse : listWarehouse) {
            listProduct.addAll(warehouse.getListProducts());
        }
        String[] tmpName = new String[listProduct.size()];
        for (int i = 0; i < listProduct.size(); i++) {
            tmpName[i] = listProduct.get(i).getName();
        }
        List<String> Names = Arrays.asList(tmpName);
        Set<String> unicalNames = new HashSet<>(Names);

        return unicalNames;
    }

    private void printTest(List<Product> arrProducts) {
        for (Product product : arrProducts) System.out.println(product);
    }
    public void PrintWarehouse(Warehouse warehouse) {       //пусть будет
        System.out.println("Название склада:  " + warehouse.getName());
        System.out.println("Колличество товаров " + warehouse.AllCollItems());
        System.out.println("-------------------------------");

        for (Product product : warehouse.getListProducts()) {
            System.out.println(product);
        }
        System.out.println("-------------------------------");

    }

}
