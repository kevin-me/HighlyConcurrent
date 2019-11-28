package RssSubscrier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RSSDataCapturer
{

    private String name;

    public RSSDataCapturer(String name)
    {
        this.name = name;
    }

    public List<CommonInformationItem> load(String url)
    {
        List<CommonInformationItem> ss = new ArrayList<>();
        CommonInformationItem news1 = new CommonInformationItem();
        news1.setDate("15643");
        news1.setDescription("dasda");
        news1.setFileName("kevin");
        news1.setId("wqewqfda");
        news1.setSource("fdefefgewfqdf");
        news1.setLink("dasdasdasdasda");

        ss.add(news1);
        return ss;
    }
}
