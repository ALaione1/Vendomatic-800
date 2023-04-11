package com.techelevator.core;

import java.io.IOException;
import java.time.LocalDateTime;

abstract class CreditHandler {

    private final LocalDateTime dataTime ;
    private final Transaction transaction;
    public Transaction getTransaction() {
        return transaction;
    }
    public double getCreditBalance(Product product){
        return 0.0;
    }
    public CreditHandler( LocalDateTime dataTime, Transaction transaction) {
        this.dataTime = dataTime;
        this.transaction = transaction;
    }
    public void feedMoneyWithBill(Bills bill) throws IOException {

    }
    public void feedMoneyWithCoin(Coins coin) throws IOException {

    }
    public double getAmountBalance(){
        return 0;
    }


    public LocalDateTime getDataTime() {
        return dataTime;
    }
    public double getRemainingBalance(){
        return 0.0;
    }
    public void changeDispense(){

    }
}
