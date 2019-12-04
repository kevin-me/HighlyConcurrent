package DocumentIndex;

import java.io.File;
import java.util.Date;
import java.util.concurrent.*;

public class Concurrentiindexing
{
    public static void main(String[] args)
    {
        int numConres = Runtime.getRuntime().availableProcessors();


        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Math.max(numConres - 1, 1));

        ExecutorCompletionService<Document> completionService = new ExecutorCompletionService<>(executor);

        ConcurrentHashMap<String, ConcurrentLinkedDeque<String>> IncertedIndex = new ConcurrentHashMap<>();

        File source = new File("//data");
        File[] files = source.listFiles();

        for (File file : files)
        {
            IndexingTask task = new IndexingTask(file);

            completionService.submit(task);
            if (executor.getQueue().size() > 1000)
            {
                do
                {
                    try
                    {
                        TimeUnit.MICROSECONDS.sleep(50);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                } while (executor.getQueue().size() > 1000);
            }
        }

        InvertedIndexTask invertedIndexTask = new InvertedIndexTask(completionService, IncertedIndex);
        Thread thread = new Thread(invertedIndexTask);

        InvertedIndexTask invertedIndexTask2 = new InvertedIndexTask(completionService, IncertedIndex);
        Thread thread2 = new Thread(invertedIndexTask2);

        thread.start();
        thread2.start();

        executor.shutdown();
        try
        {
            executor.awaitTermination(1, TimeUnit.DAYS);
            thread.isInterrupted();
            thread2.isInterrupted();
            thread.join();
            thread2.join();


        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
