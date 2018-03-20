package com.thoughtworks.step;

public class Account {
    private final String accountNumber;
    private int balance;

    public Account(String accountNumber, int balance) throws MinimumBalanceException,InvalidAccountNumberException {
        this.accountNumber = accountNumber;
        validateBalance(balance,"Insufficient balance to create an account");
        validateAccountNumber(accountNumber);
        this.balance = balance;
    }

    private static void validateAccountNumber(String accountNumber) throws InvalidAccountNumberException {
        if(!accountNumber.matches("\\d{4}-\\d{4}")){
            throw new InvalidAccountNumberException("Invalid account number");
        }
    }

    private static void validateBalance(int balance,String message) throws MinimumBalanceException {
        if(balance < 1000){
            throw new MinimumBalanceException(message);
        }
    }


    public int getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void credit(int amount) {
        balance+=amount;
    }

    public void debit(int amount) throws MinimumBalanceException{
        validateBalance(balance-amount,"Can't process your withdraw request");
        balance-=amount;
    }
}
