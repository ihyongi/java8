package me.whiteship.java8to11.section2;

//public interface Bar extends Foo {
//    //Foo이 디폴트메서드 제공하고 싶지않을떄 -- 추상메서드로 선언한다
//    void printNameUpperCase();
//}
public interface Bar {
    default void printNameUpperCase() {
        System.out.println("BAR");
    }
}
