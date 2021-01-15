package com.dyh.imoocmusic.activitys;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dyh.imoocmusic.R;
import com.dyh.imoocmusic.models.SongModel;
import com.dyh.imoocmusic.utils.RealmUtil;
import com.dyh.imoocmusic.views.PlayMusicView;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class PlayMusicActivity extends BaseActivity {

    public static final String MUSIC_ID = "musicId";

    private ImageView mIvPlayBg;
    private PlayMusicView mPmvMusicView;
    private TextView mTvMusicName, mTvMusicAuthor;
    private String mMusicId;
    private SongModel mSong;
    private RealmUtil mRealmUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        initData();
        initView();
    }

    private void initData() {
        mMusicId = getIntent().getStringExtra(MUSIC_ID);
        mRealmUtil = new RealmUtil();
        mSong = mRealmUtil.getSong(mMusicId);
    }

    private void initView() {
        //隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mIvPlayBg = findViewById(R.id.iv_play_bg);
        mTvMusicName = findViewById(R.id.tv_music_name);
        mTvMusicAuthor = findViewById(R.id.tv_music_author);
        Glide.with(this)
                .load(mSong.getPoster())
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 10)))
                .into(mIvPlayBg);
        mTvMusicName.setText(mSong.getName());
        mTvMusicAuthor.setText(mSong.getAuthor());

        mPmvMusicView = findViewById(R.id.pmv_music_view);
//        mPmvMusicView.setMusicIcon(mSong.getPoster());
        mPmvMusicView.setMusic(mSong);
//        mPmvMusicView.playMusic(mSong.getPath());
        mPmvMusicView.playMusic();
    }

    /**
     * 点击后退
     */
    public void backClick(View view) {
        onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPmvMusicView.destory();
        mRealmUtil.close();
    }
}