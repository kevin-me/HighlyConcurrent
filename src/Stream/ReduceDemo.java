package Stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReduceDemo
{

    public static void main(String[] args)
    {

        List<Integer> numList = Arrays.asList(1, 2, 3, 4, 5, 6);
        ArrayList<String> result = numList.stream().reduce(new ArrayList<String>(), (a, b) ->
        {
            a.add("element-" + Integer.toString(b));
            return a;
        }, (a, b) -> null);
        System.out.println(result);

    }
}
