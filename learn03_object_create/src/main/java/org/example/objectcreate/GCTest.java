package org.example.objectcreate;

/**
 * ClassName:GCTest
 * Package:org.example.objectcreate
 * Description: 对象在 Eden 区分配内存测试
 * 当 Eden 区没有足够的空间进行分配时，虚拟机会发起一次 Minor GC
 * <p>
 * 测试：
 * 添加运行JVM参数：-XX:+PrintGCDetails
 * 1. 只分配 allocation1 内存，查看 GC 日志
 *      - 结果：allocation1 对象直接分配在 Eden 区且 Eden 区 used 占比接近 100%
 * 2. 分配 allocation1、allocation2 内存，查看 GC 日志
 *     - 结果：发生一次 Minor GC，allocation1 因为占用内存超过 survivor 区空间，所以直接进入老年代，allocation2 进入 survivor 区
 * 3. 分配 allocation1、allocation2、allocation3、allocation4、allocation5、allocation6 内存，查看 GC 日志
 *    - 结果：在给allocation2 分配内存时，发现 Eden 区空间不足，发起一次 Minor GC
 *          - allocation1 因为占用内存超过 survivor 区空间，所以直接进入老年代，allocation2 进入 survivor 区
 *          - minor gc 后继续给 allocation3、allocation4、allocation5、allocation6 分配内存，因为它们加起来的占用空间小于eden区的空间，所以直接分配在 eden 区（不发生minor gc）
 *
 * 设置大对象的大小：如果对象的大小超过了设置的阈值，那么这个对象会直接在老年代分配内存，不会在新生代分配内存
 * -XX:PretenureSizeThreshold=1000000（单位：字节） -XX:+UseSerialGC
 * 这个参数只对 Serial 和 ParNew 两款收集器有效
 *
 * @Date:2024/10/15 11:24
 * @Author:qs@1.com
 */
public class GCTest {
    public static void main(String[] args) throws InterruptedException {
        byte[] allocation1, allocation2/*, allocation3, allocation4, allocation5, allocation6*/;
        // 这个 90M 可以自行修改 - 按自己机器实际的 eden 区空间大小
        // 先拿这个 90M 先跑下看看 eden 区空间（因为eden 区会有一些基础开销至少几M内存，所以设置的对象占用内存空间要比 eden 区的小一些）
        // 设置后跑一遍 -> eden 区 used 占比要接近 100%
        allocation1 = new byte[90000 * 1024];

        /*allocation2 = new byte[8000 * 1024];*/

        /*allocation3 = new byte[1000 * 1024];
        allocation4 = new byte[1000 * 1024];
        allocation5 = new byte[1000 * 1024];
        allocation6 = new byte[1000 * 1024];*/
    }

}
