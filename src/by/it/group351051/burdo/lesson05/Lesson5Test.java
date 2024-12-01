package by.it.group351051.burdo.lesson05;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class Lesson5Test {
    @Test
    public void A() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group351051/burdo/lesson05/dataC.txt");
        A_QSort instance = new A_QSort();
        int[] result=instance.getAccessory(stream);
        boolean ok=Arrays.equals(result,new int[]{1,0,0});
        assertTrue("A failed", ok);
    }

    @Test
    public void A2() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group351051/burdo/lesson05/dataATest.txt");
        A_QSort instance = new A_QSort();
        int[] result=instance.getAccessory(stream);
        boolean ok=Arrays.equals(result,new int[]{3, 0, 2, 0, 3});
        assertTrue("A failed", ok);
    }


    @Test
    public void B1() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group351051/burdo/lesson05/dataB.txt");
        B_CountSort instance = new B_CountSort();
        int[] result=instance.countSort(stream);
        boolean ok=Arrays.equals(result,new int[]{2,2,3,9,9});
        assertTrue("B failed", ok);
    }

    @Test
    public void B2() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group351051/burdo/lesson05/dataBTest.txt");
        B_CountSort instance = new B_CountSort();
        int[] result=instance.countSort(stream);
        boolean ok=Arrays.equals(result,new int[]{1,2,2,3,4,9,9,88,999});
        assertTrue("B failed", ok);
    }


    @Test
    public void C() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group351051/burdo/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        boolean ok=Arrays.equals(result,new int[]{1,0,0});
        assertTrue("C failed", ok);
    }

}
