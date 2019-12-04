package DocumentIndex;

import java.util.Map;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Future;

public class InvertedIndexTask implements Runnable
{
    private CompletionService<Document> completionService;

    private ConcurrentHashMap<String, ConcurrentLinkedDeque<String>> IncertedIndex;


    public InvertedIndexTask(CompletionService<Document> completionService, ConcurrentHashMap<String, ConcurrentLinkedDeque<String>> incertedIndex)
    {
        this.completionService = completionService;
        IncertedIndex = incertedIndex;
    }

    @Override
    public void run()
    {
        while (!Thread.interrupted())
        {
            try
            {

                Document document = completionService.take().get();
                updateInvertedIndex(document.getMap(), IncertedIndex, document.getFileName());

            }
            catch (Exception e)
            {
                break;
            }
        }

        while (true)
        {
            Future<Document> future = completionService.poll();

            if (future == null)
            {
                try
                {
                    break;
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }

    }

    private void updateInvertedIndex(Map<String, Integer> map, ConcurrentHashMap<String, ConcurrentLinkedDeque<String>> incertedIndex, String filename)
    {
        for (String word : map.keySet())
        {
            if (word.length() >= 3)
            {
                incertedIndex.computeIfAbsent(word, k -> new ConcurrentLinkedDeque<>()).add(filename);
            }

        }

    }

}
