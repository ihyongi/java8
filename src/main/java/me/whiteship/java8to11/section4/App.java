package me.whiteship.java8to11.section4;

import me.whiteship.java8to11.section3.OnlineClass;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class App {
//    public static void main(String[] args) {
//        List<OnlineClass> springClass = new ArrayList<>();
//        springClass.add(new OnlineClass(1, "spring boot", true));
//        springClass.add(new OnlineClass(2, "spring data jpa", true));
//        springClass.add(new OnlineClass(3, "spring mvc", false));
//        springClass.add(new OnlineClass(4, "spring core", false));
//        springClass.add(new OnlineClass(5, "rest api development", false));
//
//        OnlineClass springBoot = new OnlineClass(1, "spring boot", true);
//        Optional<Progress> progress = springBoot.getProgress();
//        /**
//         * as-is
//         * npe방지를 위해..
//         * 에러를 만들기 좋은 코드
//         */
//        if (progress.isPresent()){
//            Duration studyDuration = progress.get().getStudyDuration();
//            System.out.println("studyDuration = " + studyDuration); //이코드가 정상실행될까 ?ㄴㄴ npe
//        }
//        else Optional.empty();
//
//    }

    public static void main(String[] args) {
        List<OnlineClass> springClass = new ArrayList<>();
        springClass.add(new OnlineClass(1, "spring boot", true));
        springClass.add(new OnlineClass(5, "rest api development", false));

        //있을 수도있고 없을 수도 있기때문에 return이 Optional이다.
        Optional<OnlineClass> spring = springClass.stream().filter(c -> c.getTitle().startsWith("spring")).findFirst();
        /**
         * flatMap
         * progress2 == progress 같다
         */
        //as-is
        Optional<Optional<Progress>> progress1 = spring.map(OnlineClass::getProgress);
        Optional<Progress> progress2 = progress1.orElseThrow();

        //to-be
        Optional<Progress> progress = spring.flatMap(OnlineClass::getProgress); //두번 Optional깐것과 같다.

        Optional<OnlineClass> optional = springClass.stream().filter(c -> c.getTitle().startsWith("jpa")).findFirst();
        boolean present = spring.isPresent();
        System.out.println("present = " + present);

        /**
         * 값 가져오기 get()
         * 값이 있으면 상관없지만 없으면 문제
         */
        OnlineClass onlineClass = spring.get();
        System.out.println("onlineClass = " + onlineClass.getTitle());
        if (optional.isPresent()) {
            OnlineClass optionalClass = optional.get(); //NoSuchElementException
            System.out.println("optionalClass = " + optionalClass.getTitle());
        }

        /**
         * get보다 이것을 권장
         */
        optional.ifPresent(c->{
            System.out.println(c.getTitle());
        });
        OnlineClass onlineClass1 = optional.orElse(createNewClass());
        System.out.println("onlineClass1 = " + onlineClass1.getTitle());
        OnlineClass onlineClass2 = optional.orElseGet(App::createNewClass); //없음 새롭게 만들어라
        OnlineClass onlineClass3 = optional.orElseThrow(IllegalArgumentException::new); //없음 에러 던져라

        Optional<OnlineClass> onlineClass4 = optional.filter(OnlineClass::isClosed);
        System.out.println("onlineClass4 = " + onlineClass4.isPresent());
        //optional로 하면 Optional.empty가 나와서..
        Optional<Integer> i = spring.map(OnlineClass::getId);
        System.out.println("i = " + i.get());
    }

    private static OnlineClass createNewClass() {
        System.out.println("creating new online class");
        return new OnlineClass(10, "New class", false);
    }
}
