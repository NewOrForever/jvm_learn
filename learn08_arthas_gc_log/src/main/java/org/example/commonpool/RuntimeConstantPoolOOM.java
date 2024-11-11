package org.example.commonpool;

import java.util.ArrayList;

/**
 * ClassName:RuntimeConstantPoolOOM
 * Package:org.example.commonpool
 * Description: 运行时常量池导致的内存溢出异常
 * jvm 参数：-Xms10M -Xmx10M
 *
 * @Date:2024/11/9 16:24
 * @Author:qs@1.com
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            String str = String.valueOf(i).intern();
            list.add(str);
        }
    }

}
