package org.example.objectcreate;

/**
 * ClassName:ReferenceCountingGc
 * Package:org.example.objectcreate
 * Description: 对象内存回收测试 - 引用计数法
 * 引用计数法：给对象添加一个引用计数器，每当有一个地方引用它时，计数器值加 1，当引用失效时，计数器值减 1，任何时刻计数器值为 0 的对象就是不可能再被使用的
 * 优点：实现简单，判定效率高
 * 缺点：循环引用问题，无法解决，所以，Java 虚拟机不使用引用计数法来判断对象是否存活
 *
 * @Date:2024/10/15 14:56
 * @Author:qs@1.com
 */
public class ReferenceCountingGc {
    private Object instance = null;
    public static void main(String[] args) {
        ReferenceCountingGc objA = new ReferenceCountingGc();
        ReferenceCountingGc objB = new ReferenceCountingGc();
        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        // 假设这里发生 GC，objA 和 objB 是否能被回收？
        // 答案：不能，因为 objA 和 objB 互相引用，导致引用计数器值不为 0
        // 但是，Java 虚拟机不使用引用计数法来判断对象是否存活，所以，objA 和 objB 会被回收
        // System.gc();
    }

}
