package com.dyh.imoocmusic.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.dyh.imoocmusic.constants.SPConstants;

import java.util.function.BinaryOperator;

/**
 * describe: SharedPreferences封装
 * create by daiyh on 2021-1-14
 */
public class SPUtils {
    /**
     * 用户登录时保存用户手机号
     * @param context context
     * @param phone phone
     */
    public static boolean saveUser(Context context, String phone) {
        SharedPreferences sp = context.getSharedPreferences(SPConstants.SP_NAME_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SPConstants.SP_KEY_PHONE, phone);
        boolean result = editor.commit();
        return result;
    }

    /**
     * 验证用户是否已登陆
     */
    public static boolean isLogin(Context context) {
        boolean result = false;
        SharedPreferences sp = context.getSharedPreferences(SPConstants.SP_NAME_USER, Context.MODE_PRIVATE);
        String phone = sp.getString(SPConstants.SP_KEY_PHONE, "");
        if (!TextUtils.isEmpty(phone)) {
            result = true;
            LoginUtil.getInstance().setPhone(phone);
        }

        return result;
    }

    /**
     * 删除用户登陆标记
     */
    public static boolean removeUser(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SPConstants.SP_NAME_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(SPConstants.SP_KEY_PHONE);
        return editor.commit();
    }

}
