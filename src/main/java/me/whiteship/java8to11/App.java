package me.whiteship.java8to11;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {

        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        System.out.println("-----------spring 으로 시작하는 수업");
        springClasses.stream().filter(v -> v.getTitle().startsWith("spring"))
                .collect(Collectors.toList())
                .forEach(System.out::println);


        System.out.println("-----------close 되지 않은 수업");
        springClasses.stream().filter(Predicate.not(OnlineClass::isClosed))
                .forEach(v -> System.out.println(v.getId()));

        System.out.println("-----------수업 이름만 모아서 스트림 만들기");
        /*
            추가 - title boot 찾기 조건이 필요하니까.
            무조건 스트림을 사용하는게 아니고 이렇게 만들었을 때 어떤게 가독성이 좋은지 참고해서 쓰자
            아무래도 들여쓰기가 많아지면 불편하니까 짧은건 스트림으로 쓰는데 긴건 음... 모르겠네
            웹개발이 대부분 DB조회해서 뿌리는거니까 설계와 쿼리가 중요하긴한데 음... 몰라 ㅋㅋ 나중에 생각나면 쓰는거지 뭐
         */
        for (int i = 0; i < springClasses.size(); i++) {
            if (springClasses.get(i).getTitle().contains("boot")) {
                System.out.println(springClasses.get(i).getTitle());
            }
        }

        for (OnlineClass list : springClasses) {
            if (list.getTitle().contains("boot")) {
                System.out.println(list.getTitle());
            }
        }

        springClasses.stream().map(OnlineClass::getTitle)
                .filter(v -> v.contains("boot")).forEach(System.out::println);

        List<OnlineClass> javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6, "The Java, Test", true));
        javaClasses.add(new OnlineClass(7, "The Java, Code manipulation", true));
        javaClasses.add(new OnlineClass(8, "The Java, 8 to 11", false));

        List<List<OnlineClass>> keesunEvents = new ArrayList<>();
        keesunEvents.add(springClasses);
        keesunEvents.add(javaClasses);

        System.out.println("-----------두 수업 목록에 들어있는 모든 수업 아이디 출력");
        keesunEvents.stream().forEach(v -> v.stream().map(q -> q.getTitle()).forEach(System.out::println));
        keesunEvents.stream().flatMap(Collection::stream).forEach(v -> System.out.println(v.getId()));

        System.out.println("-----------10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만");
//        Stream.iterate(10, i-> + 1).forEach(System.out::println);  // 무한 1씩 증가
        Stream.iterate(10, i-> i + 1)
                .skip(10)
                .limit(10)
                .forEach(System.out::println);

        System.out.println("-----------자바 수업 중에 Test가 들어있는 수업이 있는지 확인");
        // anyMatch 한개라도 존재한다면 성공
        boolean a6 = javaClasses.stream().anyMatch(v -> v.getTitle().contains("Test"));// boolean
        System.out.println(a6);
//        javaClasses.stream().allMatch() // 전체 true false 인지

        System.out.println("-----------스프링 수업 중에 제목에 spring이 들어간 제목만 모아서 List로 만들기");
        springClasses.stream().filter(v -> v.getTitle().contains("spring")).forEach(v -> System.out.println(v.getTitle()));

        // filter, map 순서도 중요하다 리턴 타입이 바뀐다.
        List<String> collect = springClasses.stream()
                .filter(v -> v.getTitle().contains("spring"))
                .map(OnlineClass::getTitle)
                .collect(Collectors.toList());
        collect.forEach(System.out::println);

    }
}

/*
-----------spring 으로 시작하는 수업
OnlineClass{id=1, title='spring boot', closed=true}
OnlineClass{id=2, title='spring data jpa', closed=true}
OnlineClass{id=3, title='spring mvc', closed=false}
OnlineClass{id=4, title='spring core', closed=false}
-----------close 되지 않은 수업
3
4
5
-----------수업 이름만 모아서 스트림 만들기
spring boot
spring boot
spring boot
-----------두 수업 목록에 들어있는 모든 수업 아이디 출력
spring boot
spring data jpa
spring mvc
spring core
rest api development
The Java, Test
The Java, Code manipulation
The Java, 8 to 11
1
2
3
4
5
6
7
8
-----------10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만
20
21
22
23
24
25
26
27
28
29
-----------자바 수업 중에 Test가 들어있는 수업이 있는지 확인
true
-----------스프링 수업 중에 제목에 spring이 들어간 제목만 모아서 List로 만들기
spring boot
spring data jpa
spring mvc
spring core
spring boot
spring data jpa
spring mvc
spring core

Process finished with exit code 0



* */