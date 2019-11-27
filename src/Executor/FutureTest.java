package Executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class FutureTest
{
    public static void main(String[] args) throws InterruptedException
    {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(5, 10,
                100, MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));

        Future<String> future = tpe.submit(new Callable<String>()
        {
            @Override
            public String call()
            {
                try
                {
                    String a = "return String";
                    return a;
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    return "exception";
                }
            }
        });
        try
        {
            System.out.println(future.get());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            tpe.shutdown();
        }
    }
}
