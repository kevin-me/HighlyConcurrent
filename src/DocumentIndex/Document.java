package DocumentIndex;

import java.util.HashMap;
import java.util.Map;

public class Document
{
    private String fileName;

    private Map<String, Integer> map;

    public Document(String fileName, Map<String, Integer> map)
    {
        this.fileName = fileName;
        this.map = map;
    }

    public Document()
    {
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public Map<String, Integer> getMap()
    {
        return map;
    }

    public void setMap(Map<String, Integer> map)
    {
        this.map = map;
    }
}
