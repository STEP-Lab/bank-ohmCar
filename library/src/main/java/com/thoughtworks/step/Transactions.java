package com.thoughtworks.step;

import java.io.IOException;
import java.io.PrintWriter;
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

    public ArrayList<Transaction> getAllCreditTransactions() {
        Transactions transactions = new Transactions();
        for (Transaction transaction: allTransactions) {
            if (transaction instanceof CreditTransaction){
                transactions.allTransactions.add(transaction);
            }
        }
        return transactions.allTransactions;
    }

    public ArrayList<Transaction> getAllDebitTransactions() {
        Transactions transactions = new Transactions();
        for (Transaction transaction: allTransactions) {
            if (transaction instanceof DebitTransaction){
                transactions.allTransactions.add(transaction);
            }
        }
        return transactions.allTransactions;
    }

    public void print(PrintWriter writer) {
        for (Transaction transaction: allTransactions) {
            writer.println(transaction.toString());
        }
    }

    public void writeTransactionsInCSV(CSVPrinter CSVPrinter) throws IOException {
        for(Transaction transaction : allTransactions){
            CSVPrinter.writeTransactions(transaction);
        }
    }

}
