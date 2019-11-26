package matrixMultiplication;

/**
 * 串行版本计算矩阵乘法
 */
public class SerialMutiplier
{
    //计算矩阵乘法
    public static void multipy(double[][] matrix1, double[][] matrix2, double[][] result)
    {
        int row1 = matrix1.length;
        int columns1 = matrix1[0].length;
        int columns2 = matrix2[0].length;

        for (int i = 0; i < row1; i++)
        {

            for (int j = 0; j < columns1; j++)
            {
                result[i][j] = 0;

                for (int k = 0; k < columns2; k++)
                {

                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }

            }
        }
    }

}
