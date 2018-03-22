package com.thoughtworks.step;

import java.util.Date;

public abstract class Transaction {
    protected final Date date;
    protected final String to;
    protected double amount;

    public Transaction(Date date, String to, double amount ) {
        this.date = date;
        this.to = to;
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }
}
