package org.example.optimistic;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * ClassName:OOMTest
 * Package:org.example.optimistic
 * Description: OOM 测试
 * 内存溢出时自动导出 dump 文件，并使用 jvisualvm 工具分析 dump 文件
 *
 * @Date:2024/10/25 11:21
 * @Author:qs@1.com
 */
public class OOMTest {

    public static List<Object> list = new ArrayList<>();

    // JVM设置
    // -Xms10M -Xmx10M -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\jvm.dump
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (true) {
            list.add(new User(i++, UUID.randomUUID().toString()));
            new User(j--, UUID.randomUUID().toString());
        }
    }

}

class User {
    private int id;
    private String name;

    public User() {
    }

    public User(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
