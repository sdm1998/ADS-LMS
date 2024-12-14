package by.it.group351051.antonvyra.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Реализуйте сортировку слиянием для одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо отсортировать полученный массив.

Sample Input:
5
2 3 9 2 9
Sample Output:
2 2 3 9 9
*/
public class B_MergeSort {

    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a=new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        mergSort(a,0,a.length-1);

        return a;
    }

    private void mergSort(int[] array, int left, int right){
        if (left<right){
            int midl = (left+right) / 2;
            mergSort(array, left, midl);
            mergSort(array, midl+1, right);

            // merge sorted sides
            merge(array, left, midl, right);
        }
    }

    private void merge(int[] array, int left, int midl, int right){
        int num_1 = midl - left +1;
        int num_2 = right - midl;
        int[] leftArr = new int[num_1];
        int[] rightArr = new int[num_2];

        // copy values in temp mass
        System.arraycopy(array, left, leftArr, 0, num_1);
        System.arraycopy(array, midl+1, rightArr, 0, num_2);

        int i=0, j=0, k=left;
        while(i < num_1 && j < num_2){
            if (leftArr[i] <= rightArr[j]){
                array[k] = leftArr[i];
                i++;
            } else{
                array[k] = rightArr[j];
                j++;
            }
            k++;
        }

        while (i < num_1){
            array[k] = leftArr[i];
            i++;
            k++;
        }
        while (j<num_2){
            array[k] = rightArr[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
