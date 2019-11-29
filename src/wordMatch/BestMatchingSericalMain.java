package wordMatch;

import java.util.Date;
import java.util.List;

public class BestMatchingSericalMain
{
    public static void main(String[] args) throws Exception
    {
        //串行版本
       /* Date startTime, endTime;
        List<String> dictionary = WordsLoader.load("c://words.txt");
        System.out.println("Word:" + args[0]);

        startTime = new Date();
        BestMatchingData result = BestMatchingSerialCalculation.getBestMatchingWords(args[0], dictionary);
        List<String> results = result.getWords();
        endTime = new Date();

        results.forEach(System.out::println);
        System.out.println("Minimum Distance:" + result.getMinDistance());*/

        //并行版本
        //拆分的概念


        /* Date startTime, endTime;
        List<String> dictionary = WordsLoader.load("c://words.txt");
        System.out.println("Word:" + args[0]);

        startTime = new Date();
        BestMatchingData result = BestMatchingBasicConcurrentCalculation.getBestmatchingWords(args[0], dictionary);
        List<String> results = result.getWords();
        endTime = new Date();

        results.forEach(System.out::println);
        System.out.println("Minimum Distance:" + result.getMinDistance());*/

        //查询单词
        Date startTime, endTime;
        List<String> dictionary = WordsLoader.load("c://words.txt");
        System.out.println("Word:" + args[0]);

        startTime = new Date();
        Boolean result = ExistBasicConcurrentCalculation.getExistmatchingWords(args[0], dictionary);
        endTime = new Date();

        System.out.println("Minimum Distance:" + result);


    }
}
