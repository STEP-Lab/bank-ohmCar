package com.thoughtworks.step;

public class MinimumBalanceException extends Throwable {
    public MinimumBalanceException() {
        super("Insufficient balance");
    }
}
