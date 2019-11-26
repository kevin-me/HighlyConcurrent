package matrixMultiplication;

import java.util.Date;

public class SerialMain
{

    public static void main(String[] args)
    {

       double matrix1[][] = MatrixGenerator.generator(2000, 2000);
        double matrix2[][] = MatrixGenerator.generator(2000, 2000);
        double result[][] = new double[matrix1.length][matrix2[0].length];
       /*  Date start = new Date();
        SerialMutiplier.multipy(matrix1, matrix2, result);
        Date end = new Date();
        System.out.println("执行时间" + (end.getTime() - start.getTime()));*/

        Date start1 = new Date();
        ParallelGroupMutiplier.mutiply(matrix1, matrix2, result);
        Date end1 = new Date();


        System.out.println("执行时间" + (end1.getTime() - start1.getTime()));

    }
}
