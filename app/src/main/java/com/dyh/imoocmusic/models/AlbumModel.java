package com.dyh.imoocmusic.models;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * describe: 专辑模型类
 * create by daiyh on 2021-1-14
 */
public class AlbumModel extends RealmObject {
    /**
     *  {
     *       "albumId": "1",
     *       "name": "Nostalgic",
     *       "poster": "http://res.lgdsunday.club/poster-9.png",
     *       "playNum": "203.3万",
     *       "list": []
     *  }
     */
    private String albumId;
    private String name;
    private String poster;
    private String playNum;
    private RealmList<SongModel> list;

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getPlayNum() {
        return playNum;
    }

    public void setPlayNum(String playNum) {
        this.playNum = playNum;
    }

    public RealmList<SongModel> getList() {
        return list;
    }

    public void setList(RealmList<SongModel> list) {
        this.list = list;
    }
}
