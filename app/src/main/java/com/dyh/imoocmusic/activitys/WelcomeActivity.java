package com.dyh.imoocmusic.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.dyh.imoocmusic.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 欢迎界面，延迟三秒进入主页
 */
public class WelcomeActivity extends BaseActivity {
    private static final String TAG = "WelcomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initView();
    }

    /**
     * 初始化view和data
     */
    private void initView() {
        Timer mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.d(TAG, "当前线程为: " + Thread.currentThread());
                //跳转到主页
//                toMain();
                //跳转登录页
                toLogin();
            }
        }, 3 * 1000);
    }

    /**
     * 跳转到主页
     */
    private void toMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 跳转到登录页面
     */
    private void toLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}