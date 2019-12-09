package Stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConcurrentDataLoader
{

    // 体会 流的转换

    public static List<Record> load(Path path) throws IOException
    {

        System.out.println("加载数据。。。。");

        List<String> lines = Files.readAllLines(path);

        //并行流处理所有的行  跳过第一行  每一行都用；切分 转化为 String【】 流  Record （stirng【】） 构造对象
        List<Record> records = lines.parallelStream().skip(1).map(data -> data.split(";")).map(t -> new Record(t)).collect(Collectors.toList());

        Map<String[], List<Record>> aa = records.stream().unordered().parallel().collect(Collectors.groupingByConcurrent(r -> r.getData()));

        IntStream stream = records.stream().filter(record -> record.getData().length == 5).mapToInt(r -> r.getData().length);

        stream.max().getAsInt();


        //reduce () collect() 末端操作

        return records;
    }


}
