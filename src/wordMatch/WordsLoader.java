package wordMatch;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class WordsLoader
{
    public static List<String> load(String s) throws Exception
    {
        List<String> data = new ArrayList<>();
        Path file = Paths.get(s);
        InputStream in = Files.newInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader((in)));

        String line = null;
        while ((line = reader.readLine()) != null)
        {
            data.add(line);
        }
        return data;
    }
}
