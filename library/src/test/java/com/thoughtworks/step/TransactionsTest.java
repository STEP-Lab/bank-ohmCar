package com.thoughtworks.step;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;

public class TransactionsTest {

    private Transactions transactions;

    @Before
    public void setUp() {
        transactions = new Transactions();
    }

    @Test
    public void shouldRecordCreditTransaction() {
        transactions.credit("Omkar",1000);
        assertThat(transactions.allTransactions,hasItem(new CreditTransaction(new Date(),"Omkar",1000)));
    }

    @Test
    public void shouldRecordDebitTransaction() {
        transactions.debit("Ketan",1000);
        assertThat(transactions.allTransactions,hasItem(new DebitTransaction(new Date(),"Ketan",1000)));
    }

    @Test
    public void shouldRecordAllTransactions() {
        transactions.credit("Omkar",1000);
        transactions.debit("Ketan",1000);
        assertThat(transactions.allTransactions,hasItems(new CreditTransaction(new Date(),"Omkar",1000),new DebitTransaction(new Date(),"Ketan",1000)));
    }

    @Test
    public void shouldReturnAllTheTransactionsAboveCertainLimit() {
        transactions.credit("Omkar",1200);
        transactions.debit("Ketan",1400);
        transactions.credit("Ketan",700);
        Transaction creditToOmkar = new CreditTransaction("Omkar", 1200);
        Transaction debitToKetan = new DebitTransaction("Ketan", 1400);
        assertThat(transactions.getTransactionsAbove(1000),hasItems(creditToOmkar,debitToKetan));
    }

    @Test
    public void shouldReturnAllTheTransactionsBelowCertainLimit() {
        transactions.credit("Omkar",1200);
        transactions.debit("Ketan",400);
        transactions.credit("Ketan",700);
        Transaction debitToKetan = new DebitTransaction("Ketan",400);
        Transaction creditToKetan = new CreditTransaction("Ketan",700);
        assertThat(transactions.getTransactionsBelow(1000),hasItems(debitToKetan,creditToKetan));
    }

    @Test
    public void shouldReturnAllCreditTransactions() {
        transactions.credit("Omkar",1200);
        transactions.debit("Ketan",400);
        transactions.credit("Ketan",700);
        Transaction creditToKetan = new CreditTransaction("Ketan",700);
        Transaction creditToOmkar = new CreditTransaction("Omkar",1200);
        ArrayList<Transaction> allCreditTransactions = transactions.getAllCreditTransactions();
        assertThat(allCreditTransactions,hasItems(creditToKetan,creditToOmkar));
    }
}
