package optional;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        List<Double> result = new ArrayList<>();

//        ThreadLocalRandom
//                .current()
//                .doubles(10_000)
//                .boxed()
//                .forEach(
//                        d->NewMath.inv(d)
//                        .ifPresent(
//                                inv-> NewMath.sqrt(inv)
//                                .ifPresent(
//                                        sqrt->result.add(sqrt)
//                                )
//                        )
//                );
//
//        Function<Double, Stream<Double>> flatMapper = d -> NewMath.inv(d)
//                                                            .flatMap(inv -> NewMath.sqrt(inv))
//                                                            .map(sqrt -> Stream.of(sqrt))
//                                                            .orElseGet(()->Stream.empty());
//
//        List<Double> collect;
//        collect = ThreadLocalRandom.current().doubles(10000)
//                .parallel()
//                .boxed()
//                .flatMap(flatMapper).collect(Collectors.toList());
//
//
//        System.out.println(collect.size());


        List<People> people = new ArrayList<>();
        people.add(new People("adfa",3));
        people.add(new People("irkfm",16));
        people.add(new People("oelg",1));
        people.add(new People("lwosl",14));
        people.add(new People("awks",13));
        people.add(new People("ieow",166));
        people.add(new People("qoeks",13));
        people.add(new People("rgfgt",13));
        people.add(new People("jnbh",16));
        

        Comparator<People> comparator = Comparator.comparing(People::getId, Comparator.reverseOrder()).thenComparing(People::getName,Comparator.reverseOrder());

        List<People> collect = people.stream().sorted(comparator).collect(Collectors.toList());

        TreeSet<People> collect1 = people.stream().collect(Collectors.toCollection(() -> new TreeSet<>(comparator)));

        System.out.println(collect1);


//        List<People> sorted = collect1.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        System.out.println("#######################");

        collect.forEach(System.out::println);

        Map<Integer, List<People>> collect2 = people.stream().parallel().collect(Collectors.groupingBy(People::getId));
        collect2.entrySet().stream().forEach(System.out::println);
        Map<Integer, Long> collect3 = people.stream().parallel().collect(Collectors.groupingBy(p -> p.getId(), Collectors.counting()));
        collect3.entrySet().stream().forEach(System.out::println);

    }
}

