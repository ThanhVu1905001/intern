package com.sapo.edu.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {


    @Value("${atm.moneyStr}")
    public String money;


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        Customer customer = new Customer("ABC", "1234", new BigDecimal(5000000));
        AtmBalance moneyAtm = new AtmBalance(money);
        //Demo function
//        Atm bidvAtm = new BidvAtm();

        //thay đổi PrinterConsole thành PrinterFile để ghi ra file
        Printer printer = new PrinterFile("./LogMess.txt");
        BidvAtm bidvAtm = new BidvAtm(printer);


        bidvAtm.printCurrentMoney(moneyAtm.getBalance());
        bidvAtm.displayCustomerInfo(customer);
        bidvAtm.withDraw(customer, new BigDecimal(200000),moneyAtm );
        bidvAtm.printCurrentMoney(moneyAtm.getBalance());
        bidvAtm.withDraw(customer, new BigDecimal(10000000),moneyAtm);
        bidvAtm.printCurrentMoney(moneyAtm.getBalance());
        bidvAtm.deposit(customer,new BigDecimal(1000000),moneyAtm);
        bidvAtm.printCurrentMoney(moneyAtm.getBalance());
    }
}
