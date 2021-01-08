package com.dyh.imoocmusic.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.AnimatorRes;
import androidx.annotation.NonNull;
import androidx.annotation.PluralsRes;
import androidx.recyclerview.widget.RecyclerView;

import com.dyh.imoocmusic.R;
import com.dyh.imoocmusic.bean.Music;

import java.util.ArrayList;

/**
 * describe: 推荐音乐适配器
 * create by daiyh on 2021-1-8
 */

public class MusicGridAdapter extends RecyclerView.Adapter<MusicGridAdapter.MusicViewHolder> {

    private Context mContext;
    private ArrayList<Music> mData;

    public MusicGridAdapter(Context mContext, ArrayList<Music> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MusicViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_music_grid, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
//        return mData.size();
        return 6;
    }

    class MusicViewHolder extends RecyclerView.ViewHolder {

        public MusicViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
