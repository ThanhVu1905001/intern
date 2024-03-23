package com.sapo.edu.demo;

import java.math.BigDecimal;
import java.util.Scanner;

public class CustomerInitialization {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter account number: ");
        String accountNumber = scanner.nextLine();

        System.out.println("PIN: ");
        String pin = scanner.nextLine();

        System.out.println("Enter initial balance: ");
        BigDecimal initialBalance = new BigDecimal(scanner.nextLine());

        // Tạo đối tượng Customer từ thông tin nhập vào
        Customer customer = new Customer(accountNumber, pin, initialBalance);

        // In thông tin khách hàng đã khởi tạo
        System.out.println("Customer information has been created:");
        System.out.println("Account number: " + customer.getAcctNo());
        System.out.println("PIN: " + customer.getPin());
        System.out.println("Initial balance: " + customer.getBalance());
    }
}
