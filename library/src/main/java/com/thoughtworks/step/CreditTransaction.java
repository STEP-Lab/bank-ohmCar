package com.thoughtworks.step;

import java.util.Date;

public class CreditTransaction extends Transaction {
    protected CreditTransaction(Date date, String to, double amountToBeCredited) {
        super(date,to,amountToBeCredited);
    }
    public CreditTransaction(String to, double amountToBeCredited){
        this(new Date(),to,amountToBeCredited);
    }

}
