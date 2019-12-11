package sync;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class LockTask implements Runnable
{

    private static ReentrantLock lock = new ReentrantLock();

    private String name;

    @Override
    public void run()
    {
        try
        {
            lock.lock();
            System.out.println("23");

        }
        catch (Exception e)
        {

        }
        finally
        {
            lock.unlock();
        }

    }

    public static void main(String[] args)
    {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        for (int i = 0; i < 10; i++)
        {
            executor.execute(new LockTask());
        }
        executor.shutdown();
        try
        {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        }
        catch (Exception w)
        {

        }
    }


}
