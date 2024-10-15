package org.example.objectcreate;

/**
 * ClassName:ObjectInitZeroTest
 * Package:org.example.objectcreate
 * Description: 对象创建测试 - 分配内存后初始化零值测试
 *
 * @Date:2024/10/12 16:53
 * @Author:qs@1.com
 */
public class ObjectInitZeroTest {
    public static void main(String[] args) {
        Example example = new Example();
    }

}

class Example {
    int a;
    boolean b;
    String c;
    static int staticInt;
    static String staticString;

    static {
        System.out.println("static block called");
        System.out.println("staticInt = " + staticInt);
        System.out.println("staticString = " + staticString);
    }

    public Example() {
        System.out.println("Constructor called");
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);
    }
}
