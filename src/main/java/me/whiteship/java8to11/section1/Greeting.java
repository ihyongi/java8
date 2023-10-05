package me.whiteship.java8to11.section1;

public class Greeting {
    private String name;

    //비어있는 생성자
    public Greeting(){

    }

    //생성자
    public Greeting(String name) {
        this.name = name;
    }

    //인스턴스
    public String hello(String name) {
        return "hello " + name;
    }

    //스태틱
    public static String hi(String name) {
        return "hi " + name;
    }

    public String getName() {
        return name;
    }
}
