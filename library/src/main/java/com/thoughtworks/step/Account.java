package com.thoughtworks.step;


public class Account {
    private final String accountHolder;
    private double balance;

    public Account(AccountNumber accountNumber, double balance, String accountHolder) throws MinimumBalanceException,InvalidAccountNumberException {
        this.accountHolder = accountHolder;
        if(!validateBalance(balance)) {
            throw new MinimumBalanceException("Insufficient minimum balance to create an account!");
        }
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    private static boolean validateBalance(double balance){
        return balance > 1000;
    }

    private static boolean canDebit(double amountToBeDebited,double balance){
        double updatedBalance = balance - amountToBeDebited;
        return validateBalance(updatedBalance);
    }

    private static boolean canCredit(double amountToBeCredited){
        return amountToBeCredited > 0;
    }

    public double credit(double amountToBeCredited) throws MinimumBalanceException {
        if(canCredit(amountToBeCredited)){
            balance+=amountToBeCredited;
            return balance;
        }
        throw new MinimumBalanceException("Invalid credit request!");
    }

    public double debit(double amountToBeDebited) throws MinimumBalanceException {
        if (canDebit(amountToBeDebited, balance)) {
            balance-=amountToBeDebited;
            return balance;
        }
        throw new MinimumBalanceException("Can't process your debit request due to low balance!");
    }
}
