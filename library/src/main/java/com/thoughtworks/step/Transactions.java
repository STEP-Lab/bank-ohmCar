package com.thoughtworks.step;

import java.util.ArrayList;

public class Transactions {
    protected ArrayList<Transaction> allTransactions;

    public Transactions() {
        this.allTransactions = new ArrayList<>();
    }

    public void debit(String to,double amountToBeDebited){
        this.allTransactions.add(new DebitTransaction(to,amountToBeDebited));
    }

    public void credit(String to, double amountToBeCredited) {
        this.allTransactions.add(new CreditTransaction(to,amountToBeCredited));
    }
}
