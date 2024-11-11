package org.example.commonpool;

/**
 * ClassName:TestIntern
 * Package:org.example.commonpool
 * Description: 测试 String.intern() 方法
 *
 * @Date:2024/11/9 16:22
 * @Author:qs@1.com
 */
public class TestIntern {
    public static void main(String[] args) {
        // 字符串常量池："计算机"和"技术"     堆内存：s1引用的对象"计算机技术"
        // 堆内存中还有个StringBuilder的对象，但是会被gc回收，StringBuilder的toString方法会new String()，这个String才是真正返回的对象引用
        String s1 = new StringBuilder("计算机").append("软件").toString(); // 没有出现"计算机技术"字面量，所以不会在常量池里生成"计算机技术"对象
        // "计算机技术" 在池中没有，但是在heap(堆)中存在，则intern时，会直接返回该heap中的引用
        System.out.println(s1.intern() == s1); // true

        // java 是关键字，所以 s2.intern() 返回的是 java 字符串常量池中的 java 字符串
        String s2 = new StringBuilder("ja").append("va").toString();
        System.out.println(s2.intern() == s2); // false

        String s3 = new String("test"); // s3 -> heap中的"test"对象
        // "test"作为字面量，放入了池中，而new时s3指向的是heap中新生成的string对象，s3.intern()指向的是"test"字面量之前在池中生成的字符串对象
        // s3.intern() -> 字符串常量池中的"test"对象
        System.out.println(s3.intern() == s3); // false
    }

}
