package org.example.classloader;

import java.io.FileInputStream;
import java.lang.reflect.Method;

/**
 * ClassName:MyClassLoaderTest
 * Package:org.example.classloader
 * Description:
 *
 * @Date:2024/10/11 10:49
 * @Author:qs@1.com
 */
public class MyClassLoaderTest {
    static class MyClassLoader extends ClassLoader {
        private String classPath;

        public MyClassLoader(String classPath) {
            this.classPath = classPath;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] data = loadByte(name);
                // defineClass将一个字节数组转为Class对象，这个字节数组是class文件读取后最终的字节数组
                return defineClass(name, data, 0, data.length);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ClassNotFoundException();
            }
        }

        private byte[] loadByte(String name) throws Exception {
            /*name = name.replaceAll("\\.", "/");
            FileInputStream fis = new FileInputStream(classPath + "/" + name + ".class");*/
            String path = name.replace('.', '/').concat(".class");
            // 读取class字节码文件
            FileInputStream fis = new FileInputStream(classPath + "/" + path);
            int len = fis.available();
            byte[] data = new byte[len];
            fis.read(data);
            fis.close();
            return data;
        }
    }

    public static void main(String[] args) {
        /**
         * 初始化自定义类加载器，会先初始化父类ClassLoader，其中会把自定义类加载器的父加载器设置为应用程序类加载器AppClassLoader
         *  - 先初始化父类ClassLoader {@link java.lang.ClassLoader#ClassLoader()}
         *      - {@link ClassLoader#getSystemClassLoader()} 返回 AppClassLoader
         *  - {@link ClassLoader#ClassLoader(Void, ClassLoader)} 将 AppClassLoader 设置为自定义类加载器的父加载器
          */
        MyClassLoader classLoader = new MyClassLoader("D:/test");
        try {
            // D盘创建 test/org/example/classloader 几级目录，将User类的复制类User1.class丢入该目录
            // 注意：如果你是在将 User.java 复制为 User1.java 后，再编译为 User1.class，那么 User1.class 丢入 test/org/example/classloader 后
            // 需要将 User1.java 删除，否则最终 User1.class 会被 AppClassLoader 加载，而不是 MyClassLoader 加载
            Class<?> clazz = classLoader.loadClass("org.example.classloader.User1");
            Object obj = clazz.newInstance();
            Method soutMethod = clazz.getDeclaredMethod("sout", null);
            soutMethod.invoke(obj, null);
            System.out.println(clazz.getClassLoader().getClass().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
