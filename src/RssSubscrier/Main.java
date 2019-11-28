package RssSubscrier;

import java.util.concurrent.TimeUnit;

public class Main
{
    public static void main(String[] args)
    {
        NewSystem system = new NewSystem("C:\\sources.txt");

        Thread t = new Thread(system);

        t.start();
        try
        {
            TimeUnit.MINUTES.sleep(1);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        system.shutdown();
    }
}
