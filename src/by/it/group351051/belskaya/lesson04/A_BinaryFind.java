package by.it.group351051.belskaya.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class A_BinaryFind {
    int[] findIndex(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);

        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 1; i <= n; i++) {
            a[i - 1] = scanner.nextInt();
        }

        int k = scanner.nextInt();
        int[] result = new int[k];

        for (int i = 0; i < k; i++) {
            int value = scanner.nextInt();
            int left = 0;
            int right = a.length - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;

                if (a[mid] == value) {
                    result[i] = mid + 1;
                    break;
                } else if (a[mid] < value) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            if (left > right) {
                result[i] = -1;
            }
        }

        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group351051/belskaya/dataA.txt");
        A_BinaryFind instance = new A_BinaryFind();
        //long startTime = System.currentTimeMillis();
        int[] result = instance.findIndex(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

}
