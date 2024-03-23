package com.sapo.edu.demo;

import java.math.BigDecimal;

public class BidvAtm implements Atm {
    private Printer printer;
    //sử dụng contructor injecton để Refactor Dependency Injection
    public BidvAtm(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void withDraw(Customer customer, BigDecimal amount, AtmBalance moneyAtm) {
        if (amount == null || amount.compareTo(new BigDecimal(0)) < 0) {
            printer.printMessage("Amount is invalid");
            return;
        }
        if (customer.getBalance().compareTo(amount) < 0) {
            printer.printMessage("Insufficient Balance");
            return;
        }
        if (amount.compareTo(moneyAtm.getBalance()) >= 0) {
            printer.printMessage("ATM is out of money");
            return;
        }

        BigDecimal currentBalance = customer.getBalance();
        customer.setBalance(currentBalance.subtract(amount));

        moneyAtm.setBalance(moneyAtm.getBalance().subtract(amount));
    }


    @Override
    public void printCurrentMoney(BigDecimal moneyAtm) {
        printer.printMessage("Current ATM money is " + moneyAtm.toString());
    }


    @Override
    public void deposit(Customer customer, BigDecimal amount, AtmBalance moneyAtm) {
        if (amount == null || amount.compareTo(new BigDecimal(0)) <= 0) {
            printer.printMessage("Amount is invalid");
            return;
        }

        BigDecimal currentBalance = customer.getBalance();
        customer.setBalance(currentBalance.add(amount));
        moneyAtm.setBalance(moneyAtm.getBalance().add(amount));
    }

    @Override
    public void displayCustomerInfo(Customer customer) {
        printer.printCustomer(customer);
    }
}
