package com.dyh.imoocmusic.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dyh.imoocmusic.R;
import com.dyh.imoocmusic.views.InputView;

public class RegisterActivity extends BaseActivity {

    private InputView mIpvPhone, mIpvPassword, mIpvConfirmPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    private void initView() {
        initNavBar(true, "注册", false);

        mIpvPhone = findViewById(R.id.ipv_r_phone);
        mIpvPassword = findViewById(R.id.ipv_r_password);
        mIpvConfirmPwd = findViewById(R.id.ipv_confirm_password);

        
    }
}