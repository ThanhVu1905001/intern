package com.sapo.edu.demo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PrinterFile implements Printer {
    private String logFilePath;

    public PrinterFile(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    @Override
    public void printCustomer(Customer customer) {
        // Implement code to print customer information to the log file
        try (PrintWriter writer = new PrintWriter(new FileWriter(logFilePath, true))) {
            writer.println("CustomerId: " + customer.getAcctNo() + ", balance: " + customer.getBalance().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printMessage(String message) {
        // ghi kết quả vào file
        try (PrintWriter writer = new PrintWriter(new FileWriter(logFilePath, true))) {
            writer.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
