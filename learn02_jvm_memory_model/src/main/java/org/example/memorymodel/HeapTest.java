package org.example.memorymodel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger logger = LoggerFactory.getLogger(HeapTest.class);
    public static void main(String[] args) throws InterruptedException {
        try {
            ArrayList<HeapTest> heapTests = new ArrayList<>();
            while (true) {
                heapTests.add(new HeapTest());
                Thread.sleep(5);
            }
        } catch (Throwable e) {
            logger.error("堆内存测试异常 --------->", e);
        }
    }

}
