package me.whiteship.java8to11.section1;

import java.util.function.*;

public class Foo {
    public static void main(String[] args) {
        //익명 내부 클래스
        RunSomething runSomething = new RunSomething() {
            @Override
            public void doIt() {

            }
        };

        //옵션+엔터 => 람다로 구현
        //함수형 인터페이스 구현시
        RunSomething runSomething1 = () -> System.out.println("Hello");
        runSomething1.doIt();


        /**
         * 수학적 함수 예시 == 퓨어하다
         * 입력받은 값이 동일한 경우 결과가 같아야 한다.
         * 동일 결과가 아니라면 함수형 프로그래밍이라고 보기 어렵다
         */
        RunSomething2 runSomething2 = number -> number + 10 ;
        System.out.println("runSomething2.doIt(1) = " + runSomething2.doIt(1)); //11
        System.out.println("runSomething2.doIt(1) = " + runSomething2.doIt(1)); //11
        System.out.println("runSomething2.doIt(1) = " + runSomething2.doIt(1)); //11

        /**
         * 퓨어하지 않다
         *  1. 상태값에 의존하는 경우
         *  2. 외부의 값을 변경하려는 경우
         *
         *  퓨어하려면 함수 밖의 있는값을 참조하거나 변경하려하면안되고 오로지 함수가 전달받은 파라미터값 가지고 쓴다.
         */

        //1.
        RunSomething2 runSomething3 = new RunSomething2() {
            int baseNum = 10; //상태값에 의존한다.
            @Override
            public int doIt(int number) {
                return number + baseNum;
            }
        };

        //2.
        RunSomething2 runSomething4 = new RunSomething2() {
        int baseNum = 10; //상태값에 의존한다.
            @Override
            public int doIt(int number) {
                baseNum ++;
                return number + baseNum;
            }
        };

        //3. 로컬배리어블 변경.. final이라고 간주함
        int baseNum = 10; //상태값에 의존한다.
        RunSomething2 runSomething5 = new RunSomething2() {
            @Override
            public int doIt(int number) {
                return number + baseNum;
            }
        };

        //function<T,R> 구현 1.
        Plus10 plus10 = new Plus10();
        System.out.println("plus10 = " + plus10.apply(1)); //11

        //function<T,R> 구현 2.
        Function<Integer, Integer> plus10_1 = (i) -> i + 10;
        System.out.println("plus10_1 = " + plus10_1.apply(1)); //11

        //function<T,R> 구현 3. 함수 조합용은 default로 제공
        //1. compose -- 입력값을 뒤에 함수에 먼저 적용
        Function<Integer, Integer> multiple2 = (i) -> i * 2;
        // 나는 10을 더하기전에 먼저 곱하겠다.
        Function<Integer, Integer> multiple2AndPlus10 = plus10.compose(multiple2);
        System.out.println("나는 10을 더하기전에 먼저 곱하겠다. = " + multiple2AndPlus10.apply(2));  //14

        //2. andThen -- 입력값이 앞에 함수에 먼저 적용 후 뒤에
        Function<Integer, Integer> plus10AndMultiple2 = plus10.andThen(multiple2);
        System.out.println("나는 10을 더하고 나서 곱하겠다. = " + plus10AndMultiple2.apply(2));  //24

        //3. identity --> 이건 입력값리턴이라 조합용으로 보기 어려움

        // cunsumer<T> 구현
        Consumer<Integer> printT = System.out::println;
        printT.accept(10);

        //supplier<T> 구현
        //인자가 없으며 무조건 10을 return 한다.
        Supplier<Integer> get10 = () -> 10;
        System.out.println("get10.get() = " + get10.get());

        //predicate<T> 구현
        Predicate<String> startWithHyoeun = (s) -> s.startsWith("hyoeun"); //return true or false
        Predicate<Integer> isOdd = (i) -> i % 2 == 1; //return true or false
        Predicate<Integer> isEven = (i) -> i % 2 == 0; //return true or false

        //unaryoperator<T> 구현
        UnaryOperator<Integer> plus10_2 = (i) -> i + 10;
        UnaryOperator<Integer> multiple2_2 = (i) -> i * 2;

        //BinaryOperator<T> 구현
        BinaryOperator<Integer> sum = Integer::sum;

        //Variable Capture
        Foo foo = new Foo();
        foo.run();


    }

    private void run() {
        int baseNum = 10;

        /**
         * 1,2,3 공통적인것 ?? 셋다 baseNum 참조 가능
         * 다른점은 ?? 쉐도잉..
         * 몽테소리1!!
         *
         */
        //로컬 클래스
        class LocalClass {
            void printBaseNum() {
                int baseNum = 11;
                System.out.println(baseNum); //11
            }
        }

        //익명 클래스
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer baseNum) { //이곳의 파라미터를 참조한다. 더이상 밖의 baseNum x
                System.out.println(baseNum); //쉐도잉
            }
        };
        integerConsumer.accept(3); //3

        //람다
        /**
         * int baseNum = 10;
         * 람다에선 같은 스콮,, 또 변수선언? 불가능
         */
        IntConsumer printT = (i) -> System.out.println(i + baseNum);
        printT.accept(10);
    }
}
