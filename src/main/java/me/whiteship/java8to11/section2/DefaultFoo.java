package me.whiteship.java8to11.section2;

public class DefaultFoo implements Foo, Bar{
    String name;

    public DefaultFoo(String name) {
        this.name = name;
    }

    //충돌하는경우 직접 override해서 재정의
    @Override
    public void printNameUpperCase() {
        System.out.println(this.name.toUpperCase());
    }

    @Override
    public void printName() {
        System.out.println(this.name);
    }

    @Override
    public String getName() {
        return this.name;
    }
}
