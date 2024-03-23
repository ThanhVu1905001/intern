package com.sapo.edu.stringutils;

import org.apache.commons.lang3.StringUtils;

public class Stringutils {
    public static void main(String[] args) {
        // Chuỗi văn bản đầu vào
        String input = "Hello, World!";

        // Sử dụng thư viện StringUtils để thực hiện các thao tác xử lý chuỗi

        // Kiểm tra xem chuỗi có Empty không
        boolean isEmpty = StringUtils.isEmpty(input);
        System.out.println("Is the input string empty? " + isEmpty);

        // Kiểm tra xem chuỗi có rỗng hoặc chỉ chứa dấu cách không
        boolean isBlank = StringUtils.isBlank(input);
        System.out.println("Is the input string blank? " + isBlank);
        // Reverse chuỗi
        if (!isBlank && !isEmpty) {
            // Thực hiện xử lý chuỗi
            String reversed = StringUtils.reverse(input);
            System.out.println("Reversed string: " + reversed);
        }
    }
}