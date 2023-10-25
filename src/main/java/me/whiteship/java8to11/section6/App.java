package me.whiteship.java8to11.section6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App {
    /**
     * 스레드와 프로세스 차이 ??
     * 프로세스는 운영체제로부터 자원을 할당받는 작업의 단위이고
     * 스레드는 프로세스가 할당받은 자원을 이용하는 실행의 단위입니다.
     *
     * 프로세스 : 자신만의 고유 공간과 자원을 할당받아 사용
     * 스레드 : 다른 스레드와 공간과 자원을 공유하면서 사용
     *
     * 스레드의 출현 목적은?
     * - 프로세스보다 크기가 작은 실행 단위 필요
     * - 프로세스의 생성 및 소멸에 따른 오버헤드 감소
     * - 빠른 컨텍스트 스위칭
     * - 프로세스들의 통신 시간, 방법 어려움 해소
     *
     * Q. 스레드 주소공간에 대해서 설명?
     * 하나의 스레드가 동작하기 위해 총 6개의 공기 있다.
     * 사적공간, 공유공간, 커널 스택
     * 사적공간은 스레드 코드 공간, 스레드 전용 전역변수 공간, 스택 공간이 있다.
     * 공유공간에는 데이터 공간, 힙 공간이 있다. 그리고 커널 스택이 있다.
     *
     *
     */
//    public static void main(String[] args) {
//        MyThread myThread = new MyThread();
//        myThread.start(); //스레드 하나 만들어진 상태
//
//        System.out.println("Hello");
//    }
//    static class MyThread extends Thread {
//        @Override
//        public void run() {
//            System.out.println("Hello : " + Thread.currentThread().getName());
//        }
//    }

//    public static void main(String[] args) throws InterruptedException {
//        Thread thread = new Thread(() -> {
//            System.out.println("Thread : " + Thread.currentThread().getName()); //2
//            try {
//                Thread.sleep(3000L);
//            } catch (InterruptedException e) {
//                throw new IllegalStateException(e);
//            }
//        });
//        thread.start();
//
//        System.out.println("Hello : "+ Thread.currentThread().getName()); //1
//        thread.join(); //3tㅡ레드 끝날때까지 기다렷다가 그 다음 라인 실행
//        System.out.println(thread + " is finished");
//    }
//    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        executorService.submit(getRunnable("Hello"));
//        executorService.submit(getRunnable("Hyoeun"));
//        executorService.submit(getRunnable("The"));
//        executorService.submit(getRunnable("Java"));
//        executorService.submit(getRunnable("Thread"));
//
//        executorService.shutdown();// 현재 진행중인 작업을 끝까지 마치고 끝낸다. -- graceful
////        executorService.shutdownNow(); //응 난그런거 없어 자뉜한 요자임 할때 now
//    }
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        /**
         * 앞에 1초 기다렷다가 반복적으로 2초에 한번씩 출력
         */
        scheduledExecutorService.scheduleAtFixedRate(getRunnable("Hello"), 1,2, TimeUnit.SECONDS);
    }

    private static Runnable getRunnable(String s) {
        return () -> System.out.println("s = " + s + Thread.currentThread().getName());
    }
}
