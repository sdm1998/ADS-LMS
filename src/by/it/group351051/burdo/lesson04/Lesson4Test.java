package by.it.group351051.burdo.lesson04;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class Lesson4Test {
    @Test
    public void A() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group351051/burdo/lesson04/dataA.txt");
        A_BinaryFind instance = new A_BinaryFind();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.findIndex(stream);
        //long finishTime = System.currentTimeMillis();
        StringBuilder sb=new StringBuilder();
        for (int index:result){
            sb.append(index).append(" ");
        }
        System.out.println(sb.toString().trim());
        boolean ok=sb.toString().trim().equals("3 1 -1 1 -1");
        assertTrue("A failed", ok);
    }

    @Test
    public void A2() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group351051/burdo/lesson04/dataATest.txt");
        A_BinaryFind instance = new A_BinaryFind();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.findIndex(stream);
        //long finishTime = System.currentTimeMillis();
        StringBuilder sb=new StringBuilder();
        for (int index:result){
            sb.append(index).append(" ");
        }
        System.out.println(sb.toString().trim());
        boolean ok=sb.toString().trim().equals("3 1 -1 -1 4");
        assertTrue("A failed", ok);
    }

}
