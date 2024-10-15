package org.example.objectcreate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * ClassName:OOMTest
 * Package:org.example.objectcreate
 * Description: 测试下对象回收时 finalize 方法的调用
 * <b>finalize 方法：官方不推荐使用，这里只是为了测试 finalize 方法的调用
 * 实际业务中不要使用<b/>
 *
 * @Date:2024/10/15 15:18
 * @Author:qs@1.com
 */
public class OOMTest {
    public static List<Object> list = new ArrayList<>();
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (true) {
            list.add(new UserFinalizeTest(i++, UUID.randomUUID().toString()));
            // 设置需要回收的对象的id为负数
            new UserFinalizeTest(j--, UUID.randomUUID().toString());
        }
    }

    static class UserFinalizeTest {
        private int id;
        private String name;

        public UserFinalizeTest() {
        }

        public UserFinalizeTest(int id, String name) {
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

        // User类需要重写finalize方法
        @Override
        protected void finalize() throws Throwable {
            OOMTest.list.add(this);
            System.out.println("关闭资源，userid=" + id + "即将被回收");
        }
    }

}
