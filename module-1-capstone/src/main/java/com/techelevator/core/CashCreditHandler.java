package com.techelevator.core;

import com.techelevator.util.TELog;

import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CashCreditHandler extends CreditHandler {
    private final List<Bills> BillBalance ;
    private final List<Coins> CoinBalance ;

    public CashCreditHandler(LocalDateTime dataTime, Transaction transaction) {
        super(dataTime, transaction);
        this.BillBalance = new ArrayList<>();
        this.CoinBalance = new ArrayList<>();

    }

    @Override
    public void feedMoneyWithBill(Bills bill) throws IOException {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        BillBalance.add(bill);
        double balance = getRemainingBalance();
        TELog.log("FEED MONEY(" + bill + ") " + currency.format(billToAmount(bill)) +
                " " + currency.format(balance));
    }
    @Override
    public void feedMoneyWithCoin(Coins coin) throws IOException {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        CoinBalance.add(coin);
        double balance = getRemainingBalance();
        TELog.log("FEED MONEY(" + coin + ") " + currency.format(coinToAmount(coin)) +
                " " + currency.format(balance));
    }
    private double billToAmount(Bills bill) {
        switch (bill){
            case $ONE: return 1.0;
            case $FIVE: return 5.0;
            case $TEN: return 10.0;
            case $TWENTY: return 20.0;
            default: return 0;
        }
    }

    private double coinToAmount(Coins coin) {
        switch (coin){
            case PENNY: return  0.01;
            case NICKLE: return  0.05;
            case DIME: return  0.10;
            case QUARTER: return 0.25;
            default: return 0;
        }
    }
    @Override
    public double getAmountBalance() {
        double amount =0.0;
        for(Bills bill: BillBalance) {
            amount+=billToAmount(bill);
        }
        for(Coins coin: CoinBalance ) {
            amount+=coinToAmount(coin);
        }
        return amount;
    }
    @Override
    public double getCreditBalance(Product product){
        return getAmountBalance()-getTransaction().getTotalCost()-product.getPrice();
    }

    @Override
    public double getRemainingBalance() {
        return  getAmountBalance() - getTransaction().getTotalCost();
    }
    @Override
    public void changeDispense() {
        getTransaction().getChange(getRemainingBalance()).forEach((k,v)->
                System.out.println(v+" X "+k)
        );
    }
}
