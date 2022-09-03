package moderJavaInaction.ch2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ch2Main {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(80, Color.GREEN),
                new Apple(155, Color.GREEN),
                new Apple(120, Color.RED));

        // [Apple{color=GREEN, weight=80}, Apple{color=GREEN, weight=155}]
        List<Apple> test1 = filterGreenApples(inventory);
        System.out.println(test1);

        // [Apple{color=GREEN, weight=155}, Apple{color=RED, weight=120}]
        List<Apple> test2 = filterApplesByWeight(inventory, 100);
        System.out.println(test2);

        // [Apple{color=GREEN, weight=80}, Apple{color=GREEN, weight=155}]
        List<Apple> greenApples = filterApplesByColor(inventory, Color.GREEN);
        System.out.println(greenApples);

        // [Apple{color=RED, weight=120}]
        List<Apple> redApples = filterApplesByColor(inventory, Color.RED);
        System.out.println(redApples);

        // [Apple{color=GREEN, weight=80}, Apple{color=GREEN, weight=155}]
        // 두번째 매개변수는 전략디자인패턴을 이용해서 만들었다.
        // AppleColorPredicate의 클래스에서 인터페이스를 호출받아 비교함.
        // 헤드퍼스트디자인패턴 1장에서 나오는건데 제대로 안읽어서 잘 기억이 안난다.
        List<Apple> greenApples2 = filter(inventory, new AppleColorPredicate());
        System.out.println(greenApples2);

        // [Apple{color=GREEN, weight=155}]
        List<Apple> heavyApples = filter(inventory, new AppleWeightPredicate());
        System.out.println(heavyApples);

        // []
        List<Apple> redAndHeavyApples = filter(inventory, new AppleRedAndHeavyPredicate());
        System.out.println("redAndHeavyApples ==> " + redAndHeavyApples);

        // [Apple{color=RED, weight=120}]
        List<Apple> redApples2 = filter(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple a) {
                return a.getColor() == Color.RED;
            }
        });
        System.out.println(redApples2);
    }

    // 칼라가 GREEN을 찾아서 다시 리스트에 담아서 리턴
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getColor() == Color.GREEN) {
                result.add(apple);
            }
        }
        return result;
    }

    // 사용자가 입력한 색상이 같으면 리스트에 담아서 리턴
    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getColor() == color) {
                result.add(apple);
            }
        }
        return result;
    }

    // 사용자가 입력한 무게보다 큰 값을 리스트에 담아서 리턴
    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > weight) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filter(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    enum Color {
        RED,
        GREEN
    }


    interface ApplePredicate {

        boolean test(Apple a);

    }

    static class AppleWeightPredicate implements ApplePredicate {

        @Override
        public boolean test(Apple apple) {
            return apple.getWeight() > 150;
        }

    }

    static class AppleColorPredicate implements ApplePredicate {

        @Override
        public boolean test(Apple apple) {
            return apple.getColor() == Color.GREEN;
        }

    }

    static class AppleRedAndHeavyPredicate implements ApplePredicate {

        @Override
        public boolean test(Apple apple) {
            return apple.getColor() == Color.RED && apple.getWeight() > 150;
        }

    }

}

