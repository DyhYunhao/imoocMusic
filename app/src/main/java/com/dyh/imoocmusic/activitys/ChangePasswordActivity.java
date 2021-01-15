package com.dyh.imoocmusic.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.dyh.imoocmusic.R;
import com.dyh.imoocmusic.utils.UserUtils;
import com.dyh.imoocmusic.views.InputView;

public class ChangePasswordActivity extends BaseActivity {

    private InputView mIpvOldPassword, mIpvNewPassword, mIpvConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        initView();
    }

    private void initView() {
        initNavBar(true, "修改密码", false);

        mIpvOldPassword = findViewById(R.id.ipv_old_password);
        mIpvNewPassword = findViewById(R.id.ipv_new_password);
        mIpvConfirmPassword = findViewById(R.id.ipv_confirm_new_password);

    }

    public void sureClick(View view) {
        String oldPassword = mIpvOldPassword.getInputStr();
        String password = mIpvNewPassword.getInputStr();
        String confirmPassword = mIpvConfirmPassword.getInputStr();

        boolean result = UserUtils.changePassword(this, oldPassword, password, confirmPassword);
        if (!result) {
            return;
        }
        UserUtils.logout(this);
    }
}