package wordMatch;

/**
 * 计算两个字符串之间的距离
 */
public class LevenshteinDistance
{

    public static int calculate(String str, String target)
    {
        int d[][];              // 矩阵
        int n = str.length();
        int m = target.length();
        int i;                  // 遍历str的
        int j;                  // 遍历target的
        char ch1;               // str的
        char ch2;               // target的
        int temp;               // 记录相同字符,在某个矩阵位置值的增量,不是0就是1
        if (n == 0)
        {
            return m;
        }
        if (m == 0)
        {
            return n;
        }
        d = new int[n + 1][m + 1];
        // 初始化第一列
        for (i = 0; i <= n; i++)
        {
            d[i][0] = i;
        }
        // 初始化第一行
        for (j = 0; j <= m; j++)
        {
            d[0][j] = j;
        }
        for (i = 1; i <= n; i++)
        {
            // 遍历str
            ch1 = str.charAt(i - 1);
            // 去匹配target
            for (j = 1; j <= m; j++)
            {
                ch2 = target.charAt(j - 1);
                if (ch1 == ch2 || ch1 == ch2 + 32 || ch1 + 32 == ch2)
                {
                    temp = 0;
                }
                else
                {
                    temp = 1;
                }
                // 左边+1,上边+1, 左上角+temp取最小
                d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);
            }
        }
        return d[n][m];

    }

    private static int min(int i, int j, int k)
    {
        return Math.min(i, Math.min(j, k));
    }
}
