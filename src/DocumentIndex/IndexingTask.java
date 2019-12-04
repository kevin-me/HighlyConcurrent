package DocumentIndex;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class IndexingTask implements Callable<Document>
{
    private File file;

    public IndexingTask(File file)
    {
        this.file = file;
    }

    @Override
    public Document call() throws Exception
    {
        DocumentParser parser= new DocumentParser();

        Map<String,Integer> map = parser.parse(file.getAbsolutePath());

        Document document= new Document();

        document.setFileName(file.getName());
        document.setMap(map);


        return document;
    }
}
