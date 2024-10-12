package org.example.classloader;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.security.ProtectionDomain;

/**
 * ClassName:MyClassLoaderTest02
 * Package:org.example.classloader
 * Description: 尝试打破双亲委派机制
 *
 * @Date:2024/10/11 10:49
 * @Author:qs@1.com
 */
public class MyClassLoaderTest02 {
    static class MyClassLoader02 extends ClassLoader {
        private String classPath;

        public MyClassLoader02(String classPath) {
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
        protected Class<?> loadClass(String name, boolean resolve)
                throws ClassNotFoundException {
            synchronized (getClassLoadingLock(name)) {
                // First, check if the class has already been loaded
                Class<?> c = findLoadedClass(name);
                if (c == null) {
                    // If still not found, then invoke findClass in order
                    // to find the class.
                    long t1 = System.nanoTime();
                    c = findClass(name);

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

    public static void main(String[] args) {
        /**
         * 最终结果会报错：java.lang.SecurityException: Prohibited package name: java.lang
         * 虽然我们重写了 loadClass 方法，打破了双亲委派机制，但是在 defineClass 方法中会检查类名，如果类名是以 java. 开头的，就会抛出异常
         * @see ClassLoader#preDefineClass(String, ProtectionDomain)
         * 通过该方式来兜底，在打破双亲委派机制的情况下，保护核心类库不被篡改
         */
        MyClassLoader02 classLoader = new MyClassLoader02("D:/test");
        try {
            // 尝试用自己改写的类加载机制去加载自己写的 java.lang.String.class
            Class<?> clazz = classLoader.loadClass("java.lang.String");
            Object obj = clazz.newInstance();
            Method soutMethod = clazz.getDeclaredMethod("sout", null);
            soutMethod.invoke(obj, null);
            System.out.println(clazz.getClassLoader().getClass().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
