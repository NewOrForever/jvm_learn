package org.example.optimistic;

/**
 * ClassName:MathMockCpuSoaring
 * Package:org.example.optimistic
 * Description: 模拟 CPU 高占用
 *
 * @Date:2024/10/25 15:42
 * @Author:qs@1.com
 */
public class MathMockCpuSoaring {
    public static final int initData = 666;

    public int compute() {  // 一个方法对应一块栈帧内存区域
        int a = 1;
        int b = 2;
        int c = (a + b) * 10;
        return c;
    }

    public static void main(String[] args) {
        MathMockCpuSoaring math = new MathMockCpuSoaring();
        while (true){
            math.compute();
        }
    }

}
