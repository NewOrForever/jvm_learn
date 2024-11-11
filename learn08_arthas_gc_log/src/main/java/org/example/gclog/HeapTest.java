package org.example.gclog;

import java.util.ArrayList;

/**
 * ClassName:HeapTest
 * Package:org.example.arthas.gclog
 * Description:
 *
 * @Date:2024/11/9 14:28
 * @Author:qs@1.com
 */
public class HeapTest {
    byte[] a = new byte[1024 * 100];  //100KB
    public static void main(String[] args) throws InterruptedException {
        ArrayList<HeapTest> heapTests = new ArrayList<>();
        while (true) {
            heapTests.add(new HeapTest());
            Thread.sleep(10);
        }
    }

}
