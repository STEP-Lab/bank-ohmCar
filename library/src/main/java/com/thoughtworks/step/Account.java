package com.thoughtworks.step;

import java.util.ArrayList;

public class Account {
    private final String accountHolder;
    private double balance;
    private Transactions transactions = new Transactions();
    private double MIN_BALANCE_REQUIRED = 1000;
    private ArrayList<Transaction> allTransactions = transactions.allTransactions;

    public Account(AccountNumber accountNumber, double balance, String accountHolder) throws MinimumBalanceException {
        this.accountHolder = accountHolder;
        validateBalance(balance);
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
        if(balance < MIN_BALANCE_REQUIRED) {
            throw new MinimumBalanceException("Insufficient balance to create an account!");
        }
    }

    private boolean canDebit(double amountToBeDebited,double balance){
        double updatedBalance = balance - amountToBeDebited;
        return updatedBalance >= MIN_BALANCE_REQUIRED && amountToBeDebited>0;
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
