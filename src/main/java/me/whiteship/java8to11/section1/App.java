package me.whiteship.java8to11.section1;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class App {

//    public static void main(String[] args) {
//        int size = 1500;
//        int[] numbers = new int[size];
//        Random random = new Random();
//
//        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
//        long start = System.nanoTime();
//        Arrays.sort(numbers);
//        System.out.println("serial sorting took " + (System.nanoTime() - start));
//
//        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
//        start = System.nanoTime();
//        Arrays.parallelSort(numbers);
//        System.out.println("parallel sorting took " + (System.nanoTime() - start));
//
//    }

    public static void main(String[] args) {
        Function<Integer,String> intToString = (i) -> i.toString();

        //1. static메서드 사용방법
//        UnaryOperator<String> hi = (s) -> "hi " + s;
        UnaryOperator<String> hi = Greeting::hi; //메소드 레퍼런스

        //2. 특정인스턴스 메서드 사용방법
        Greeting greeting = new Greeting();
        UnaryOperator<String> hello = greeting::hello; //인스턴스 메서드 참조
        System.out.println(hello.apply("hyoeun"));

        //3. 생성자 참조 - 생성자 호출시 리턴값은 이 객체의 타입이다
        //3.1 생성자에 인자가없는경우
        Supplier<Greeting> newGreeting = Greeting::new; //이 자체로 실제로 인스턴스를 만드는 것은 아님
        Greeting greeting1 = newGreeting.get(); //이 시점에서 인스턴스가 만들어짐 .. supplier.get()

        //3.2 생성자에 인자가있는경우
        Function<String, Greeting> stringGreetingFunction = Greeting::new;
        Greeting hyoeun = stringGreetingFunction.apply("hyoeun");
        System.out.println(hyoeun.getName());

        //4. 임의 객체의 인스턴스 메서드 참조
        String[] names = {"mang_e", "hyongi", "spring"};
        Arrays.sort(names, String::compareToIgnoreCase); //임의의 객체 인스턴스 메서드를 참조한 것
        System.out.println(Arrays.toString(names));
    }

}
