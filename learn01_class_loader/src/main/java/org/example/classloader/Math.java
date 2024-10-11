package org.example.classloader;

/**
 * ClassName:Math
 * Package:org.example.classloader
 * Description:
 *
 * @Date:2024/10/10 15:29
 * @Author:qs@1.com
 */
public class Math {
    public static final int initData = 666;
    public static User user = new User();

    public int compute() { // 一个方法对应一块栈帧内存区域
        int a = 1;
        int b = 2;
        int c = (a + b) * 10;
        return c;
    }

    public static void main(String[] args) {
        Math math = new Math();
        math.compute();
    }

}