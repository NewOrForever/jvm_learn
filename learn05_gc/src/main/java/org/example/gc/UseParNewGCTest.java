package org.example.gc;

/**
 * ClassName:UseParNewGCTest
 * Package:org.example.gc
 * Description: 使用 ParNew 收集器测试
 * -XX:+UseParNewGC：只设置 ParNew 收集器的话，老年代默认使用 SerialOld 收集器
 * -XX:+UseParNewGC -XX:+UseConcMarkSweepGC：设置 ParNew 收集器（新生代）和 CMS 收集器（老年代）
 *
 *
 * @Date:2024/10/18 9:57
 * @Author:qs@1.com
 */
public class UseParNewGCTest {
    public static void main(String[] args) {
        byte[] allocation1, allocation2/*, allocation3, allocation4, allocation5, allocation6*/;
        // 这个 90M 可以自行修改 - 按自己机器实际的 eden 区空间大小
        // 先拿这个 90M 先跑下看看 eden 区空间（因为eden 区会有一些基础开销至少几M内存，所以设置的对象占用内存空间要比 eden 区的小一些）
        // 设置后跑一遍 -> eden 区 used 占比要接近 100%
        allocation1 = new byte[90000 * 1024];

        allocation2 = new byte[10000 * 1024];

        /*allocation3 = new byte[1000 * 1024];
        allocation4 = new byte[1000 * 1024];
        allocation5 = new byte[1000 * 1024];
        allocation6 = new byte[1000 * 1024];*/
    }

}
