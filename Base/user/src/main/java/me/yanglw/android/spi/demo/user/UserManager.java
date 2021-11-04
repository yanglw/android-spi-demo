package me.yanglw.android.spi.demo.user;

/**
 * Created by yanglw on 2018-05-28.
 */
public class UserManager {
    private static final UserManager MANAGER = new UserManager();

    private static final UserInfo INFO = new UserInfo("yanglw", "weigeku8@gmail.com");
    private UserInfo info;

    private UserManager() {
        // get from storage.
        info = INFO;
    }

    public static UserManager get() {
        return MANAGER;
    }

    public UserInfo getCurrentUserInfo() {
        return info == null ? INFO : info;
    }

    public boolean isLogin() {
        return info != INFO;
    }

    public boolean updateUserInfo(UserInfo info) {
        if (info == null) {
            return false;
        }
        this.info = info;
        // save to storage.
        return true;
    }

    public boolean logout() {
        info = INFO;
        // clean storage.
        return true;
    }
}
