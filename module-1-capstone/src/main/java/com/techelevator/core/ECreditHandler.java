package com.techelevator.core;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ECreditHandler extends CreditHandler {

    public ECreditHandler(LocalDateTime dataTime, Transaction transaction) {
        super(dataTime, transaction);
    }
}
