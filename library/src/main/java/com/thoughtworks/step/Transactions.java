package com.thoughtworks.step;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

public class Transactions {
    protected ArrayList<Transaction> allTransactions;

    public Transactions() {
        this.allTransactions = new ArrayList<>();
    }

    protected void debit(Date date, String to, double amountToBeDebited){
        this.allTransactions.add(new DebitTransaction(date,to,amountToBeDebited));
    }


    public void debit(String to,double amountToBeDebited){
        this.allTransactions.add(new DebitTransaction(to,amountToBeDebited));
    }

    protected void credit(Date date, String to, double amountToBeCredited){
        this.allTransactions.add(new CreditTransaction(date,to,amountToBeCredited));
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

    public ArrayList<Transaction> getTransactionsOn(Date date) {
        Transactions result = new Transactions();
        for (Transaction transaction: allTransactions) {
            if(transaction.getDate().equals(date)){
                result.allTransactions.add(transaction);
            }
        }
        return result.allTransactions;
    }

    public ArrayList<Transaction> getTransactionsBefore(Date date) {
        Transactions result = new Transactions();
        for(Transaction transaction : allTransactions){
            if(transaction.getDate().before(date)){
                result.allTransactions.add(transaction);
            }
        }
        return result.allTransactions;
    }

    public ArrayList<Transaction> getTransactionsAfter(Date date) {
        Transactions result = new Transactions();
        for(Transaction transaction : allTransactions){
            if(transaction.getDate().after(date)){
                result.allTransactions.add(transaction);
            }
        }
        return result.allTransactions;
    }
}
