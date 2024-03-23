package com.sapo.edu.demo;

import java.math.BigDecimal;

public class AtmBalance {
    private BigDecimal balance;

    public AtmBalance(String balance) {
        this.balance = new BigDecimal(balance);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}