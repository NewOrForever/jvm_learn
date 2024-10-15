package org.example.objectcreate;

import org.openjdk.jol.info.ClassLayout;

/**
 * ClassName:JOLSample
 * Package:org.example.objectcreate
 * Description: JOL 示例
 * JOL（Java Object Layout）是一个开源的 Java 对象布局分析工具
 * JOL 是一个小巧的工具，可以用来查看对象在内存中的布局
 *
 * @Date:2024/10/14 16:55
 * @Author:qs@1.com
 */
public class JOLSample {
    public static void main(String[] args) {
        ClassLayout classLayout = ClassLayout.parseInstance(new Object());
        System.out.println(classLayout.toPrintable());

        ClassLayout classLayout1 = ClassLayout.parseInstance(new int[]{});
        System.out.println(classLayout1.toPrintable());

        ClassLayout classLayout2 = ClassLayout.parseInstance(new A());
        System.out.println(classLayout2.toPrintable());
    }

    // -XX:+UseCompressedOops           默认开启的压缩所有指针
    // -XX:+UseCompressedClassPointers  默认开启的压缩对象头里的类型指针Klass Pointer
    // Oops : Ordinary Object Pointers
    public static class A {
        // 8B mark word
        // 4B Klass Pointer   如果关闭压缩-XX:-UseCompressedClassPointers或-XX:-UseCompressedOops，则占用8B
        int id;        // 4B
        String name;   // 对象医用 - 4B  如果关闭压缩-XX:-UseCompressedOops，则占用8B
        byte b;        // 1B
        Object o;      // 对象引用 - 4B  如果关闭压缩-XX:-UseCompressedOops，则占用8B
    }

}
