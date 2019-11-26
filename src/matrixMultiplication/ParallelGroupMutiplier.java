package matrixMultiplication;

import java.util.ArrayList;
import java.util.List;

public class ParallelGroupMutiplier
{
    public static void mutiply(double[][] matrix1, double[][] matrix2, double[][] result)
    {
        List<Thread> list = new ArrayList<>();

        int row1 = matrix1.length;

        int numThreads = Runtime.getRuntime().availableProcessors();

        int startIndex, endIndex, step;

        step = row1 / numThreads;
        startIndex = 0;
        endIndex = step;

        for (int i = 0; i < numThreads; i++)
        {
            GroupMutiplierTask task = new GroupMutiplierTask(result, matrix1, matrix2, startIndex, endIndex);

            Thread thread = new Thread(task);
            thread.start();
            list.add(thread);
            startIndex = endIndex;
            endIndex = i == numThreads - 2 ? row1 : endIndex + step;
        }

        for (Thread thread : list)
        {
            try
            {
                thread.join();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }


    }
}
