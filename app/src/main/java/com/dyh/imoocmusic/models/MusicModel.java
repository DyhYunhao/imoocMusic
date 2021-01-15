package com.dyh.imoocmusic.models;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * describe: 音乐资源
 * create by daiyh on 2021-1-14
 */
public class MusicModel extends RealmObject {
    private RealmList<AlbumModel> album;
    private RealmList<SongModel> hot;

    public RealmList<AlbumModel> getAlbum() {
        return album;
    }

    public void setAlbum(RealmList<AlbumModel> album) {
        this.album = album;
    }

    public RealmList<SongModel> getHot() {
        return hot;
    }

    public void setHot(RealmList<SongModel> hot) {
        this.hot = hot;
    }
}
