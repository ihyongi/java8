package me.whiteship.java8to11.section5;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class App {

//    public static void main(String[] args) throws InterruptedException {
//        Date date = new Date();
//        long time = date.getTime(); //타임스탬프오와 다를바가 없다
//        System.out.println("date = " + date); //Tue Oct 24 22:01:07 KST 2023
//        System.out.println("time = " + time); //1698152467163 -- 기계용시간
//
//        Thread.sleep(3000L);
//        Date after = new Date();
//        System.out.println("after1 = " + after);
//        after.setTime(time); //인스턴스를 변화시킴 이전 시간으로..
//        System.out.println("after2 = " + after); //mutable하다
//        /**
//         * mutable하다 ? 멀티스레드에서 안전하게 사용하기 어렵다
//         * threa1에서 plus day1
//         * thread2에서 minus day1
//         * 한다고 가정했을때 2가 먼저 작용해서 그담에 1이 들어오면 날짜가 고대로이기 때문에 원하는결과를 도출못하는 경우가 있음을 말하는거
//         */
////        date.setTime();
//    }

    public static void main(String[] args) throws InterruptedException {
        /**
         * month가 0부터시작
         * 7월 -> 6월
         * 8월 -> 7월
         * -> 타입세이프하지 않다. (=! 스레드세이프)
         *
         * JODA time 쓰다가 자바에 표준으로 들어왔단 얘기인것 같다.
         */
        Calendar hyoeunBirthDay = new GregorianCalendar(1993, Calendar.AUGUST, 11);
        Instant instant = Instant.now();
        System.out.println("instant = " + instant); //사용자 친화적으로 알려준다 기준시(UTC, GMT)
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());//내 시스템기준 시간으로 보겠다
        System.out.println("zonedDateTime = " + zonedDateTime);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime birthDay = LocalDateTime.of(1993, Month.AUGUST, 11, 0 ,0 ,0);
        ZonedDateTime zonedDateTime1 = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));//특정존의 현재시간 보고싶을때
        System.out.println("zonedDateTime1 = " + zonedDateTime1);
        //변환가능
        zonedDateTime1.toInstant();

        //3. 기간표현
        /**
         * period는 휴먼용
         * duration은 머신용
         * 시간비교..
         */
        LocalDate today = LocalDate.now();
        LocalDate thisYearBirthday = LocalDate.of(2023, Month.NOVEMBER, 11);
        Period between = Period.between(today, thisYearBirthday);
        Period until = today.until(thisYearBirthday);
        System.out.println("until11 = " + until.getDays());
        System.out.println("until22 = " + until.get(ChronoUnit.DAYS));
        System.out.println("between.getDays() = " + between.getDays());

        Instant now1 = Instant.now();
        Instant plus = now1.plus(10, ChronoUnit.SECONDS);
        Duration between1 = Duration.between(now1, plus);
        System.out.println("between1 = " + between1.getSeconds());

        //4. 포맷팅(localdatetime -> string)
        LocalDateTime now2 = LocalDateTime.now();
        System.out.println("now2 = " + DateTimeFormatter.ofPattern("YYYYMMdd").format(now2));
        //4. 파실(string->localdatetime)
        LocalDate parse = LocalDate.parse("08/11/1993", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        System.out.println("parse = " + parse);

        /**
         * 5. api
         * date -> instant :기계형 시간
         */
        Date date = new Date();
        Instant instant1 = date.toInstant();
        Date newDate = Date.from(instant1);

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        ZonedDateTime zonedDateTime2 = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault());
        LocalDateTime dateTime = zonedDateTime2.toLocalDateTime();
        GregorianCalendar from = GregorianCalendar.from(zonedDateTime2); //레거시 스타일

        //예전 -> 최근 api
        ZoneId zoneId = TimeZone.getTimeZone("PST").toZoneId();
        TimeZone timeZone = TimeZone.getTimeZone(zoneId);

    }
}
