package me.whiteship.java8to11;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTime {
    public static void main(String[] args) {

//        https://codeblog.jonskeet.uk/2017/04/23/all-about-java-util-date/
//        https://docs.oracle.com/javase/tutorial/datetime/overview/index.html
//        https://docs.oracle.com/javase/tutorial/datetime/iso/overview.html


        Instant instant = Instant.now();
        System.out.println(instant);    // 기준시간 UTC, GMT - 2022-06-18T09:51:31.950245Z
        System.out.println(instant.atZone(ZoneId.of("UTC")));    // 원하는시간 - 2022-06-18T09:51:31.950245Z[UTC]

        // 내 컴퓨터의 로컬 시간
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = instant.atZone(zoneId);
        System.out.println(zonedDateTime);  // 2022-06-18T18:51:31.950245+09:00[Asia/Seoul]

        // 서버시간이 나온다. 로컬이 한국이지만 서버가 미국에 배포가되면 미국의 시간이 나온다.
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);    // 2022-06-18T18:51:31.971202800
        LocalDateTime birthDay = LocalDateTime.of(1992, Month.APRIL, 30, 0, 0);

        Instant nowInstant = Instant.now();
        ZonedDateTime zonedDateTime1 = nowInstant.atZone(ZoneId.of("Asia/Seoul"));
        System.out.println(zonedDateTime1); // 2022-06-18T18:51:31.971694900+09:00[Asia/Seoul]

        // 기간
        LocalDate today = LocalDate.now();
        LocalDate thisYearBirthDay = LocalDate.of(2022, Month.OCTOBER, 15);

        Period period = Period.between(today, thisYearBirthDay);
        System.out.println(period.getDays());   // 남은날짜 27

        Period until = today.until(thisYearBirthDay);   // 언제까지
        System.out.println(until.get(ChronoUnit.DAYS)); // 27 차이가 얼마나 나는지

        // Instant를 가지고 비교한다. (머신용)
        Instant instant1 = Instant.now();
        Instant plus = instant1.plus(10, ChronoUnit.SECONDS);
        Duration between = Duration.between(instant1, plus);
        System.out.println(between.getSeconds());   // 10 차이가 얼마나 나는지 비교

        // formatter
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime);   // 2022-06-18T19:07:58.100275800
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println(dateTime.format(formatter)); // 06/18/2022

        LocalDate parse = LocalDate.parse("07/15/1990", formatter);
        System.out.println(parse);  // 1990-07-15




    }
}
