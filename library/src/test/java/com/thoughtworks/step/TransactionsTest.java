package com.thoughtworks.step;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;

public class TransactionsTest {

    private Transactions transactions;
    private SimpleDateFormat dateFormatter;

    @Before
    public void setUp() {
        dateFormatter = new SimpleDateFormat("yyyy-mm-dd");
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

    @Test
    public void shouldReturnAllDebitTransactions() {
        transactions.debit("Omkar",1200);
        transactions.debit("Ketan",400);
        transactions.credit("Ketan",700);
        Transaction debitToKetan = new DebitTransaction("Ketan",400);
        Transaction debitToOmkar = new DebitTransaction("Omkar",1200);
        ArrayList<Transaction> allDebitTransactions = transactions.getAllDebitTransactions();
        assertThat(allDebitTransactions,hasItems(debitToKetan,debitToOmkar));
    }

    @Test
    public void shouldPrintTransactions() throws FileNotFoundException, UnsupportedEncodingException {
        ArrayList<String> result = new ArrayList<>();
        transactions.credit("Ketan",700);
        Transaction creditToKetan = new CreditTransaction("Ketan",700);
        PrintWriter printWriter = new PrintWriter("transactions.txt", "UTF-8"){
            @Override
            public void println(String x) {
                result.add(x);
                System.out.println(x);
            }
        };
        transactions.print(printWriter);
        printWriter.close();
        assertThat(result,hasItem(creditToKetan.toString()));
    }

    @Test
    public void shouldWriteTransactionsInCSVFile() throws IOException {
        ArrayList<String> result = new ArrayList<>();
        transactions.credit("Ketan",700);
        String headers = "Date,Amount,To";
        Transaction creditToKetan = new CreditTransaction("Ketan",700);
        FileWriter fileWriter = new FileWriter("transactions.csv"){
            @Override
            public Writer append(CharSequence csq) {
                result.add(String.valueOf(csq));
                System.out.println(csq);
                return this;
            }
        };
        CSVPrinter csvPrinter = new CSVPrinter(fileWriter, headers);
        csvPrinter.writeHeaders();
        transactions.writeTransactionsInCSV(csvPrinter);
        csvPrinter.close();
        assertThat(result,hasItems(String.valueOf(creditToKetan.getDate()), String.valueOf(creditToKetan.getAmount()), creditToKetan.getSource()));
    }

    @Test
    public void shouldReturnTransactionsOnAParticularDate() throws ParseException {
        Date marchTwenty = dateFormatter.parse("2018-03-20");
        Date marchTwentyOne = dateFormatter.parse("2018-03-21");
        transactions.debit(marchTwenty,"Omkar",2000);
        transactions.credit(marchTwenty,"Ketan",5000);
        transactions.credit(marchTwentyOne,"Omkar",400);
        DebitTransaction debitTransaction = new DebitTransaction(marchTwenty, "Omkar", 2000);
        CreditTransaction creditTransaction = new CreditTransaction(marchTwenty, "Ketan", 5000);
        ArrayList<Transaction> transactionsOnMarchTwenty =  transactions.getTransactionsOn(marchTwenty);
        assertThat(transactionsOnMarchTwenty,hasItems(debitTransaction,creditTransaction));
    }

    @Test
    public void shouldReturnAllTheTransactionsBeforeAParticularDate() throws ParseException {
        Date marchTwenty = dateFormatter.parse("2018-03-20");
        Date marchTwentyOne = dateFormatter.parse("2018-03-21");
        Date marchTwentyTwo = dateFormatter.parse("2018-03-22");
        transactions.credit(marchTwenty,"Ketan",500);
        transactions.debit(marchTwentyOne,"Omkar",800);
        transactions.credit(marchTwentyTwo,"Madhuri",900);
        CreditTransaction creditTransaction = new CreditTransaction(marchTwenty, "Ketan", 500);
        DebitTransaction debitTransaction = new DebitTransaction(marchTwentyOne, "Omkar", 800);
        ArrayList<Transaction> transactionsBeforeMarchTwentyTwo = transactions.getTransactionsBefore(marchTwentyTwo);
        assertThat(transactionsBeforeMarchTwentyTwo,hasItems(creditTransaction,debitTransaction));
    }
}
