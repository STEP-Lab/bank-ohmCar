package com.thoughtworks.step;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class TransactionTest {
    @Test
    public void checkDateOfTransaction() {
        Date date = new Date();
        Transaction transaction = new DebitTransaction(new Date(),"Ketan",100);
        assertThat(transaction.getDate(),is(date));
    }
}
