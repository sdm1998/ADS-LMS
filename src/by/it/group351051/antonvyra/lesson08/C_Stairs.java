package by.it.group351051.antonvyra.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Даны число 1<=n<=100 ступенек лестницы и
целые числа −10000<=a[1],…,a[n]<=10000, которыми помечены ступеньки.
Найдите максимальную сумму, которую можно получить, идя по лестнице
снизу вверх (от нулевой до n-й ступеньки), каждый раз поднимаясь на
одну или на две ступеньки.

Sample Input 1:
2
1 2
Sample Output 1:
3

Sample Input 2:
2
2 -1
Sample Output 2:
1

Sample Input 3:
3
-1 2 1
Sample Output 3:
3

*/

public class C_Stairs {

    int getMaxSum(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] stairs = new int[n];

        for (int i = 0; i < n; i++) {
            stairs[i] = scanner.nextInt();
        }

        // Base case initialization
        if (n == 1) {
            return stairs[0]; // Only one step, return its value
        }

        // DP array to store the maximum sum at each step
        int[] dp = new int[n];

        // Initialize base cases
        dp[0] = stairs[0];  // The sum to reach the first step is its value
        dp[1] = Math.max(stairs[0] + stairs[1], stairs[1]);  // You can either jump from step 0 to step 1, or start at step 1 directly

        // Calculate the maximum sum for each subsequent step
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2]) + stairs[i];
        }

        // The result will be the maximum sum to reach the last step
        return dp[n - 1];
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson08/dataC.txt");
        C_Stairs instance = new C_Stairs();
        int res=instance.getMaxSum(stream);
        System.out.println(res);
    }

}
