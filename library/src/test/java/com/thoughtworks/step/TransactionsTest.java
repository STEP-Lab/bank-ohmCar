package com.thoughtworks.step;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;

public class TransactionsTest {
    @Test
    public void recordTransaction() {
        Date date = new Date();
        Transaction debitTransaction = new DebitTransaction(date,"Omkar",1000);
        Transactions transactions = new Transactions();
        transactions.debit("Omkar",1000);
        assertThat(transactions.allTransactions,hasItem(debitTransaction));
    }
}
