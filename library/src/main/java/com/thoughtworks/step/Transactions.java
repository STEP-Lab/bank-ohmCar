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

    public ArrayList<Transaction> getTransactionsAbove(double amount) {
        Transactions transactions = new Transactions();
        for (Transaction transaction: allTransactions) {
            if (transaction.getAmount()>amount){
                transactions.allTransactions.add(transaction);
            }
        }
        return transactions.allTransactions;
    }

    public ArrayList<Transaction> getTransactionsBelow(double amount) {
        Transactions transactions = new Transactions();
        for (Transaction transaction: allTransactions) {
            if (transaction.getAmount()<amount){
                transactions.allTransactions.add(transaction);
            }
        }
        return transactions.allTransactions;
    }
}
