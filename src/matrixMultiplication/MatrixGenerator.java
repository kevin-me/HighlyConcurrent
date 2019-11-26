package matrixMultiplication;

import java.util.Random;

public class MatrixGenerator
{
    //随机生产 二维矩阵
    public static double[][] generator(int rows, int columns)
    {

        double[][] matrix = new double[rows][columns];

        Random ran = new Random();

        for (int i = 0; i < rows; i++)
        {

            for (int j = 0; j < columns; j++)
            {

                matrix[i][j] = ran.nextDouble() * 10;

            }
        }
        return matrix;

    }
}
