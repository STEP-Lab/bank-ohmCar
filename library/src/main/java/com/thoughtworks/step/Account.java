package com.thoughtworks.step;


public class Account {
    private final String accountHolder;
    private double balance;

    public Account(AccountNumber accountNumber, double balance, String accountHolder) throws MinimumBalanceException,InvalidAccountNumberException {
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

    private static void validateBalance(double balance) throws MinimumBalanceException {
        if(balance < 1000) {
            throw new MinimumBalanceException("Insufficient balance to create an account!");
        }
    }

    private static boolean canDebit(double amountToBeDebited,double balance){
        double updatedBalance = balance - amountToBeDebited;
        return updatedBalance >= 1000;
    }

    private static boolean canCredit(double amountToBeCredited){
        return amountToBeCredited > 0;
    }

    public double credit(double amountToBeCredited) throws MinimumBalanceException {
        if(canCredit(amountToBeCredited)){
            balance += amountToBeCredited;
            return getBalance();
        }
        throw new MinimumBalanceException("Invalid credit request!");
    }

    public double debit(double amountToBeDebited) throws MinimumBalanceException {
        if (canDebit(amountToBeDebited, balance)) {
            balance -= amountToBeDebited;
            return getBalance();
        }
        throw new MinimumBalanceException("Can't process your debit request due to low balance!");
    }
}
