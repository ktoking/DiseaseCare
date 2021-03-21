package com.fehead.diseaseCare.utility;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DateUtil {
    /**
     * 通过调用Priod类的静态方法between,得到两个日期相差的年月日信息
     * @return
     */
    private void calculateTimeDifferenceTimeDifferenceByPeriod(LocalDateTime dateTime){
        LocalDateTime tody=LocalDateTime.now();
        //Period period=Period.between(dateTime,tody);
    }
}
