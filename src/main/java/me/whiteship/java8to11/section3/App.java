package me.whiteship.java8to11.section3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class App {
//    public static void main(String[] args) {
//        ArrayList<String> name = new ArrayList<>();
//        name.add("hyoeun");
//        name.add("mange");
//        name.add("toby");
//        name.add("foo");

        /**
         * 이 안에 Functional Interface Consumer가 들어감
         * 리턴 밸류가 없음
         */
//        name.forEach(System.out::println); //메서드 레퍼런스

        //2. spliterator() 쪼개서 순회한다
//        Spliterator<String> spliterator = name.spliterator();
//        Spliterator<String> spliterator1 = spliterator.trySplit();//보통 반으로 쪼개서 앞에꺼가 뒤로 감??
//        while (spliterator.tryAdvance(System.out::println)); //다음게없으면 false리턴
//        System.out.println("====================");
//        while (spliterator1.tryAdvance(System.out::println)); //다음게없으면 false리턴

//        name.removeIf(s-> s.startsWith("t"));
//
//        long k = name.stream().map(String::toUpperCase)
//                .filter(s -> s.startsWith("H"))
//                .count();
//        System.out.println("k = " + k);

        /**
         * Comparator -- Functional Interface
         */
//        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
//        name.sort(compareToIgnoreCase); //문자열로 정렬
//        name.sort(compareToIgnoreCase.reversed()); //문자열 역순으로 정렬
//        name.sort(compareToIgnoreCase.reversed().thenComparing()); //문자열 역순으로 정렬 + 다른정렬조건
//        name.forEach(System.out::println);

        /**
         * lazy
         * 중계형 오퍼레이터는 터미널 오퍼레이터가 오기전까지 실행을 안한다.
         * 그저 스트림 파이프라인을 정의한 것
         * 반드시 종료형 오퍼레이터가 필요하다.
         * 종료형이 오퍼레이터 없이는 무의미하다..
         * .collect(Collectors.toList()); 필요
         */
//        List<String> collect = name.stream().map((s) -> {
//            System.out.println(s);
//            return s.toUpperCase();
//        }).collect(Collectors.toList());//name에 재할당 안되어있다
//        System.out.println("====================");
//        collect.forEach(System.out::println);
//
//        System.out.println("====================");
//        name.forEach(System.out::println); //대문자로 출력되는가 ?ㄴㄴ

        /**
         * 병렬처리
         * parallel Stream
         * jvm이 알아서 처리해준다.
         * 무조건 빠르다 x, 오히려 느려질 수 도 있음..
         *
         * 스레드를 처리하는 비용 < 컨텍스트 스위칭 하는 비용
         * 언제 써야하는가? 데이터가 정말 방대하게 큰 경우
         * 대부분의 경우 stream이 good
         */
//        List<String> collect = name.parallelStream().map(String::toUpperCase).collect(Collectors.toList());
//        collect.forEach(System.out::println);
//        System.out.println("====================");
//
//        //살짝 오바
//        List<String> collect1 = name.parallelStream().map((s) -> {
//            System.out.println(s + " " + Thread.currentThread().getName());
//            return s.toUpperCase();
//        }).collect(Collectors.toList());
//        collect1.forEach(System.out::println);
//    }

        //api test
        public static void main(String[] args) {
            List<OnlineClass> springClass = new ArrayList<>();
            springClass.add(new OnlineClass(1, "spring boot", true));
            springClass.add(new OnlineClass(2, "spring data jpa", true));
            springClass.add(new OnlineClass(3, "spring mvc", false));
            springClass.add(new OnlineClass(4, "spring core", false));
            springClass.add(new OnlineClass(5, "rest api development", false));

            System.out.println("1. spring으로 시작하는 수업");
            springClass.stream()
                    .filter(c-> c.getTitle().startsWith("spring"))
                    .forEach(cc-> System.out.println(cc.getId()));

            System.out.println("2. closed 되지 않은 수업");
            springClass.stream()
                    .filter(Predicate.not(OnlineClass::isClosed)) //!가 안되는걸 이렇게 해결가능하구나!!
                    .forEach(cc-> System.out.println(cc.getTitle()));

            System.out.println("3. 수업이름만 모아서 스트림 만들기");
            springClass.stream()
                    .map(OnlineClass::getTitle)
                    .forEach(System.out::println);


            List<OnlineClass> javaClass = new ArrayList<>();
            javaClass.add(new OnlineClass(6, "The Java, Test", true));
            javaClass.add(new OnlineClass(7, "The Java, Code manipulation", true));
            javaClass.add(new OnlineClass(8, "The Java, 8 to 11", false));


            List<List<OnlineClass>> hyoeunEvents = new ArrayList<>();
            hyoeunEvents.add(springClass);
            hyoeunEvents.add(javaClass);

            /**
             * 여기서 flatmap써보기
             * 리스트 + 리스트 => 하나로!!
             * flatMap(stream넣어주면 됨)
             */
            System.out.println("4. 두 수업 목록에 들어잇는 모든 수업 아이디 출력");
            hyoeunEvents.stream()
                    .flatMap(Collection::stream)
                    .forEach(c-> System.out.println(c.getId()));

            System.out.println("5. 10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만");
            //10부터 1씩증가하는 stream만들기
            Stream.iterate(10, i -> i + 1)
                    .skip(10)
                    .limit(10)
                    .forEach(System.out::println);

            System.out.println("6. 자바 수업 중에 Test가 들어있는 수업이 있는지 확인");
            System.out.println(javaClass.stream().anyMatch(c-> c.getTitle().contains("Test")));

            System.out.println("7. 스프링 수업 중에 제목에 spring이 들어간 제목만 모아서 List로 만들기");
            List<String> collect = springClass.stream()
                    .filter(c -> c.getTitle().contains("spring"))
                    .map(OnlineClass::getTitle)
                    .collect(Collectors.toList());
            collect.forEach(System.out::println);

        }
}
