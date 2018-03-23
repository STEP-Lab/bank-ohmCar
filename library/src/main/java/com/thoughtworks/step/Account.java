package com.thoughtworks.step;


import java.sql.Array;
import java.util.ArrayList;

public class Account {
    private final String accountHolder;
    private double balance;
    private Transactions transactions = new Transactions();
    private ArrayList<Transaction> allTransactions = transactions.allTransactions;

    public Account(AccountNumber accountNumber, double balance, String accountHolder) throws MinimumBalanceException,InvalidAccountNumberException {
        this.accountHolder = accountHolder;
        validateBalance(balance);
        this.allTransactions = allTransactions;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public ArrayList<Transaction> getAllTransactions() {
        return allTransactions;
    }

    private void validateBalance(double balance) throws MinimumBalanceException {
        if(balance < 1000) {
            throw new MinimumBalanceException("Insufficient balance to create an account!");
        }
    }

    private boolean canDebit(double amountToBeDebited,double balance){
        double updatedBalance = balance - amountToBeDebited;
        return updatedBalance >= 1000;
    }

    private boolean canCredit(double amountToBeCredited){
        return amountToBeCredited > 0;
    }

    public double credit(double amountToBeCredited) throws MinimumBalanceException {
        if(canCredit(amountToBeCredited)){
            balance += amountToBeCredited;
            transactions.credit(accountHolder,amountToBeCredited);
            return getBalance();
        }
        throw new MinimumBalanceException("Invalid credit request!");
    }

    public double debit(double amountToBeDebited) throws MinimumBalanceException {
        if (canDebit(amountToBeDebited, balance)) {
            balance -= amountToBeDebited;
            transactions.debit(accountHolder,amountToBeDebited);
            return getBalance();
        }
        throw new MinimumBalanceException("Can't process your debit request due to low balance!");
    }
}
