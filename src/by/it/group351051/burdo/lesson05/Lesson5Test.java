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
        InputStream stream = new FileInputStream(root + "by/it/group351051/burdo/lesson05/dataA.txt");
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

}
