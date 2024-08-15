package com.sh.app;

import java.time.LocalDate;
import java.util.Date;

public class test {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        System.out.println(date);

        Date date1 = new Date();
        System.out.println(date1);
    }
}