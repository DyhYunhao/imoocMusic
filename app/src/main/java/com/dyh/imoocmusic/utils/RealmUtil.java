package com.dyh.imoocmusic.utils;

import android.content.Context;

import com.dyh.imoocmusic.migration.Migration;
import com.dyh.imoocmusic.models.AlbumModel;
import com.dyh.imoocmusic.models.MusicModel;
import com.dyh.imoocmusic.models.SongModel;
import com.dyh.imoocmusic.models.UserModel;

import java.io.FileNotFoundException;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * describe:Realm数据库
 * create by daiyh on 2021-1-13
 */
public class RealmUtil {
    private Realm mRealm;

    public RealmUtil() {
        mRealm = Realm.getDefaultInstance();
    }

    /**
     * 保存用户信息
     */
    public void saveUser(UserModel userModel) {
        mRealm.beginTransaction();
        mRealm.insert(userModel);
        mRealm.commitTransaction();
    }

    /**
     * Realm数据库发生结构性变化时 （模型或模型中字段发生新增，修改和删除）
     * 需要对数据库进行迁移
     */
    private static RealmConfiguration getRealmConfig() {
        return new RealmConfiguration.Builder()
                .schemaVersion(1)
                .migration(new Migration())
                .build();
    }

    /**
     * 告诉realm数据需要迁移，并且设置最新的配置
     */
    public static void migration() {
        RealmConfiguration conf = getRealmConfig();
        Realm.setDefaultConfiguration(conf);
        try {
            Realm.migrateRealm(conf);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭数据库
     */
    public void close() {
        if (mRealm != null && !mRealm.isClosed()) {
            mRealm.close();
        }
    }

    /**
     * 返回所有用户
     */
    public List<UserModel> getAllUser() {
        RealmQuery<UserModel> query = mRealm.where(UserModel.class);
        RealmResults<UserModel> results = query.findAll();
        return results;
    }

    /**
     * 验证用户信息
     */
    public boolean validateUser(String phone, String password) {
        boolean result = false;
        RealmQuery<UserModel> query = mRealm.where(UserModel.class);
        query.equalTo("phone", phone).equalTo("password", password);
        UserModel userModel = query.findFirst();
        if (userModel != null) {
            result = true;
        }
        return result;
    }

    /**
     * 获取当前用户
     */
    public UserModel getUser() {
        RealmQuery<UserModel> query = mRealm.where(UserModel.class);
        UserModel userModel = query.equalTo("phone", LoginUtil.getInstance().getPhone()).findFirst();
        return userModel;
    }

    /**
     * 修改密码
     */
    public void changePassword(String password) {
        UserModel userModel = getUser();
        mRealm.beginTransaction();
        userModel.setPassword(password);
        mRealm.commitTransaction();
    }

    /**
     * 保存音乐源数据
     */
    public void saveMusic(Context context) {
        String music = DataUtils.getJsonFromAssets(context, "DataSource.json");
        mRealm.beginTransaction();
        mRealm.createObjectFromJson(MusicModel.class, music);
        mRealm.commitTransaction();
    }

    /**
     * 删除音乐源数据
     */
    public void removeMusic() {
        mRealm.beginTransaction();
        mRealm.delete(MusicModel.class);
        mRealm.delete(SongModel.class);
        mRealm.delete(AlbumModel.class);
        mRealm.commitTransaction();
    }

    /**
     * 返回音乐源数据
     */
    public MusicModel getMusicSource() {
        return mRealm.where(MusicModel.class).findFirst();
    }

    /**
     * 返回歌单
     */
    public AlbumModel getAlbum(String albumId) {
        return mRealm.where(AlbumModel.class).equalTo("albumId", albumId).findFirst();
    }

    /**
     * 返回音乐
     */
    public SongModel getSong(String songId) {
        return mRealm.where(SongModel.class).equalTo("musicId", songId).findFirst();
    }
}
