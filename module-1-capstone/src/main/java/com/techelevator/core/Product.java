package com.techelevator.core;

import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Product {

    private int id;
    private String name;
    private String item;
    private int code;
    private double price;

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getItem() {
        return item;
    }

    public int getCode() {
        return code;
    }

    public Product(int id, String name, String item, int code,double price) {
        this.code = code;
        this.name = name;
        this.price= price;
        this.item = item;
        this.id = id;
    }

    public Product(String name) {
        this(0,name,null,0,0.0);
    }
    public Product(){
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private boolean isExist(){
       return false;
    }

    public Map<String,Map<String,String>> toMap(){
        Map<String,String> product = new HashMap<>();
        Map<String,Map<String,String>> productDetail = new HashMap<>();
        product.put("id",String.valueOf(id));
        product.put("code",String.valueOf(code));
        product.put("item",item);
        product.put("price",String.valueOf(price));
        productDetail.put(name,product);
       return productDetail;
    }
}
