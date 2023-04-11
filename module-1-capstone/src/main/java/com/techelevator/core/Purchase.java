package com.techelevator.core;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javatuples.Pair;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import static java.util.Map.entry;

public class Purchase {
   private Map<Product, Integer> cart;
   private final Transaction transaction;
    public Map<Product, Integer> getCart() {
        return cart;
    }
    public Transaction getTransaction() {
        return transaction;
    }


    public Purchase(Map<Product, Integer> cart, Transaction transaction) {
        this.cart = cart;
        this.transaction = transaction;
    }

    public void setCart(Map<Product, Integer> cart) {
        this.cart = cart;
    }

}
