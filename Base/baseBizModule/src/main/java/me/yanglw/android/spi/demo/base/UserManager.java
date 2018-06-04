package me.yanglw.android.spi.demo.base;

/**
 * Created by yanglw on 2018-05-28.
 */
public class UserManager {
    private static final UserInfo INFO = new UserInfo("yanglw", "weigeku8@gmail.com");

    public static UserInfo getCurrentUserInfo() {
        return INFO;
    }
}
