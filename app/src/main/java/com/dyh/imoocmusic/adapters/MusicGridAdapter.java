package com.dyh.imoocmusic.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.AnimatorRes;
import androidx.annotation.NonNull;
import androidx.annotation.PluralsRes;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dyh.imoocmusic.R;
import com.dyh.imoocmusic.activitys.AlbumListActivity;
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
        Glide.with(mContext)
                .load("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=167119816,1724333262&fm=26&gp=0.jpg")
                .into(holder.mIvMusicImg);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, AlbumListActivity.class);
                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
//        return mData.size();
        return 6;
    }

    static class MusicViewHolder extends RecyclerView.ViewHolder {

        ImageView mIvMusicImg;
        View itemView;

        public MusicViewHolder(@NonNull View itemView) {
            super(itemView);
            mIvMusicImg = itemView.findViewById(R.id.iv_music_img_item);
            this.itemView = itemView;
        }
    }

}
