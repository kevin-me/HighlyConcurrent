package wordMatch;

import java.util.ArrayList;
import java.util.List;

public class BestMatchingSerialCalculation
{
    public static BestMatchingData getBestMatchingWords(String words, List<String> dictionary)
    {
        List<String> results = new ArrayList<>();
        int minDistance = Integer.MAX_VALUE;
        int distance;
        for (String str : dictionary)
        {
            distance = LevenshteinDistance.calculate(words, str);
            if (distance < minDistance)
            {
                results.clear();
                minDistance = distance;
                results.add(str);
            }
            else if (distance == minDistance)
            {
                results.add(str);
            }
        }
        BestMatchingData result = new BestMatchingData();
        result.setMinDistance(minDistance);
        result.setWords(results);
        return result;
    }
}
