package me.whiteship.java8to11;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class OptionalTest {
    public static void main(String[] args) {

        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        Optional<OnlineClass> optional = springClasses.stream()
                .filter(v -> v.getTitle().startsWith("spring"))
                .findFirst();
        boolean present = optional.isPresent();
        System.out.println(present);    // true

        Optional<OnlineClass> optional1 = springClasses.stream()
                .filter(v -> v.getTitle().startsWith("jpa"))
                .findFirst();
        boolean empty = optional1.isEmpty();  // isEmpty true false 뒤집기
        System.out.println(empty); // true

        System.out.println("---");

        OnlineClass onlineClass = optional.get();
        System.out.println(onlineClass.getTitle()); // spring boot

        /* 권장사항 */
        // 데이터 찾기
        optional.ifPresent(v -> System.out.println(v.getTitle())); // functional interface의 람다 실행

        // 데이터가 있으면 가져오고 없으면 다른걸 실행한다. 하지만 있건 없건 무조건 메소드는 실행된다.
        OnlineClass onlineClass1 = optional1.orElse(createNewClass());
        System.out.println(onlineClass1.getTitle()); // 해당코드가 있더라도 createnewClass() 메소드는 실행된다.

        // 데이터가 있으면 실행한다. 없으면! 실행된다.
        OnlineClass onlineClass2 = optional.orElseGet(OptionalTest::createNewClass);
        System.out.println(onlineClass2.getTitle()); // 해당코드가 있으면 실행을 안한다.

        // 데이터가 없으면 에러를 실행한다.
        OnlineClass onlineClass3 = optional.orElseThrow(IllegalStateException::new);
        System.out.println(onlineClass3);

        // 데이터를 걸러낸다. 있다는 가정하에 사용한다.
        Optional<OnlineClass> onlineClass4 = optional.filter(v -> v.isClosed());
        System.out.println(onlineClass4.isEmpty()); // spring 있음 : false

        // map
        Optional<Integer> integer = optional.map(v -> v.getId());
        System.out.println(integer.isPresent());

        // return type이 optional 이면 optional을 한번 벗겨준다.
        // optional.flatMap(OnlineClass::getTitle);

        // stream의 flatMap 1:1 매칭 input한개지만 output 이 여러개일때.


    }

    private static OnlineClass createNewClass() {
        System.out.println("createNewClass start");
        return new OnlineClass(10, "New class", false);
    }
}
