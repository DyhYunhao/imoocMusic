package com.dyh.imoocmusic.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.RequiresApi;

import com.dyh.imoocmusic.R;
import com.dyh.imoocmusic.activitys.WelcomeActivity;
import com.dyh.imoocmusic.models.SongModel;
import com.dyh.imoocmusic.utils.MediaPlayerUtil;

/**
 * 1.通过service链接
 * 2.播放音乐，暂停音乐
 * 启动service，绑定service，解除绑定service
 * 3。监听音乐播放完成，停止service
 */
public class MusicService extends Service {

    //不可为0
    public static final int NOTIFICATION_ID = 1;

    private MediaPlayerUtil mMediaPlayerUtil;
    private SongModel mSongModel;

    public MusicService() {
    }

    public class MusicBind extends Binder {
        /**
         * 设置音乐
         */
        public void setMusic(SongModel songModel) {
            mSongModel = songModel;
            startForeground();
        }

        /**
         * 播放音乐
         */
        public void playMusic() {
            /**
             * 1.判断当前的音乐是否是已经在播放的音乐
             * 2.是的话直接执行start方法
             * 3.如果不是，就调用setPath方法
             */
            if (mMediaPlayerUtil.getPath() != null && mMediaPlayerUtil.getPath().equals(mSongModel.getPath())) {
                mMediaPlayerUtil.start();
            } else {
                mMediaPlayerUtil.setPath(mSongModel.getPath());
                mMediaPlayerUtil.setOnMediaPlayerListener(new MediaPlayerUtil.OnMediaPlayerListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mMediaPlayerUtil.start();
                    }

                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        stopSelf();
                    }
                });
            }
        }

        /**
         * 暂停播放
         */
        public void stopMusic() {
            mMediaPlayerUtil.pause();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MusicBind();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayerUtil = MediaPlayerUtil.getInstance(this);
    }

    /**
     * 系统默认不允许不可见的后台服务播放音乐
     */
    //设置服务前台可见
    private void startForeground() {
        //通知栏点击跳转的intent
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0,
                new Intent(this, WelcomeActivity.class), PendingIntent.FLAG_CANCEL_CURRENT);

        //创建Notification
//        Notification notification = new Notification.Builder(this)
//                .setContentTitle(mSongModel.getName())
//                .setContentText(mSongModel.getPoster())
//                .setSmallIcon(R.mipmap.logo)
//                .setContentIntent(pendingIntent)
//                .build();
        /**
         * 创建Notification
         */
        Notification notification = null;
        /**
         * android API 26 以上 NotificationChannel 特性适配
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = createNotificationChannel();
            notification = new Notification.Builder(this, channel.getId())
                    .setContentTitle(mSongModel.getName())
                    .setContentText(mSongModel.getAuthor())
                    .setSmallIcon(R.mipmap.logo)
                    .setContentIntent(pendingIntent)
                    .build();
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        } else {
            notification = new Notification.Builder(this)
                    .setContentTitle(mSongModel.getName())
                    .setContentText(mSongModel.getAuthor())
                    .setSmallIcon(R.mipmap.logo)
                    .setContentIntent(pendingIntent)
                    .build();
        }

        //设置notification在前台展示
        startForeground(NOTIFICATION_ID, notification);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private NotificationChannel createNotificationChannel () {
        String channelId = "imooc";
        String channelName = "ImoocMusicService";
        String Description = "ImoocMusic";
        NotificationChannel channel = new NotificationChannel(channelId,
                channelName, NotificationManager.IMPORTANCE_HIGH);
        channel.setDescription(Description);
        channel.enableLights(true);
        channel.setLightColor(Color.RED);
        channel.enableVibration(true);
        channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        channel.setShowBadge(false);
        return channel;
    }
}