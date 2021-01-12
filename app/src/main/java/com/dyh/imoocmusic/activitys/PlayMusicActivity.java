package com.dyh.imoocmusic.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dyh.imoocmusic.R;
import com.dyh.imoocmusic.views.PlayMusicView;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class PlayMusicActivity extends BaseActivity {

    private ImageView mIvPlayBg;
    private PlayMusicView mPmvMusicView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        initView();
    }

    private void initView() {
        //隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mIvPlayBg = findViewById(R.id.iv_play_bg);
        Glide.with(this)
                .load("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=167119816,1724333262&fm=26&gp=0.jpg")
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 10)))
                .into(mIvPlayBg);

        mPmvMusicView = findViewById(R.id.pmv_music_view);
        mPmvMusicView.setMusicIcon("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=167119816,1724333262&fm=26&gp=0.jpg");
        mPmvMusicView.playMusic();
    }

    /**
     * 点击后退
     */
    public void backClick(View view) {
        onBackPressed();
    }
}