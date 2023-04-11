package com.techelevator.core;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private final long id;
    private final Map<Product,Integer> slotInventory = new HashMap<>();
    private final Product[] products;
    //private int remainingQuantity;
    public Inventory(long id) throws IOException {
        this.id = id;
        ObjectMapper mapper = new ObjectMapper();
        // load the json file into Product[]
        products = mapper.readValue(new File("data/product.json"), Product[].class);
        reStock(); // restock the vending machine
    }
    public long getId() {
        return id;
    }
    private void reStock() throws IOException {
        for( Product product : getProduct()){
            int INITIAL_QUANTITY = 5;
            slotInventory.put(product, INITIAL_QUANTITY);
        }
    }
    private Map<Product, Integer> getSlotInventory() {
        return slotInventory;
    }
    public void updateQuantity(Product product, int quantity){
        // update the quantity here for each product
        if(slotInventory.get(product)!=null && (slotInventory.get(product)-quantity)>=0){
            slotInventory.put(product,slotInventory.get(product)-quantity);
        }
    }
    public void displayProduct() throws IOException {
        // inventory content dis-players
        for(Product product : getProduct()) {
            System.out.println("Item: "+product.getItem()+" | Code: " + product.getCode() + " | Product: " +
                    product.getName() + " | Price: " + product.getPrice()+" | Remain Quantity: "+
                    slotInventory.get(product));
        }
    }

    public Product[] getProduct() throws IOException {
       return products;
    }

    public Product getProductByCode(int code) throws IOException {
        // look up for product in inventory by it slot code
        Product p = null;
        for( Product product : getProduct()){
            if(product.getCode() == code){
                p=product;
                break;
            }
        }
        return p;
    }

    public int getInventoryByProduct(Product product){
        // it returns quantity
        return getSlotInventory().get(product);
    }
}
