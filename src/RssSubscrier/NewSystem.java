package RssSubscrier;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.*;

public class NewSystem implements Runnable
{
    private String route;

    private ScheduledThreadPoolExecutor executor;

    private NewsBuffer buffer;

    private CountDownLatch latch = new CountDownLatch(1);


    public NewSystem(String route)
    {
        this.route = route;
        executor = new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors());

        //带有阻塞操作的并发数据结构 如果 null 调用方法的线程将被阻塞
        LinkedBlockingDeque<CommonInformationItem> hh = new LinkedBlockingDeque();
        //HashMap 的并发实现 存储 id 和source
        ConcurrentHashMap<String, String> storedItems = new ConcurrentHashMap<String, String>();
        buffer = new NewsBuffer(hh, storedItems);
    }

    @Override
    public void run()
    {
        Path file = Paths.get(route);

        NewsWriter newsWriter = new NewsWriter(buffer);

        Thread t = new Thread(newsWriter);

        t.start();

        try
        {
            InputStream in = Files.newInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader((in)));

            String line = null;

            while ((line = reader.readLine()) != null)
            {

                String data[] = line.split(";");

                NewsTask task = new NewsTask(data[0], data[1], buffer);

                System.out.println("Task:" + task.getName());
                executor.scheduleWithFixedDelay(task, 0, 1, TimeUnit.MINUTES);

            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        synchronized (this)
        {
            try
            {
                latch.await();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        System.out.println("Shutting down the executor");
        executor.shutdown();
        t.isInterrupted();
        System.out.println("The System has finished");
    }

    public void shutdown()
    {
        latch.countDown();
    }
}
