package com.dyh.imoocmusic.utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import com.blankj.utilcode.util.LogUtils;

import java.io.IOException;

/**
 * describe: 播放器
 * create by daiyh on 2021-1-13
 */
public class MediaPlayerUtil {

    private static MediaPlayerUtil instance;
    private Context mContext;
    private MediaPlayer mMediaPlayer;
    private OnMediaPlayerListener mMediaPlayerListener;
    private String mPath;

    public MediaPlayerUtil(OnMediaPlayerListener mMediaPlayerListener) {
        this.mMediaPlayerListener = mMediaPlayerListener;
    }

    public void setOnMediaPlayerListener(OnMediaPlayerListener listener) {
        mMediaPlayerListener = listener;
    }

    public static MediaPlayerUtil getInstance(Context context) {
        if (instance == null) {
            synchronized (MediaPlayerUtil.class) {
                if (instance == null) {
                    instance = new MediaPlayerUtil(context);
                }
            }
        }
        return instance;
    }

    private MediaPlayerUtil(Context context) {
        mContext = context;
        mMediaPlayer = new MediaPlayer();
    }

    /**
     * 1.setPath: 当前需要播放的音乐
     * 2.start： 播放音乐
     * 3.pause: 暂停播放
     */
    public void setPath(String path) {

        /**
         * 1.音乐正在播放，重置音乐播放状态
         * 2.设置播放音乐路径
         * 3.准备播放
         */
//        1.音乐正在播放或者切换了音乐，重置音乐播放状态
        if (mMediaPlayer.isPlaying() || !path.equals(mPath)) {
            mMediaPlayer.reset();
        }
        mPath = path;

//        2.设置播放音乐路径
        try {
            mMediaPlayer.setDataSource(mContext, Uri.parse(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

//        3.准备播放
        mMediaPlayer.prepareAsync();
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                if (mMediaPlayerListener != null) {
                    mMediaPlayerListener.onPrepared(mediaPlayer);
                }
            }
        });

        //监听音乐播放完成
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if (mMediaPlayerListener != null) {
                    mMediaPlayerListener.onCompletion(mediaPlayer);
                }
            }
        });
    }

    public void start() {
        if (mMediaPlayer.isPlaying()) return;
        mMediaPlayer.start();
    }

    public void pause() {
        mMediaPlayer.pause();
    }

    /**
     * 获取播放路径
     */
    public String getPath() {
        return mPath;
    }

    public interface OnMediaPlayerListener {
        void onPrepared(MediaPlayer mp);
        void onCompletion(MediaPlayer mp);
    }
}
