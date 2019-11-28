package RssSubscrier;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

public class NewsBuffer
{
    //带有阻塞操作的并发数据结构 如果 null 调用方法的线程将被阻塞
    private LinkedBlockingDeque<CommonInformationItem> buffer;
    //HashMap 的并发实现 存储 id 和source
    private ConcurrentHashMap<String, String> storedItems;

    public NewsBuffer(LinkedBlockingDeque<CommonInformationItem> buffer, ConcurrentHashMap<String, String> storedItems)
    {
        this.buffer = buffer;
        this.storedItems = storedItems;
    }

    //添加方法
    public void add(CommonInformationItem item)
    {
        //插入
        storedItems.compute(item.getId(), (id, oldSource) ->
        {
            if (oldSource == null)
            {
                buffer.add(item);
                return item.getSource();
            }
            else
            {
                System.out.println("Item" + item.getId() + "has been processed before");
                return oldSource;
            }

        });
    }

    //获取
    public CommonInformationItem get() throws InterruptedException

    {
        return buffer.take();
    }

}
