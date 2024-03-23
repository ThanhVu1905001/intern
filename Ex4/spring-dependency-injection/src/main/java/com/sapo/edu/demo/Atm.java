package com.sapo.edu.demo;

import java.math.BigDecimal;

public interface Atm {
    void withDraw(Customer customer, BigDecimal amount, AtmBalance moneyAtm);


    void printCurrentMoney(BigDecimal moneyAtm);


    void deposit(Customer customer, BigDecimal amount, AtmBalance moneyAtm);

    void displayCustomerInfo(Customer customer);
}
