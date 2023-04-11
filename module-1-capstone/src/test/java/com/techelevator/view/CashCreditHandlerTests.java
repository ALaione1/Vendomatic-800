package com.techelevator.view;

import com.techelevator.core.Bills;
import com.techelevator.core.CashCreditHandler;
import com.techelevator.core.Coins;
import com.techelevator.core.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class CashCreditHandlerTests {
    private CashCreditHandler coinCredit;
    private CashCreditHandler  billCredit;

    @Before
    public void initialize(){
        Transaction transaction = new Transaction(5156, LocalDateTime.now());
        coinCredit = new CashCreditHandler(LocalDateTime.now(),transaction);
        billCredit = new CashCreditHandler(LocalDateTime.now(),transaction);
    }

    @Test
    public void testFeedMoneyWithBill() throws IOException {

        //arrange
        billCredit.feedMoneyWithBill(Bills.$FIVE);
        assertEquals(5.00 , billCredit.getRemainingBalance());

    }
    @Test
    public void testFeedMoneyWithCoin() throws IOException {
        coinCredit.feedMoneyWithCoin(Coins.DIME);
        assertEquals(0.10, coinCredit.getRemainingBalance());
    }


    @Test
    public void testChangeDispense() throws IOException {
        //arrange
        Transaction transaction1 = new Transaction(5156, LocalDateTime.now());
        CashCreditHandler change1 = new CashCreditHandler(LocalDateTime.now(),transaction1);
        change1.feedMoneyWithCoin(Coins.QUARTER);
        change1.feedMoneyWithCoin(Coins.QUARTER);
        change1.feedMoneyWithCoin(Coins.PENNY);
        change1.feedMoneyWithCoin(Coins.PENNY);
        Map<Coins,Integer> change;
        change = change1.getTransaction().getChange(change1.getAmountBalance());
        assertEquals(Optional.of(2), (change.get(Coins.PENNY)));


    }
}
