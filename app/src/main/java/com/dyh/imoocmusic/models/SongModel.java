package com.dyh.imoocmusic.models;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * describe: 歌曲model
 * create by daiyh on 2021-1-14
 */
public class SongModel extends RealmObject {
    /**
     *  {
     *           "musicId": "101",
     *           "name": "Nostalgic Piano",
     *           "poster": "http://res.lgdsunday.club/poster-1.png",
     *           "path": "http://res.lgdsunday.club/Nostalgic%20Piano.mp3",
     *           "author": "Rafael Krux"
     *  }
     */
    private String musicId;
    private String name;
    private String poster;
    private String path;
    private String author;

    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
