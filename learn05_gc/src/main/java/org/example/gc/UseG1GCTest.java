package org.example.gc;

/**
 * ClassName:UseG1GCTest
 * Package:org.example.gc
 * Description: 使用 G1 收集器测试
 * -XX:+UseG1GC
 *
 * jvm 参数设置：-XX:+UseG1GC -XX:+PrintGCDetails -Xms8G -Xmx8G -XX:MaxGCPauseMillis=300
 * 这里设置了堆内存大小为 8G，最大暂停时间为 300ms
 * 每个 region 的大小是 8G/2048 = 4M，超过 region 的一半也就是 2M 就会被认为是大对象，被分配到 humongous region
 * 因为我这里每个 allocation 属性指向的对象都是超过 2M 的，所以这些大对象都会被分配到 humongous region
 *
 *
 * @Date:2024/10/25 9:35
 * @Author:qs@1.com
 */
public class UseG1GCTest {
    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6;
        allocation1 = new byte[1000000 * 1024];

        allocation2 = new byte[2000000 * 1024];
        /**
         * 释放 allocation2 对象，帮助 GC 回收
         * 如果这里不主动释放 allocation2 对象，那么就会导致 allocation2 指向的对象一直被引用着，不会被回收
         * allocation2 设置为 null 后，allocation2 原先指向的对象就没有再被引用了，这样再发生 GC 时，allocation2 指向的对象就会被回收
         */
        allocation2 = null;

        allocation3 = new byte[1000000 * 1024];
        allocation4 = new byte[100000 * 1024];
        allocation5 = new byte[100000 * 1024];
        allocation6 = new byte[100000 * 1024];
    }

}
