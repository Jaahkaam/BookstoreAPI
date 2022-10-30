package dxc.bookstore.bookstoreapp.util;

import dxc.bookstore.bookstoreapp.model.entity.User;

public class ThreadContext {

    private static final ThreadLocal<User> threadUser = new ThreadLocal<User>();

    public static User getThreadUser(){
        return threadUser.get();
    }

    public static void setThreadUser(User user){
        threadUser.set(user);
    }

    public static void removeUser(){
        threadUser.remove();
    }

}
