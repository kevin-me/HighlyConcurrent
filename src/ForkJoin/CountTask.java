package ForkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class CountTask extends RecursiveTask<Integer>
{
    private static final int THRESHOLD = 2;
    private int start;
    private int end;

    public CountTask(int start, int end)
    {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute()
    {
        int sum = 0;
        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute)
        { // 如果任务足够小，就计算任务
            for (int i = start; i <= end; i++)
            {
                sum += i;
            }
        }
        else
        { // 如果任务大于阈值，分裂成两个子任务执行
            int middle = (start + end) / 2;
            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle + 1, end);

            // 执行子任务
            leftTask.fork();
            rightTask.fork();

            // 等待子任务执行完，并得到其结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();

            // 合并子任务
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args)
    {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask countTask = new CountTask(1, 100);
        peekNextLocalTask();
        Future<Integer> result = forkJoinPool.submit(countTask);
        try
        {
            if (countTask.isCompletedAbnormally())
            {
                System.out.println(countTask.getException());
            }
            System.out.println(result.get());
        }
        catch (InterruptedException | ExecutionException e)
        {
            e.printStackTrace();
        }
    }

}
