package by.it.group351051.burdo.lesson03;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertTrue;

public class Lesson3Test {
    /*
    для прохождения тестов создайте JUnit-конфигурацию на свой пакет:
    Поля:
    Name:               Test burdo
    Test kind:          All in package
    Package:            by.it.group351051.burdo
    Search for test:    In whole project
    */


    @Test
    public void A() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/group351051/burdo/lesson03/dataHuffman.txt");
        A_Huffman instance = new A_Huffman();
        String result = instance.encode(f);
        boolean ok=result.equals("01001100100111");
        assertTrue("A failed", ok);

        f = new File(root + "by/it/group351051/burdo/lesson03/dataHuffmanTest.txt");
        instance = new A_Huffman();
        result = instance.encode(f);
        ok=result.equals("111111010100011100001010011");
        assertTrue("A failed", ok);
    }

    @Test
    public void B() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/group351051/burdo/lesson03/encodeHuffman.txt");
        B_Huffman instance = new B_Huffman();
        String result = instance.decode(f);
        boolean ok=result.equals("abacabad");
        assertTrue("B failed", ok);

        f = new File(root + "by/it/group351051/burdo/lesson03/encodeHuffmanTest.txt");
        instance = new B_Huffman();
        result = instance.decode(f);
        ok=result.equals("helloWorld");
        assertTrue("B failed", ok);
    }
    @Test
    public void C() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group351051/burdo/lesson03/heapData.txt");
        C_HeapMax instance = new C_HeapMax();
        Long res=instance.findMaxValue(stream);
        boolean ok=(res==500);
        assertTrue("C failed", ok);

        stream = new FileInputStream(root + "by/it/group351051/burdo/lesson03/heapDataTest.txt");
        instance = new C_HeapMax();
        res=instance.findMaxValue(stream);
        ok=(res==400);
        assertTrue("C failed", ok);
    }

}
