package me.whiteship.java8to11.section1;

/**
 * 함수형 인터페이스
 * 추상메서드 하나만 정의한다
 * 추상메서드가 두개라면 ? 함수형 인터페이스가 아니다
 */
@FunctionalInterface
public interface RunSomething {
    void doIt();
//    abstract void doItAgain();
    /**
     * 자바 8부터 새로운 기능
     * static 메서드나 public 키워드 생략가능
     */

    /**
     * 인터페이스에 정의 할 수 있는 형태가 다양해짐
     */
    static void printName(){
        System.out.println("hyoeun");
    }

    default void printAge(){
        System.out.println("31");
    }

    //fixme 함수형 인터페이스에서 중요한 것은 오로지 추상메서드가 몇개있는가 => 1개인가?
}
