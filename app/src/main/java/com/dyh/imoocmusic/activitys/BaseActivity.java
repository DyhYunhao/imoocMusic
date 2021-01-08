package com.dyh.imoocmusic.activitys;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dyh.imoocmusic.R;

/**
 * describe: 统一Activity的父类
 * create by daiyh on 2021-1-5
 */
public class BaseActivity extends AppCompatActivity {

    private ImageView mIvBack, mIvMe;
    private TextView mTvBarTitle;

    protected void initNavBar(boolean isShowBack, String navTitle, boolean isShowMe) {
        mIvBack = findViewById(R.id.iv_back);
        mIvMe = findViewById(R.id.iv_me);
        mTvBarTitle = findViewById(R.id.tv_nav_bar_title);

        mIvBack.setVisibility(isShowBack ? View.VISIBLE : View.GONE);
        mIvMe.setVisibility(isShowMe ? View.VISIBLE : View.GONE);
        mTvBarTitle.setText(navTitle);

        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mIvMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BaseActivity.this, MeActivity.class));
            }
        });
    }

}
