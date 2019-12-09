package Stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectDemo
{

    public static void main(String[] args)
    {

        List<Integer> collectList = Stream.of(1, 2, 3, 4)
                .collect(Collectors.toList());
        System.out.println("collectList: " + collectList);

        Set<Integer> collectSet = Stream.of(1, 2, 3, 3)
                .collect(Collectors.toSet());
        System.out.println("collectSet: " + collectSet);

        Optional<Integer> collectMaxBy = Stream.of(1, 2, 3, 4)
                .collect(Collectors.maxBy(Comparator.comparingInt(o -> o)));
        System.out.println("collectMaxBy:" + collectMaxBy.get());


        Map<Boolean, List<Integer>> collectParti = Stream.of(1, 2, 3, 4)
                .collect(Collectors.partitioningBy(it -> it % 2 == 0));
        System.out.println("collectParti : " + collectParti);


        Map<Boolean, List<Integer>> collectGroup= Stream.of(1, 2, 3, 4)
                .collect(Collectors.groupingBy(it -> it > 3));
        System.out.println("collectGroup : " + collectGroup);

    }
}
