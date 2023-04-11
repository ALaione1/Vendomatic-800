package com.techelevator.core;

import java.time.LocalDateTime;
import java.util.*;

public class Transaction {
    private final long id;
    private final LocalDateTime dateTime;
    private List<Purchase> purchases;
    private Status status;

    public List<Purchase> getPurchases() {
        return purchases;
    }
    public double getTotalCost(){
        double amount = 0.0;
        for(Purchase purchase: getPurchases()){
            for (Product product:purchase.getCart().keySet()){
                amount+=product.getPrice();
            }
        }
        return amount;
    }
    public void addPurchase(Purchase purchase){
        purchases.add(purchase);
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }
    List<Coins> coinsChange = new ArrayList<>();

    public long getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Status getStatus() {
        return status;
    }

    public Transaction(long id, LocalDateTime dateTime) {
        this.id = id;
        this.dateTime = dateTime;
        this.status = Status.OPEN;
        this.purchases = new ArrayList<>();
    }
    public void setStatus(Status status){
        this.status = status;
    }
    public void dispenseProduct(){
    for(Purchase purchase : getPurchases()){
        System.out.println("*******************");
        Product product=null;
        for ( Product p : purchase.getCart().keySet() ) {
            product = p;
            break;
        }
           String s = "";
           switch (Objects.requireNonNull(product).getItem()) {
               case "Chip":
                   s = "Crunch Crunch";
                   break;
               case "Candy":
                   s = "Munch Munch";
                   break;
               case "Drink":
                   s = "Glug Glug";
                   break;
               case "Gum":
                   s = "Chew Chew";
                   break;
               default:s = "";
           }
           System.out.println(s+" your "+product.getName()+", Yum!");
       }
        System.out.println("*******************"); // Printing blink line
    }

    public double getBalance(){
        return  0;
    }
    public boolean isClose(){
        return getStatus()==Status.CLOSED;
    }
    public void close(){
        setStatus(Status.CLOSED);
    }

    private List<Coins> changeComputing(double balance){
        double roundOff = (double) Math.round(balance * 100) / 100;
            if(roundOff>=0.25){
                coinsChange.add(Coins.QUARTER);
                changeComputing(roundOff - 0.25);
            }else
            if(roundOff>=0.10){
                coinsChange.add(Coins.DIME);
                changeComputing(roundOff - 0.10);
            }else
            if(roundOff>=0.05){
                coinsChange.add(Coins.NICKLE);
                changeComputing(roundOff - 0.05);
            }else
            if (roundOff>=0.01){
                coinsChange.add(Coins.PENNY);
                changeComputing(roundOff - 0.01);
            }
            return coinsChange;
    }
    public Map<Coins,Integer> getChange(double amount){
        Map<Coins,Integer> change= new HashMap<>();
        List<Coins> coins = changeComputing(amount);
        for(Coins coin : coins) {
            change.put(coin,
                    change.get(coin)==null?1:
                            change.get(coin) + 1);
        }
        return change;
    }
}
