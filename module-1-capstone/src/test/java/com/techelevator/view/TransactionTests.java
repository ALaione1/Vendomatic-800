package com.techelevator.view;

import com.techelevator.core.Coins;
import com.techelevator.core.Product;
import com.techelevator.core.Purchase;
import com.techelevator.core.Transaction;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class TransactionTests {
    @Test
    public void testDispenseProduct() {
        HashMap<Product, Integer> cart1 = new HashMap<>();
        HashMap<Product, Integer> cart2 = new HashMap<>();
        Product product = new Product( 201,  "Coke",  "Drink",  101, 1.00);
        Product product2 = new Product( 202,  "Potato Crisps",  "Chip",  102, 1.25);
        cart1.put(product, 1);
        cart2.put(product2, 1);
        Transaction transaction = new Transaction(134 , LocalDateTime.now());
        Purchase purchaseTest1 = new Purchase(cart1,transaction);
        Purchase purchaseTest2 = new Purchase(cart2,transaction);
        transaction.dispenseProduct();

    }



}


