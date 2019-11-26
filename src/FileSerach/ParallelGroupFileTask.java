package FileSerach;

import java.io.File;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ParallelGroupFileTask implements Runnable
{

    private final String fileName;

    private final ConcurrentLinkedDeque<File> directories;

    private final Result parallelResult;

    private boolean found;

    public ParallelGroupFileTask(String fileName, ConcurrentLinkedDeque<File> directories, Result parallelResult)
    {
        this.fileName = fileName;
        this.directories = directories;
        this.parallelResult = parallelResult;
        this.found = false;
    }

    public boolean isFound()
    {
        return found;
    }

    public void setFound(boolean found)
    {
        this.found = found;
    }

    @Override
    public void run()
    {

        while (directories.size() > 0)
        {
            File file = directories.poll();
            try
            {
                processDirectory(file, fileName, parallelResult);

                if (found)
                {
                    System.out.println("当前线程" + Thread.currentThread().getName());
                    System.out.println("当前路劲" + parallelResult.getPath());
                    return;
                }

            }
            catch (InterruptedException e)
            {
                System.out.println("当前线程" + Thread.currentThread().getName());
            }
        }

    }

    private void processDirectory(File file, String fileName, Result result) throws InterruptedException
    {
        File[] contents;
        contents = file.listFiles();

        if (contents == null || contents.length == 0)
        {
            return;
        }

        for (File content : contents)
        {
            if (content.isDirectory())
            {
                processDirectory(content, fileName, result);

                if (Thread.currentThread().isInterrupted())
                {
                    throw new InterruptedException();
                }
                if (found)
                {
                    return;
                }

            }
            else
            {
                processFile(content, fileName, result);
                if (Thread.currentThread().isInterrupted())
                {
                    throw new InterruptedException();
                }
            }
            if (found)
            {
                return;
            }

        }
    }

    private void processFile(File content, String fileName, Result result)
    {

        if (content.getName().equals(fileName))
        {
            result.setPath(content.getAbsolutePath());
            result.setIsFound(true);
            this.found=true;
            System.out.println("文件路径" + result.getPath());
            return;
        }
    }
}
