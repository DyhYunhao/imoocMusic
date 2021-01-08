package com.dyh.imoocmusic.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dyh.imoocmusic.R;
import com.dyh.imoocmusic.utils.UserUtils;

public class MeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        initView();
    }

    private void initView() {
        initNavBar(true, "个人中心", false);

    }

    //修改密码
    public void changePassword(View view) {
        startActivity(new Intent(this, ChangePasswordActivity.class));
    }

    //退出登录
    public void logoutClick(View view) {
        UserUtils.logout(this);
    }
}