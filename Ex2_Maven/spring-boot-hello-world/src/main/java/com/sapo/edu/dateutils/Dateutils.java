package com.sapo.edu.dateutils;

import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

public class Dateutils {
    public static void main(String[] args) {
        try {
            // Lấy thời gian hiện tại
            Date now = new Date();

            // Sử dụng thư viện DateUtils để thực hiện các thao tác xử lý thời gian

            // Tạo một đối tượng Calendar từ thời gian hiện tại
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(now);

            // Thêm 7 ngày vào thời gian hiện tại
            calendar.add(Calendar.DAY_OF_MONTH, 7);

            // Lấy thời gian sau khi thêm 7 ngày
            Date oneWeekLater = calendar.getTime();

            System.out.println("One week later: " + oneWeekLater);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred: " + e.getMessage());
        }
    }
}
