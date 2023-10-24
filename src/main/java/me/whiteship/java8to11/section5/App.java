package me.whiteship.java8to11.section5;

import java.util.Calendar;
import java.util.GregorianCalendar;

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


    }
}
