package org.example.classloader;

import sun.misc.Launcher;

import java.net.URL;

/**
 * ClassName:TestJDKClassLoader
 * Package:org.example.classloader
 * Description:
 *
 * @Date:2024/10/10 16:25
 * @Author:qs@1.com
 */
public class TestJDKClassLoader {
    public static void main(String[] args) {
        // 引导类加载器
        /**
         * 引导类加载器为什么打印出来是null？
         * 因为引导类加载器是用C++实现的，是虚拟机的一部分，它不是一个Java类，所以获取不到引导类加载器的引用
         */
        System.out.println("引导类加载器：" + String.class.getClassLoader());
        // 扩展类加载器
        System.out.println("扩展类加载器：" + com.sun.crypto.provider.DESKeyFactory.class.getClassLoader());
        // 应用类加载器
        System.out.println("应用类加载器：" + TestJDKClassLoader.class.getClassLoader());

        System.out.println("-----------------");
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader extClassLoader = appClassLoader.getParent();
        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        System.out.println("引导类加载器 bootstrapClassLoader：" + bootstrapClassLoader);
        System.out.println("扩展类加载器 extClassLoader：" + extClassLoader);
        System.out.println("应用类加载器 appClassLoader：" + appClassLoader);

        System.out.println("-----------------");
        System.out.println("bootstrapLoader引导类加载器加载以下文件：");
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urLs) {
            System.out.println(url);
        }

        System.out.println("-----------------");
        System.out.println("extClassLoader扩展类加载器加载以下文件：");
        /**
         * @see Launcher.ExtClassLoader#getExtDirs()
         */
        System.out.println(System.getProperty("java.ext.dirs"));

        System.out.println("-----------------");
        System.out.println("appClassLoader应用类加载器加载以下文件：");
        /**
         * @see Launcher.AppClassLoader#getAppClassLoader(ClassLoader)
         */
        System.out.println(System.getProperty("java.class.path"));
    }

}
