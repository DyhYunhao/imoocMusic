package com.dyh.imoocmusic.views;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
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
import com.dyh.imoocmusic.models.SongModel;
import com.dyh.imoocmusic.service.MusicService;
import com.dyh.imoocmusic.utils.MediaPlayerUtil;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * describe:
 * create by daiyh on 2021-1-11
 */
public class PlayMusicView extends FrameLayout {

    private Context mContext;
    //    private MediaPlayerUtil mMediaPlayerUtil;
    private Intent mServiceIntent;
    private MusicService.MusicBind mMusicBind;
    private SongModel mSongModel;

    private View mView;
    private CircleImageView mCivMusicImg;
    private Animation mPlayMusicAnim, mPlayNeedleAnim, mStopNeedleAnim;
    private FrameLayout mFlPlayMusic;
    private ImageView mIvPlayNeedle, mIvPlayButton;
    private boolean isPlaying, isBindService;
    private String mPath;

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

//        mMediaPlayerUtil = MediaPlayerUtil.getInstance(mContext);

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
//        mPath = path;
        mIvPlayButton.setVisibility(View.GONE);
        mFlPlayMusic.startAnimation(mPlayMusicAnim);
        mIvPlayNeedle.startAnimation(mPlayNeedleAnim);

        startMusicService();

//        /**
//         * 1.判断当前的音乐是否是已经在播放的音乐
//         * 2.是的话直接执行start方法
//         * 3.如果不是，就调用setPath方法
//         */
//        if (mMediaPlayerUtil.getPath() != null && mMediaPlayerUtil.getPath().equals(path)) {
//            mMediaPlayerUtil.start();
//        } else {
//            mMediaPlayerUtil.setPath(path);
//            mMediaPlayerUtil.setOnMediaPlayerListener(new MediaPlayerUtil.OnMediaPlayerListener() {
//                @Override
//                public void onPrepared(MediaPlayer mp) {
//                    mMediaPlayerUtil.start();
//                }
//            });
//        }
    }

    /**
     * 停止播放音乐
     */
    public void stopPlayMusic() {
        isPlaying = false;
        mIvPlayButton.setVisibility(View.VISIBLE);
        mFlPlayMusic.clearAnimation();
        mIvPlayNeedle.startAnimation(mStopNeedleAnim);

//        mMediaPlayerUtil.pause();
        if (mMusicBind != null)
            mMusicBind.stopMusic();
    }

    /**
     * 设置光盘中显示的音乐封面图片
     */
    public void setMusicIcon() {
//        init(context);
        Glide.with(mContext).load(mSongModel.getPoster()).into(mCivMusicImg);
    }

    public void setMusic(SongModel songModel) {
        mSongModel = songModel;
        setMusicIcon();
    }

    /**
     * 启动音乐服务
     */
    private void startMusicService() {
        //启动Service
        if (mServiceIntent == null) {
            mServiceIntent = new Intent(mContext, MusicService.class);
            mContext.startService(mServiceIntent);
        } else {
            mMusicBind.playMusic();
        }

        //绑定Service
        if (!isBindService) {
            isBindService = true;
            mContext.bindService(mServiceIntent, conn, Context.BIND_AUTO_CREATE);
        }
    }

    /**
     * 解除绑定
     */
    public void destory() {
        if (isBindService) {
            isBindService = false;
            mContext.unbindService(conn);
        }
    }

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mMusicBind = (MusicService.MusicBind) iBinder;
            mMusicBind.setMusic(mSongModel);
            mMusicBind.playMusic();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

}
