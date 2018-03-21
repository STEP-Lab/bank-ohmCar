package com.thoughtworks.step;

public class Account {
    private final String accountNumber;
    private final String accountHolder;
    private double balance;

    public Account(String accountNumber, double balance, String accountHolder) throws MinimumBalanceException,InvalidAccountNumberException {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        if(!validateBalance(balance)) {
            throw new MinimumBalanceException("Insufficient minimum balance to create an account");
        }
        validateAccountNumber(accountNumber);
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    private static boolean validateBalance(double balance){
        return balance > 1000;
    }

    private static void validateAccountNumber(String accountNumber) throws InvalidAccountNumberException{
        if(!accountNumber.matches("\\d{4}-\\d{4}")){
            throw new InvalidAccountNumberException("Invalid account number");
        }
    }

    private static boolean canDebit(double amountToBeDebited,double balance){
        double updatedBalance = balance - amountToBeDebited;
        return validateBalance(updatedBalance);
    }

    private static boolean canCredit(double amountToBeCredited){
        return amountToBeCredited > 0;
    }

    public double credit(int amountToBeCredited) throws MinimumBalanceException{
        if(canCredit(amountToBeCredited)){
            balance+=amountToBeCredited;
            return balance;
        }
        throw new MinimumBalanceException("Invalid credit request");
    }

    public double debit(int amountToBeDebited) throws MinimumBalanceException {
        if (canDebit(amountToBeDebited, balance)) {
            balance-=amountToBeDebited;
            return balance;
        }
        throw new MinimumBalanceException("Can't process your debit request due to low balance");
    }
}
