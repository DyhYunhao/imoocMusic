package com.dyh.imoocmusic.bean;

/**
 * describe: 音乐实体
 * create by daiyh on 2021-1-8
 */
public class Music {

    private String musicImgUrl;
    private String musicName;
    private double playNum;

    public String getMusicImgUrl() {
        return musicImgUrl;
    }

    public void setMusicImgUrl(String musicImgUrl) {
        this.musicImgUrl = musicImgUrl;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public double getPlayNum() {
        return playNum;
    }

    public void setPlayNum(double playNum) {
        this.playNum = playNum;
    }
}
