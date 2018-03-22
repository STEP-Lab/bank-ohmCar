package com.thoughtworks.step;

public class AccountNumber {

    public AccountNumber(String accountNumber) throws InvalidAccountNumberException {
        validateAccountNumber(accountNumber);
    }

    private boolean isValidAccountNumber(String accountNumber){
        return accountNumber.matches("\\d{4}-\\d{4}");
    }

    private void validateAccountNumber(String accountNumber) throws InvalidAccountNumberException {
        if(!isValidAccountNumber(accountNumber)){
            throw new InvalidAccountNumberException("Invalid account number!");
        }
    }

}
