package com.dyh.imoocmusic.utils;

import android.util.Log;

/**
 * describe: 用户登录和退出
 * create by daiyh on 2021-1-14
 */
public class LoginUtil {
    private static LoginUtil instance;
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private LoginUtil() {}

    public static LoginUtil getInstance() {
        if (instance == null) {
            synchronized (LoginUtil.class) {
                if (instance == null) {
                    instance = new LoginUtil();
                }
            }
        }
        return instance;
    }
}
