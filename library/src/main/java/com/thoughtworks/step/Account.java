package com.thoughtworks.step;

public class Account {
    private final String accountNumber;
    private int balance;

    public Account(String accountNumber, int balance) throws MinimumBalanceException,InvalidAccountNumberException {
        this.accountNumber = accountNumber;
        if(balance < 1000){
            throw new MinimumBalanceException("Insufficient balance to create an account");
        }
        if(!accountNumber.matches("\\d{4}-\\d{4}")){
            throw new InvalidAccountNumberException("Invalid account number");
        }
        this.balance = balance;
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
        balance-=amount;
        if(balance < 1000) {
            throw new MinimumBalanceException("Can't process your withdraw request");
        }
    }
}
