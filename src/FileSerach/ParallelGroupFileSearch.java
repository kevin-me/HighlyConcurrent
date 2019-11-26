package FileSerach;

import java.io.File;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ParallelGroupFileSearch
{

    public static void main(String[] args)
    {

        File file = new File("F://");

        Result result = new Result();

        searchFiles(file, "课时6算法推导与案例.mp4", result);
    }

    public static void searchFiles(File file, String fileName, Result parallelResult)
    {

        ConcurrentLinkedDeque<File> diectoies = new ConcurrentLinkedDeque<>();

        File[] files = file.listFiles();

        for (File f : files)
        {
            if (f.isDirectory())
            {
                diectoies.add(f);
            }
        }

        int numThreads = Runtime.getRuntime().availableProcessors();

        Thread[] threads = new Thread[numThreads];

        ParallelGroupFileTask[] tasks = new ParallelGroupFileTask[numThreads];


        for (int i = 0; i < numThreads; i++)
        {
            tasks[i] = new ParallelGroupFileTask(fileName, diectoies, parallelResult);
            threads[i] = new Thread(tasks[i]);
            threads[i].start();
        }

        boolean finish = false;

        int numFinished = 0;

        while (!finish)
        {
            numFinished = 0;
            for (int i = 0; i < threads.length; i++)
            {
                if (threads[i].getState() == Thread.State.TERMINATED)
                {

                    numFinished++;
                    if (tasks[i].isFound())
                    {
                        finish = true;
                    }
                }
            }

        }

        if (numFinished == threads.length)
        {
            finish = true;
        }
        if (numFinished != threads.length)
        {
            for (Thread thread : threads)
            {
                thread.isInterrupted();
            }
        }
    }


}
