package wordMatch;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class ExistBasicConcurrentCalculation
{
    public static boolean getExistmatchingWords(String word, List<String> dictionary) throws InterruptedException, ExecutionException
    {

        int nunCores = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(nunCores);

        int size = dictionary.size();
        int step = size / nunCores;
        int startIndex, endIndex;
        List<ExistBasicTask> results = new ArrayList<>();

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

            ExistBasicTask task = new ExistBasicTask(startIndex, endIndex, dictionary, word);
            results.add(task);
        }
        Boolean result = false;
        try
        {
            result = executor.invokeAny(results);

            return result;
        }
        catch (ExecutionException e)
        {
            if (e.getCause() instanceof NoSuchElementException)
            {
                return false;
            }
        }
        finally
        {
            executor.shutdown();

            return result;
        }
    }
}
