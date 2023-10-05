package me.whiteship.java8to11.section2;

public interface Foo {
    void printName();

    /**
     * 인터페이스 구현하지않으면 에러나는데
     * defalut 키워드로 이를 방지할 수 있음
     *
     * @implSpec 이 구현체는 getName()으로 가져온 문자열를
     * 대문자로 바꿔 출력한다
     * 또한 구현하는곳에서 DefaultFoo.class에서 재정의 가능
     *
     * 제약사항
     * 디폴트 메서드를 제공할 수 없는 메서드 --Object에서 제공하는 equals, hashcode, toString
     * 재정의할 수 없다
     *
     * 다이아몬드 프라블럼?
     * 인스턴스가 사용할 수 있는거
     * ---------------------------------
     * 모든 인스턴스 또는 해당 타입이 관련되어있는 유틸리티 재공하고싶은경우
     * 스태틱 메서드를 제공할 수 있음
     */
    static void printAnyThing() {
        System.out.println("Foo");
    }
    default void printNameUpperCase() {
        System.out.println(getName().toUpperCase());
    }

    String getName();
}
