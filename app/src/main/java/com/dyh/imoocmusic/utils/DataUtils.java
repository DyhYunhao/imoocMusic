package com.dyh.imoocmusic.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * describe: 歌曲数据获取
 * create by daiyh on 2021-1-14
 */
public class DataUtils {

    /**
     * 读取资源文件中的数据
     */
    public static String getJsonFromAssets(Context context, String fileName) {
        /**
         * 1.StringBuild存放读取出的数据
         * 2.AssetManager 资源管理器， open方法打开指定的资源文件，返回InputStream
         * 3.InputStreamReader(字节到字符的桥接器) BufferReader(存放读取字符的缓冲区)
         * 4.循环利用BufferReader的readerLine方法读取每一行的数据
         */
        StringBuilder stringBuilder = new StringBuilder();
        AssetManager assetManager = context.getAssets();
        try {
            InputStream inputStream = assetManager.open(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

}
