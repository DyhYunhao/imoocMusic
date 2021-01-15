package com.dyh.imoocmusic.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dyh.imoocmusic.R;
import com.dyh.imoocmusic.activitys.AlbumListActivity;
import com.dyh.imoocmusic.models.AlbumModel;

import java.util.List;

/**
 * describe: 推荐音乐适配器
 * create by daiyh on 2021-1-8
 */

public class MusicGridAdapter extends RecyclerView.Adapter<MusicGridAdapter.MusicViewHolder> {

    private Context mContext;
    private List<AlbumModel> mData;

    public MusicGridAdapter(Context mContext, List<AlbumModel> mData) {
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
        AlbumModel albumModel = mData.get(position);

        Glide.with(mContext)
                .load(albumModel.getPoster())
                .into(holder.mIvMusicImg);
        holder.mTvPlayNum.setText(albumModel.getPlayNum());
        holder.mTvAlbumName.setText(albumModel.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, AlbumListActivity.class);
                intent.putExtra(AlbumListActivity.ALBUM_ID, albumModel.getAlbumId());
                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mData.size();
//        return 6;
    }

    static class MusicViewHolder extends RecyclerView.ViewHolder {

        ImageView mIvMusicImg;
        View itemView;
        TextView mTvPlayNum, mTvAlbumName;

        public MusicViewHolder(@NonNull View itemView) {
            super(itemView);
            mIvMusicImg = itemView.findViewById(R.id.iv_music_img_item);
            mTvPlayNum = itemView.findViewById(R.id.tv_item_play_num);
            mTvAlbumName = itemView.findViewById(R.id.tv_item_album_name);
            this.itemView = itemView;
        }
    }

}
