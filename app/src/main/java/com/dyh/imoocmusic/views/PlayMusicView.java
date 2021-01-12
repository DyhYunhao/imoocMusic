package com.dyh.imoocmusic.views;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;
import com.dyh.imoocmusic.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * describe:
 * create by daiyh on 2021-1-11
 */
public class PlayMusicView extends FrameLayout {

    private Context mContext;
    private View mView;
    private CircleImageView mCivMusicImg;
    private Animation mPlayMusicAnim, mPlayNeedleAnim, mStopNeedleAnim;
    private FrameLayout mFlPlayMusic;
    private ImageView mIvPlayNeedle, mIvPlayButton;
    private boolean isPlaying;

    public PlayMusicView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mView = LayoutInflater.from(mContext).inflate(R.layout.view_play_music, this, false);
        mCivMusicImg = mView.findViewById(R.id.civ_music_img);
        mFlPlayMusic = mView.findViewById(R.id.fl_play_music);
        mIvPlayNeedle = mView.findViewById(R.id.iv_play_needle);
        mIvPlayButton = mView.findViewById(R.id.iv_play_button);

        mFlPlayMusic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                trigger();
            }
        });

        //1.定义所需要执行的动画 （1）光盘转动的动画 （2）指针指向光盘的动画 （3）指针离开光盘的动画
        //2.开始动画
        mPlayMusicAnim = AnimationUtils.loadAnimation(mContext, R.anim.play_music);
        mPlayNeedleAnim = AnimationUtils.loadAnimation(mContext, R.anim.play_needle);
        mStopNeedleAnim = AnimationUtils.loadAnimation(mContext, R.anim.stop_needle);

        addView(mView);


    }

    /**
     * 切换播放状态
     */
    private void trigger() {
        if (isPlaying) {
            stopPlayMusic();
        } else {
            playMusic();
        }
    }

    /**
     * 播放音乐
     */
    public void playMusic() {
        isPlaying = true;
        mIvPlayButton.setVisibility(View.GONE);
        mFlPlayMusic.startAnimation(mPlayMusicAnim);
        mIvPlayNeedle.startAnimation(mPlayNeedleAnim);
    }

    /**
     * 停止播放音乐
     */
    public void stopPlayMusic() {
        isPlaying = false;
        mIvPlayButton.setVisibility(View.VISIBLE);
        mFlPlayMusic.clearAnimation();
        mIvPlayNeedle.startAnimation(mStopNeedleAnim);
    }

    /**
     * 设置光盘中显示的音乐封面图片
     */
    public void setMusicIcon(String icon) {
//        init(context);
        Glide.with(mContext).load(icon).into(mCivMusicImg);
    }
}
