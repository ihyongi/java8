package me.whiteship.java8to11.section2;

public class App {
    public static void main(String[] args) {
        Foo foo = new DefaultFoo("hyoeun");
        foo.printName();
        foo.printNameUpperCase();

        Foo.printAnyThing(); //스태틱메서드
    }
}
