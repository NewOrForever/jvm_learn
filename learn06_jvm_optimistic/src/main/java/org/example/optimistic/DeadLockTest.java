package org.example.optimistic;

/**
 * ClassName:DeadLockTest
 * Package:org.example.optimistic
 * Description: 死锁测试
 * 使用 jps 查看进程号
 * 使用 jstack 进程号 查看线程堆栈信息 -> 会看到死锁信息
 *
 * @Date:2024/10/25 13:41
 * @Author:qs@1.com
 */
public class DeadLockTest {
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock1) {
                try {
                    System.out.println("thread1 begin");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
                synchronized (lock2) {
                    System.out.println("thread1 end");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (lock2) {
                try {
                    System.out.println("thread2 begin");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
                synchronized (lock1) {
                    System.out.println("thread2 end");
                }
            }
        }).start();

        System.out.println("main thread end");
    }

}
