package me.whiteship.java8to11.section1;

import java.util.function.Function;

/**
 * 첫번쨰 integer -> 입력값타입
 * 두번째 integer -> 리턴값타입
 */
public class Plus10 implements Function<Integer, Integer> {
    @Override
    public Integer apply(Integer integer) {
        return integer + 10;
    }
}
