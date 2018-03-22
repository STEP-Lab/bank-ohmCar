package com.thoughtworks.step;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;

public class TransactionsTest {
    @Test
    public void recordTransaction() {
        Transactions transactions = new Transactions();
        transactions.debit("Omkar",1000);
        assertThat(transactions.allTransactions,hasItem(new DebitTransaction(new Date(),"Omkar",1000)));
        transactions.credit("Ketan",1000);
        assertThat(transactions.allTransactions,hasItem(new CreditTransaction(new Date(),"Ketan",1000)));
    }
}
