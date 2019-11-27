package Executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class ExecutorTest
{
    public static void main(String[] args) throws InterruptedException
    {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(5, 10,
                100, MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));
        //execute用于提交没有返回值的任务
        tpe.execute(new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    System.out.println("thread 1 execute work");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }));
        tpe.shutdown();
    }
}
