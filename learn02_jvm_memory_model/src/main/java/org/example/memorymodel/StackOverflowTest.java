package org.example.memorymodel;

/**
 * ClassName:StackOverFlowTest
 * Package:org.example.memorymodel
 * Description: 线程栈溢出测试
 * 线程栈大小可以通过 -Xss 参数设置：-Xss512k
 * 线程栈大小默认是 1M
 *
 * 测试：
 * - 这里的 redo() 方法会一直递归调用自己，直到栈溢出
 * - 通过 -Xss 参数设置线程栈大小，可以看到 count 的值不一样
 * 结果：
 * -Xss128k -> count: 1090
 * 使用默认值（1M）-> count: 23526
 * 结论：线程栈大小设置越小，能分配的栈帧数量就越少（方法调用的次数就越少）
 *
 * @Date:2024/10/12 13:39
 * @Author:qs@1.com
 */
public class StackOverflowTest {
    static int count = 0;
    static void redo() {
        count++;
        redo();
    }

    public static void main(String[] args) {
        try {
            redo();
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("count：" + count);
        }
    }

}
