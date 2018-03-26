package com.thoughtworks.step;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.*;

public class AccountTest {
    private Account account;

    @Before
    public void setUp() throws MinimumBalanceException, InvalidAccountNumberException {
        account = new Account(new AccountNumber("1111-2222"), 5000, "Omkar");
    }

    @Test
    public void checkBalance() {
        assertThat(account.getBalance(),is(5000.0));
    }

    @Test
    public void checkAccountHolder() {
        assertThat(account.getAccountHolder(),is("Omkar"));
    }

    @Test(expected = MinimumBalanceException.class)
    public void checkMinimumBalance () throws MinimumBalanceException,InvalidAccountNumberException {
        new Account(new AccountNumber("2222-1111"),800,"Ketan");
    }

    @Test
    public void checkCreditTransfer() throws MinimumBalanceException{
        assertThat(account.credit(2000),is(7000.0));
    }

    @Test(expected = MinimumBalanceException.class)
    public void checkMinimumBalanceToCredit() throws MinimumBalanceException{
        account.credit(-1000);
    }

    @Test
    public void shouldRecordCreditTransaction() throws MinimumBalanceException {
        account.credit(2000);
        assertThat(account.getAllTransactions(),hasItem(new CreditTransaction(new Date(),"Omkar",2000)));
    }

    @Test
    public void checkDebitTransfer() throws MinimumBalanceException{
        assertThat(account.debit(3000),is(2000.0));
    }

    @Test
    public void shouldRecordDebitTransaction() throws MinimumBalanceException {
        account.debit(3000);
        assertThat(account.getAllTransactions(),hasItem(new DebitTransaction(new Date(),"Omkar",3000)));

    }

    @Test(expected = MinimumBalanceException.class)
    public void checkMinimumBalanceToDebit() throws MinimumBalanceException{
        account.debit(4999);
    }
}
