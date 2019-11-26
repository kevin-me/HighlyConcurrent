package FileSerach;

import java.io.File;

/**
 * 查找文件串行版本
 */
public class SerialFileSearch
{
    public static void main(String[] args)
    {

        File file = new File("F://");

        Result result = new Result();

        searchFile(file, "课时6算法推导与案例.mp4", result);
    }

    public static void searchFile(File file, String fileName, Result result)
    {
        File[] contents;
        contents = file.listFiles();

        if (contents == null || contents.length == 0)
        {
            return;
        }

        for (File content : contents)
        {
            if (content.isDirectory())
            {
                searchFile(content, fileName, result);
            }
            else
            {
                if (content.getName().equals(fileName))
                {
                    result.setPath(content.getAbsolutePath());
                    result.setIsFound(true);
                    System.out.println("文件路径" + result.getPath());
                    return;
                }
            }
            if (result.getIsFound())
            {
                return;
            }

        }

    }

}
