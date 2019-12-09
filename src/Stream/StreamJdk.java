package Stream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class StreamJdk
{
    public static void main(String[] args) throws Exception
    {
        Stream<Integer> s = Stream.of(1, 2, 3, 4, 5, 6);
        /**
         * 求和，也可以写成Lambda语法：
         * Integer sum = s.reduce((a, b) -> a + b).get();
         */
        Integer sum = s.reduce(new BinaryOperator<Integer>()
        {
            @Override
            public Integer apply(Integer integer, Integer integer2)
            {
                return integer + integer2;
            }
        }).get();

        System.out.println(sum);
        Stream<Integer> ss = Stream.of(1, 2, 3, 4, 5, 6);

        /**
         * 求最大值，也可以写成Lambda语法：
         * Integer max = s.reduce((a, b) -> a >= b ? a : b).get();
         */
        Integer max = ss.reduce(new BinaryOperator<Integer>()
        {
            @Override
            public Integer apply(Integer integer, Integer integer2)
            {
                return integer >= integer2 ? integer : integer2;
            }
        }).get();

        System.out.println(max);


        Stream<String> sss = Stream.of("test", "t1", "t2", "teeeee", "aaaa", "taaa");
        /**
         * 以下结果将会是：　[value]testt1t2teeeeeaaaataaa
         * 也可以使用Lambda语法：
         * System.out.println(s.reduce("[value]", (s1, s2) -> s1.concat(s2)));
         */
        System.out.println(sss.reduce("[value]", new BinaryOperator<String>()
        {
            @Override
            public String apply(String s, String s2)
            {
                return s.concat(s2);
            }
        }));
    }
}
