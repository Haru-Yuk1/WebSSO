package cache;

import entity.User;

import java.util.ArrayList;
import java.util.HashMap;

public class SystemCache {
    //创建 SystemCache 的一个对象，这里是一个单例
    private static SystemCache instance = new SystemCache();
    private static ArrayList<User> registeredUsers = new ArrayList<User>();
    //让构造函数为 private，这样该类就不会被实例化
    private SystemCache() {
    }
    //获取唯一可用的对象
    public static SystemCache getInstance() {
        return instance;
    }

    public static ArrayList<User> getRegisteredUsers() {
        return registeredUsers;
    }

    public static void setRegisteredUsers(ArrayList<User> registeredUsers) {
        SystemCache.registeredUsers = registeredUsers;
    }



}
