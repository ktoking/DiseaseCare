package com.fehead.diseaseCare.utility;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateUtil {


    public static LocalDateTime getTodayStart(LocalDateTime time){
        LocalDateTime todayStart = LocalDateTime.of(time.toLocalDate(), LocalTime.MIN);// 当天开始
        return todayStart;
    }
    public static LocalDateTime getTodayEnd(LocalDateTime time){
        LocalDateTime todayEnd = LocalDateTime.of(time.toLocalDate(), LocalTime.MAX);// 当天开始
        return todayEnd;
    }
}
