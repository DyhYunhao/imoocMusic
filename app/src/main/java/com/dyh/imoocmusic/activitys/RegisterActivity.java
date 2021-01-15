package com.dyh.imoocmusic.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.dyh.imoocmusic.R;
import com.dyh.imoocmusic.utils.UserUtils;
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

    /**
     * 注册点击
     * 1.用户输入合法性验证  2.保存用户输入的手机和密码（MD5加密）
     * @param view view
     */
    public void registerClick1(View view) {
        String phone = mIpvPhone.getInputStr();
        String password = mIpvPassword.getInputStr();
        String pwdConfirm = mIpvConfirmPwd.getInputStr();

        boolean result = UserUtils.registerUser(this, phone, password, pwdConfirm);

        if (!result) return;
        onBackPressed();
    }
}