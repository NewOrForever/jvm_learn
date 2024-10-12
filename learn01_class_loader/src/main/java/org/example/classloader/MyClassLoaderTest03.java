package org.example.classloader;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.security.ProtectionDomain;

/**
 * ClassName:MyClassLoaderTest03
 * Package:org.example.classloader
 * Description: 模拟实现tomcat 的 WebAppClassLoader 加载自己 war 包应用内不同版本类，实现相互共存与隔离
 * 也是打破了双亲委派机制
 *
 * @Date:2024/10/11 10:49
 * @Author:qs@1.com
 */
public class MyClassLoaderTest03 {
    static class MyClassLoader03 extends ClassLoader {
        private String classPath;

        public MyClassLoader03(String classPath) {
            this.classPath = classPath;
        }

        /**
         * 重写类加载方法，实现自己的加载逻辑，不委派给双亲加载
         *
         * @param name    The <a href="#name">binary name</a> of the class
         * @param resolve If <tt>true</tt> then resolve the class
         * @return
         * @throws ClassNotFoundException
         */
        @Override
        protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
            synchronized (getClassLoadingLock(name)) {
                // First, check if the class has already been loaded
                Class<?> c = findLoadedClass(name);
                if (c == null) {
                    // If still not found, then invoke findClass in order
                    // to find the class.
                    long t1 = System.nanoTime();
                    if (name.startsWith("org.example.classloader")) {
                        c = findClass(name);
                    } else {
                        /**
                         * 非自定义的类还是走双亲委派加载
                         * 这里为什么要加这个判断呢？
                         *  - 因为所有的类都 extends Object，Object 类是 java 核心类库中的类，由 BootstrapClassLoader 加载
                         *  - 如果不加这个判断，那么 Object 就会由我们自定义的类加载器 MyClassLoader03 加载，这样就会报错
                         *      java.io.FileNotFoundException: D:\test\java\lang\Object.class (系统找不到指定的文件)
                         *  - 所以这里加了这个判断，如果不是自定义的类，还是走双亲委派机制
                         */
                        c = this.getParent().loadClass(name);
                    }

                    // this is the defining class loader; record the stats
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                }
                if (resolve) {
                    resolveClass(c);
                }
                return c;
            }
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

    public static void main(String[] args) throws Exception {
        MyClassLoader03 classLoader = new MyClassLoader03("D:/test");
        Class<?> clazz = classLoader.loadClass("org.example.classloader.User1");
        Object obj = clazz.newInstance();
        Method soutMethod = clazz.getDeclaredMethod("sout", null);
        soutMethod.invoke(obj, null);
        System.out.println(clazz.getClassLoader());

        MyClassLoader03 classLoader1 = new MyClassLoader03("D:/test1");
        Class<?> clazz1 = classLoader1.loadClass("org.example.classloader.User1");
        Object obj1 = clazz1.newInstance();
        Method soutMethod1 = clazz1.getDeclaredMethod("sout", null);
        soutMethod1.invoke(obj1, null);
        System.out.println(clazz1.getClassLoader());

    }

}
