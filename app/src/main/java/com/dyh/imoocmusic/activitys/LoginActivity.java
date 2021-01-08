package com.dyh.imoocmusic.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dyh.imoocmusic.R;
import com.dyh.imoocmusic.utils.UserUtils;
import com.dyh.imoocmusic.views.InputView;

public class LoginActivity extends BaseActivity {

    private InputView mIpvPhone, mIpvPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    /**
     * 初始化view
     */
    private void initView() {
        initNavBar(false, "登录", false);

        mIpvPhone = findViewById(R.id.ipv_phone);
        mIpvPassword = findViewById(R.id.ipv_password);

    }

    /**
     * 注册点击跳转
     */
    public void registerClick(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    /**
     * 点击登录
     */
    public void commitClick(View view) {
        String phone = mIpvPhone.getInputStr();
        String password = mIpvPassword.getInputStr();
        //验证用户输入是否合法
//        if (!UserUtils.validateLogin(this, phone, password)) {
//            return;
//        }

        //跳转到应用主页
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}