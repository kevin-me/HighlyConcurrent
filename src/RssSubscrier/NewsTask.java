package RssSubscrier;

import java.util.Date;
import java.util.List;

public class NewsTask implements Runnable
{
    private String name;
    private String url;
    private NewsBuffer buffer;

    public NewsTask(String name, String url, NewsBuffer buffer)
    {
        this.name = name;
        this.url = url;
        this.buffer = buffer;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public NewsBuffer getBuffer()
    {
        return buffer;
    }

    public void setBuffer(NewsBuffer buffer)
    {
        this.buffer = buffer;
    }

    @Override
    public void run()
    {
        System.out.println(name + ":Running." + new Date());
        RSSDataCapturer capturer = new RSSDataCapturer(name);
        List<CommonInformationItem> items = capturer.load(url);

        for (CommonInformationItem item : items)
        {
            buffer.add(item);
        }
    }
}
