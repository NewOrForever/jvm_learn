package org.example.objectcreate;

/**
 * ClassName:PointerCompressionDemo
 * Package:org.example.objectcreate
 * Description: 指针压缩测试
 * -XX:+UseCompressedOops 默认开启指针压缩
 * -XX:-UseCompressedOops 关闭指针压缩
 * 使用 jdk 自带的 jvisualvm 工具查看内存占用情况
 *
 * @Date:2024/10/15 8:44
 * @Author:qs@1.com
 */
public class PointerCompressionDemo {
    static class DemoObject {
        long id;
        String name;
    }

    public static void main(String[] args) {
        DemoObject[] array = new DemoObject[1000000]; // 创建 100万个对象

        for (int i = 0; i < array.length; i++) {
            array[i] = new DemoObject();
            array[i].id = i;
            array[i].name = "Object-" + i;
        }

        System.out.println("Array of objects created");
        try {
            Thread.sleep(1000000); // 为了便于观察内存使用情况
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
