package org.example.classloader;

/**
 * ClassName:User
 * Package:org.example.classloader
 * Description:
 *
 * @Date:2024/10/10 15:30
 * @Author:qs@1.com
 */
public class User {
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

    public void sout() {
        System.out.println("=======自己的加载器加载类调用方法=======");
    }

}
