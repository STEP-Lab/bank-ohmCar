package com.thoughtworks.step;

import java.util.Date;

public class DebitTransaction extends Transaction {
    protected DebitTransaction(Date date, String to, double amountToBeDebited) {
        super(date,to,amountToBeDebited);
    }
}
