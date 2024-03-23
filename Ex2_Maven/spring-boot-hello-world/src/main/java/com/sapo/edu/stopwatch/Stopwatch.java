package com.sapo.edu.stopwatch;

import org.apache.commons.lang3.time.StopWatch;

public class Stopwatch {
    public static void main(String[] args) {
        StopWatch stopWatch = StopWatch.createStarted();
        try {
            // Thực hiện các thao tác cần thời gian ở đây
            Thread.sleep(3000); // Gây ra độ trễ giả định công việc mất 3 giây
        } catch (InterruptedException e) {
            // Xử lý exception nếu cần
            e.printStackTrace();
        } finally {
            stopWatch.stop();
            long timeElapsed = stopWatch.getTime();
            System.out.println("Time elapsed: " + timeElapsed + " milliseconds");
        }
    }
}

