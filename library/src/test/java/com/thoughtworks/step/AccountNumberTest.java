package com.thoughtworks.step;

import org.junit.Test;

public class AccountNumberTest {
    @Test(expected = InvalidAccountNumberException.class)
    public void accountNumberValidation() throws InvalidAccountNumberException{
        new AccountNumber("1234");
    }

    @Test(expected = InvalidAccountNumberException.class)
    public void checkIfTheAccountNumberHasAlphabetsOrNot() throws InvalidAccountNumberException {
        new AccountNumber("12ra-20tb");
    }
}
