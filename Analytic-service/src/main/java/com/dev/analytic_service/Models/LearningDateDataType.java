package com.dev.analytic_service.Models;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

public class LearningDateDataType {

    public static void main(String[] args) {

        Calendar c = Calendar.getInstance();
//        System.out.println(c);


//        DateTime dateTime = new DateTime();
        Date date = new Date();

        c.setTime(new Date());



        LocalDateTime localDateTime = LocalDateTime.now();

        localDateTime.getSecond();

        System.out.println(localDateTime);

        Date date1 = Date.from(localDateTime.
                atZone(ZoneId.systemDefault()).
                toInstant()
        );

        System.out.println(date1);

        System.out.println(ZoneId.getAvailableZoneIds());





//        System.out.println(System.currentTimeMillis());
//        System.out.println(date.getTime());


//        System.out.println(dateTime.getMillis());
//
//        System.out.println(dateTime.getDayOfMonth() +
//                "/" + dateTime.getMonthOfYear()+
//                "/" + dateTime.getYear() +
//                " " + dateTime.getHourOfDay() +
//                ":" + dateTime.getMinuteOfHour() +
//                ":" + dateTime.getSecondOfMinute() +
//                ":" + dateTime.getMillisOfSecond()
//        );
    }

}
