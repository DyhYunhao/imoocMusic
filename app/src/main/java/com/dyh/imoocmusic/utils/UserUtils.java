package com.dyh.imoocmusic.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.dyh.imoocmusic.R;
import com.dyh.imoocmusic.activitys.LoginActivity;
import com.dyh.imoocmusic.models.UserModel;

import java.util.List;

import io.realm.RealmResults;

/**
 * describe:
 * create by daiyh on 2021-1-6
 */
public class UserUtils {
    /**
     * 验证用户
     */
    public static boolean validateLogin(Context context, String phone, String password) {
        if (!RegexUtils.isMobileExact(phone)) {
            Toast.makeText(context, "无效手机号", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(context, "请输入密码", Toast.LENGTH_SHORT).show();
            return false;
        }

        /**
         * 1.用户当前输入的手机号是否注册
         * 2.用户输入的手机号和密码是否匹配
         */
        if (!UserUtils.isUserExist(phone)) {
            Toast.makeText(context, "当前手机号未注册", Toast.LENGTH_SHORT).show();
            return false;
        }

        RealmUtil realmUtil = new RealmUtil();
        boolean result = realmUtil.validateUser(phone, EncryptUtils.encryptMD5ToString(password));
//        realmUtil.close();
        if (!result) {
            Toast.makeText(context, "手机号或密码错误", Toast.LENGTH_SHORT).show();
            return false;
        }

        //保存用户登录
        boolean isSave = SPUtils.saveUser(context, phone);
        if (!isSave) {
            Toast.makeText(context, "系统错误，请稍后重试", Toast.LENGTH_SHORT).show();
            return false;
        }

        LoginUtil.getInstance().setPhone(phone);

        //保存音乐
        realmUtil.saveMusic(context);
        realmUtil.close();
        return true;
    }

    /**
     * 退出登录
     */
    public static void logout(Context context) {
        //删除用户登录标记
        boolean isRemove = SPUtils.removeUser(context);
        if (!isRemove) {
            Toast.makeText(context, "系统错误，请稍后重试", Toast.LENGTH_SHORT).show();
            return;
        }

        //删除音乐数据
        RealmUtil realmUtil = new RealmUtil();
        realmUtil.removeMusic();
        realmUtil.close();

        Intent intent = new Intent(context, LoginActivity.class);
        //添加intent标识符，清理task栈，并且新生成一个task栈
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        //定义Activity跳转动画
        ((Activity)context).overridePendingTransition(R.anim.open_enter, R.anim.open_exit);
    }

    /**
     * 注册用户
     */
    public static boolean registerUser(Context context, String phone, String password, String pwdConfirm) {
        if (!RegexUtils.isMobileExact(phone)) {
            Toast.makeText(context, "无效手机号", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (StringUtils.isEmpty(password) || !password.equals(pwdConfirm)) {
            Toast.makeText(context, "请确认密码", Toast.LENGTH_SHORT).show();
            return false;
        }

        //用户输入的手机号是否已经被注册
        /**
         * 1.获取当前已经注册的所有用户
         * 2.根据用户输入的手机号查询所有用户
         */
        if (UserUtils.isUserExist(phone)) {
            Toast.makeText(context, "该手机号已存在", Toast.LENGTH_SHORT).show();
            return false;
        }

        UserModel userModel = new UserModel();
        userModel.setPhone(phone);
        userModel.setPassword(EncryptUtils.encryptMD5ToString(password));
        saveUser(userModel);
        return true;
    }

    /**
     * 保存用户到数据库
     */
    public static void saveUser(UserModel userModel) {
        RealmUtil realmUtil = new RealmUtil();
        realmUtil.saveUser(userModel);
        realmUtil.close();
    }

    /**
     * 根据手机号判断用户是否存在
     */
    public static boolean isUserExist(String phone) {
        boolean result = false;
        RealmUtil realmUtil = new RealmUtil();
        List<UserModel> allUser = realmUtil.getAllUser();
        for (UserModel userModel: allUser) {
            if (userModel.getPhone().equals(phone)) {
                result = true;
                break;
            }
        }

        realmUtil.close();
        return result;
    }

    /**
     * 验证用户是否已登陆
     */
    public static boolean isUserLogin(Context context) {
        return SPUtils.isLogin(context);
    }

    /**
     * 修改密码
     */
    public static boolean changePassword(Context context, String oldPassword, String password, String confirm) {
        if (TextUtils.isEmpty(oldPassword)) {
            Toast.makeText(context, "请输入原密码", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(password) || !password.equals(confirm)) {
            Toast.makeText(context, "请确认新密码", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password.equals(oldPassword)) {
            Toast.makeText(context, "新密码不能与旧密码相同", Toast.LENGTH_SHORT).show();
            return false;
        }

        RealmUtil realmUtil = new RealmUtil();
        UserModel userModel = realmUtil.getUser();
        if (!EncryptUtils.encryptMD5ToString(password).equals(userModel.getPassword())) {
            Toast.makeText(context, "原密码不正确", Toast.LENGTH_SHORT).show();
            return false;
        }
        realmUtil.changePassword(EncryptUtils.encryptMD5ToString(password));
        realmUtil.close();
        return true;
    }
}
