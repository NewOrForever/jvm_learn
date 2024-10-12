package org.example.memorymodel;

import java.util.ArrayList;

/**
 * ClassName:HeapTest
 * Package:org.example.memorymodel
 * Description: 堆内存溢出测试 -> java.lang.OutOfMemoryError: Java heap space
 * 使用 jvisualvm 工具监控 GC 情况
 *
 * @Date:2024/10/12 14:40
 * @Author:qs@1.com
 */
public class HeapTest {
    byte[] a = new byte[1024 * 100]; // 100KB

    public static void main(String[] args) throws InterruptedException {
        ArrayList<HeapTest> heapTests = new ArrayList<>();
        while (true) {
            heapTests.add(new HeapTest());
            Thread.sleep(5);
        }
    }

}
