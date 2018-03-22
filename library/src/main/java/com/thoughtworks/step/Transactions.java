package com.thoughtworks.step;

import java.util.ArrayList;

public class Transactions {
    protected final ArrayList<Transaction> allTransactions;

    public Transactions() {
        this.allTransactions = new ArrayList<>();
    }

    public void debit(String to,double amountToBeDebited){
        this.allTransactions.add(new DebitTransaction(to,amountToBeDebited));
    }
}
