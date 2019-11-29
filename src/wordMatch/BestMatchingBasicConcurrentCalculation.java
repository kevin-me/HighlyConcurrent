package wordMatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class BestMatchingBasicConcurrentCalculation
{
    public static BestMatchingData getBestmatchingWords(String word, List<String> dictionary) throws Exception
    {

        int nunCores = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(nunCores);

        int size = dictionary.size();
        int step = size / nunCores;
        int startIndex, endIndex;
        List<Future<BestMatchingData>> results = new ArrayList<>();

        for (int i = 0; i < nunCores; i++)
        {
            startIndex = i * step;

            if (i == nunCores - 1)
            {
                endIndex = dictionary.size();
            }
            else
            {
                endIndex = (i + 1) * step;
            }

            BestMatchingBasicTask task = new BestMatchingBasicTask(startIndex, endIndex, dictionary, word);

            Future<BestMatchingData> future = executor.submit(task);
            results.add(future);
        }

        executor.shutdown();

        List<String> words = new ArrayList<>();
        int minDistance = Integer.MAX_VALUE;

        for (Future<BestMatchingData> future : results)
        {

            BestMatchingData data = future.get();

            if (data.getMinDistance() < minDistance)
            {
                words.clear();
                minDistance = data.getMinDistance();
                words.addAll(data.getWords());
            }
            else if (data.getMinDistance() == minDistance)
            {
                words.addAll(data.getWords());
            }
        }

        BestMatchingData result = new BestMatchingData();
        result.setMinDistance(minDistance);
        result.setWords(words);
        return result;
    }
}
