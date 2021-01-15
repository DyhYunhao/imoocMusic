package com.dyh.imoocmusic;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.dyh.imoocmusic.utils.RealmUtil;

import io.realm.Realm;

/**
 * describe: 全局Application 存储系统信息
 * create by daiyh on 2021-1-5
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化AndroidUtilCode
        Utils.init(this);

        Realm.init(this);
        RealmUtil.migration();
    }

}
