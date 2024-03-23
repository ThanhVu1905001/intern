package com.sapo.edu.arrayutils;

import org.apache.commons.lang3.ArrayUtils;

public class Arrayutils {
    public static void main(String[] args) {
        Integer[] array = {1, 2, 3, 4, 5};

        if (array != null && array.length > 0) {
            int[] primitiveArray = ArrayUtils.toPrimitive(array);

            int sum = 0;
            for (int num : primitiveArray) {
                sum += num;
            }
            System.out.println("Sum of array elements: " + sum);
        } else {
            System.out.println("Input array is empty or null.");
        }
    }
}